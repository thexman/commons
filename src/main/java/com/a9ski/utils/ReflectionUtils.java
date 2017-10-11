/*
 * #%L
 * Commons utilities
 * %%
 * Copyright (C) 2017 Kiril Arabadzhiyski
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.a9ski.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for dealing with reflection
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class ReflectionUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);

	protected ReflectionUtils() {
		super();
	}

	/**
	 * Construct a setter method name for field
	 *
	 * @param fieldName
	 *            the field name
	 * @return the setter method name for field
	 */
	public static String constructSetterName(final String fieldName) {
		return constructXetterName(fieldName, "set");
	}

	/**
	 * Constructs a getter or setter method names
	 *
	 * @param fieldName
	 *            the field
	 * @param prefix
	 *            the prefix (set or get)
	 * @return the method name
	 */
	private static String constructXetterName(final String fieldName, final String prefix) {
		String methodName = fieldName;
		if (StringUtils.isNotEmpty(fieldName)) {
			methodName = prefix + fieldName.substring(0, 1).toUpperCase();
			if (fieldName.length() > 1) {
				methodName += fieldName.substring(1);
			}
		}
		return methodName;
	}

	/**
	 * Construct a getter method name for field
	 *
	 * @param fieldName
	 *            the field name
	 * @return the getter method name for field
	 */
	public static String constructGetterName(final String fieldName) {
		return constructXetterName(fieldName, "get");
	}

	/**
	 * Construct a getter method name for boolean field
	 *
	 * @param fieldName
	 *            the boolean field name
	 * @return getter method name for boolean field
	 */
	public static String constructBooleanGetterName(final String fieldName) {
		return constructXetterName(fieldName, "is");
	}

	/**
	 * Returns the getter {@link Method} for given field name. Returns null if the method doesn't exists
	 *
	 * @param c
	 *            the class
	 * @param fieldName
	 *            the field name
	 * @return the getter {@link Method} for given field name. Returns null if the method doesn't exists
	 */
	public static Method getGetter(final Class<?> c, final String fieldName) {
		final String getterName = constructGetterName(fieldName);
		Method method = getMethod(c, getterName, true, 0);
		if (method == null) {
			final String booleanGetterName = constructBooleanGetterName(fieldName);
			method = getMethod(c, booleanGetterName, true, 0);
			if (method != null && method.getReturnType() != null) {
				final boolean isNotPrimitiveBoolean = !boolean.class.equals(method.getReturnType());
				final boolean isNotBoolean = !Boolean.class.equals(method.getReturnType());
				if (isNotPrimitiveBoolean && isNotBoolean) {
					method = null; // the method doesn't return boolean
				}
			}
		}
		return method;
	}

	/**
	 * Returns the setter {@link Method} for given field name. Returns null if the method doesn't exists
	 *
	 * @param c
	 *            the class
	 * @param fieldName
	 *            the field name
	 * @return the setter {@link Method} for given field name. Returns null if the method doesn't exists
	 */
	public static Method getSetter(final Class<?> c, final String fieldName) {
		final String setterName = constructSetterName(fieldName);
		return getMethod(c, setterName, true, 1);
	}

	/**
	 * Returns the setter {@link Method} for given field name. Returns null if the method doesn't exists
	 *
	 * @param c
	 *            the class
	 * @param fieldName
	 *            the field name
	 * @param fieldType
	 *            the method return type. There is possible to have more than one setters, this parameter narrows down which setter to return
	 * @return the setter {@link Method} for given field name. Returns null if the method doesn't exists
	 */
	public static Method getSetter(final Class<?> c, final String fieldName, final Class<?> fieldType) {
		if (fieldType != null) {
			final String setterName = constructSetterName(fieldName);
			return getMethod(c, setterName, true, Arrays.asList(fieldType));
		} else {
			return getSetter(c, fieldName);
		}
	}

	/**
	 * Returns the first method matching the search criteria (<tt>methodName</tt>, <tt>numberOfParams</tt>)
	 * <p>
	 * If no method is matching the search criteria <tt>null</tt> is returned
	 *
	 * @param c
	 *            the class whose methods are searched
	 * @param methodName
	 *            the method name to search for
	 * @param ignoreCase
	 *            flag indicating to ignore case when comparing method names
	 * @param numberOfParams
	 *            the requested number of parameters for the method. Use -1 if the number of parameters doesn't matter
	 * @return the first method matching the search criteria (<tt>methodName</tt>, <tt>numberOfParams</tt>)
	 */
	public static Method getMethod(final Class<?> c, final String methodName, final boolean ignoreCase, final int numberOfParams) {
		if (StringUtils.isNotEmpty(methodName)) {
			for (final Method m : getAllMethods(c)) {
				if (m.getParameterTypes().length == numberOfParams || numberOfParams < 0) {
					if (ignoreCase && m.getName().equalsIgnoreCase(methodName)) {
						return m;
					} else if (!ignoreCase && m.getName().equals(methodName)) {
						return m;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns the first method matching the search criteria (<tt>methodName</tt>, <tt>numberOfParams</tt>)
	 * <p>
	 * If no method is matching the search criteria <tt>null</tt> is returned
	 *
	 * @param c
	 *            the class whose methods are searched
	 * @param methodName
	 *            the method name to search for
	 * @param ignoreCase
	 *            flag indicating to ignore case when comparing method names
	 * @param fieldTypes
	 *            list of parameter argument types
	 * @return the first method matching the search criteria (<tt>methodName</tt>, <tt>numberOfParams</tt>)
	 */
	public static Method getMethod(final Class<?> c, final String methodName, final boolean ignoreCase, final List<Class<?>> fieldTypes) {
		if (StringUtils.isNotEmpty(methodName)) {
			for (final Method m : getAllMethods(c)) {
				if (m.getParameterTypes().length == fieldTypes.size()) {
					boolean matchParams = true;
					final Parameter[] parameters = m.getParameters();
					for (int i = 0; i < fieldTypes.size(); i++) {
						if (!ClassUtils.isAssignable(fieldTypes.get(i), parameters[i].getType(), true)) {
							matchParams = false;
							break;
						}
					}
					if (matchParams && ignoreCase && m.getName().equalsIgnoreCase(methodName)) {
						return m;
					} else if (matchParams && !ignoreCase && m.getName().equals(methodName)) {
						return m;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns an list of {@code Method} objects reflecting all the methods declared by the class or interface represented by this {@code Class} object. This includes public, protected, default (package) access, and private methods, including inherited methods.
	 *
	 * @param c
	 *            the class whose methods are returned
	 * @return an list of all methods (public, protected, package and inherited)
	 */
	public static List<Method> getAllMethods(final Class<?> c) {
		final List<Method> methods = new ArrayList<>();
		if (c != null) {
			try {
				if (!c.isInterface()) {
					methods.addAll(Arrays.asList(c.getDeclaredMethods()));
				} else {
					methods.addAll(Arrays.asList(c.getDeclaredMethods()).stream().filter(m -> m.isDefault()).collect(Collectors.toList()));
				}
			} catch (final SecurityException ex) {
				LOGGER.debug("Cannot retrieve all method for class " + c, ex);
			}
			if (c.getSuperclass() != null) {
				methods.addAll(getAllMethods(c.getSuperclass()));
			}
			for (final Class<?> iface : c.getInterfaces()) {
				methods.addAll(getAllMethods(iface));
			}
		}
		return methods;
	}

	/**
	 * Returns an list of {@code Field} objects reflecting all the fields declared by the class or interface represented by this {@code Class} object. This includes public, protected, default (package) access, and private fields, including inherited fields.
	 *
	 * @param c
	 *            the class whose fields are returned
	 * @return all fields of the class (includes public, protected, default (package) access, and private fields, including inherited fields)
	 */
	public static List<Field> getAllFields(final Class<?> c) {
		final List<Field> fields = new ArrayList<>();

		try {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		} catch (final SecurityException ex) {
			LOGGER.debug("Cannot retrieve all fields for class " + c, ex);
		}
		if (c.getSuperclass() != null) {
			fields.addAll(getAllFields(c.getSuperclass()));
		}
		return fields;
	}

	/**
	 * Returns a {@code Field} object that reflects the specified field of the class or interface represented by this class. This includes public, protected, default (package) access, and private fields, including inherited fields. {@code Class} object
	 *
	 * @param c
	 *            the class whose fields are returned
	 * @param fieldName
	 *            the field name
	 * @return the field by name or null if no such field exists (package) access, and private fields, including inherited fields)
	 */
	public static Field getField(final Class<?> c, final String fieldName) {
		return getAllFields(c).stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
	}

	/**
	 * Returns a {@code Class} object that reflects the type of specified field of the class or interface represented by this class. This includes public, protected, default (package) access, and private fields, including inherited fields. {@code Class} object
	 *
	 * @param c
	 *            the class whose fields are returned
	 * @param fieldName
	 *            the field name
	 * @return the type of the field (specified by name) or null if no such field exists (package) access, and private fields, including inherited fields)
	 */
	public static Class<?> getFieldType(final Class<?> c, final String fieldName) {
		final Field f = getField(c, fieldName);
		if (f != null) {
			return f.getType();
		}
		return null;
	}

	/**
	 * Returns an list of {@code Field} objects reflecting all the fields declared by the class or interface represented by this {@code Class} object. This includes public, protected, default (package) access, and private fields, including inherited fields.
	 *
	 * @param c
	 *            the class whose fields are returned
	 * @param annotation
	 *            the annotation type
	 * @return all fields of the class (includes public, protected, default (package) access, and private fields, including inherited fields)
	 */
	public static List<Field> getAllFieldsWithAnnotation(final Class<?> c, final Class<? extends Annotation> annotation) {
		return getAllFields(c).stream().filter(f -> f.isAnnotationPresent(annotation)).collect(Collectors.toList());
	}

	/**
	 * Returns the generic type of the collection. e.g.:
	 * <p>
	 * for <tt>List&lt;String&gt;</tt> the result will be <tt>String</tt>
	 * <p>
	 * <tt>
	 * class MyClass {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;List&lt;Long&gt; myList;<br>
	 * };<br>
	 * System.out.println(ReflectionUtils.getCollectionGenericType(MyClass.getDeclaredField("myList").getGenericType()).getName());<br>
	 * </tt> will output the value <tt>java.lang.Long</tt>
	 * </p>
	 *
	 * @param type
	 *            the collection type (like field.getGenericType())
	 * @return the generic type of the collection
	 */
	public static Class<?> getCollectionGenericType(final Type type) {
		if (type instanceof ParameterizedType) {
			final ParameterizedType paramType = ((ParameterizedType) type);
			if (paramType.getRawType() instanceof Class) {
				final Class<?> c = (Class<?>) paramType.getRawType();
				if (Collection.class.isAssignableFrom(c)) {
					final Type[] argTypes = paramType.getActualTypeArguments();
					if (argTypes.length > 0 && argTypes[0] instanceof Class) {
						return (Class<?>) argTypes[0];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns the generic type of the collection. e.g.:
	 * <p>
	 * for <tt>List&lt;String&gt;</tt> the result will be <tt>String</tt>
	 * <p>
	 * <tt>
	 * class MyClass {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;List&lt;Long&gt; myList;<br>
	 * };<br>
	 * System.out.println(ReflectionUtils.getCollectionGenericType(MyClass.getDeclaredField("myList").getGenericType()).getName());<br>
	 * </tt> will output the value <tt>java.lang.Long</tt>
	 * </p>
	 *
	 * @param type
	 *            the collection type (like field.getGenericType())
	 * @return the generic type of the collection
	 */
	public static List<Class<?>> getGenericTypes(final Type type) {
		final List<Class<?>> paramClasses = new ArrayList<>();
		if (type instanceof ParameterizedType) {
			final ParameterizedType paramType = ((ParameterizedType) type);
			if (paramType.getRawType() instanceof Class) {

				final List<Type> argTypes = Arrays.asList(paramType.getActualTypeArguments());
				//@formatter:off
				paramClasses.addAll(argTypes.stream()
						.filter(argType -> argType instanceof Class)
						.map(a -> (Class<?>) a)
						.collect(Collectors.toList()));
				//@formatter:on
			}
		}
		return paramClasses;
	}

	/**
	 * Directly sets the field value without throwing checked exception
	 *
	 * @param object
	 *            the object which field will be set
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the new value of the field
	 */
	public static void setFieldValueSafe(final Object object, final String fieldName, final Object value) {
		try {
			setFieldValue(object, fieldName, value);
		} catch (final IllegalAccessException ex) {
			throw new IllegalStateException("Cannot set field '" + fieldName + "' with value '" + value + "' of object '" + object + "'", ex);
		}
	}

	/**
	 * Directly sets the field value
	 *
	 * @param object
	 *            the object which field will be set
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the new value of the field
	 * @throws IllegalArgumentException
	 *             thrown if an error occurs
	 * @throws IllegalAccessException
	 *             thrown if an error occurs
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) throws IllegalArgumentException, IllegalAccessException {
		final Field f = getField(object.getClass(), fieldName);
		if (f != null) {
			f.setAccessible(true);
			f.set(object, value);
		}
	}

	/**
	 * Sets the field value by invoking the setter method
	 *
	 * @param object
	 *            the object which field will be set
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the new value of the field
	 * @return true if the assign operation is successful
	 * @throws IllegalAccessException
	 *             thrown if an error occurs
	 * @throws IllegalArgumentException
	 *             thrown if an error occurs
	 * @throws InvocationTargetException
	 *             thrown if an error occurs
	 */
	public static boolean setFieldValueViaSetter(final Object object, final String fieldName, final Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Method setter;
		if (value != null) {
			setter = getSetter(object.getClass(), fieldName, value.getClass());
		} else {
			setter = getSetter(object.getClass(), fieldName);
		}
		if (setter != null) {
			if (value != null || !setter.getParameterTypes()[0].isPrimitive()) {
				setter.invoke(object, value);
			}
			return true;
		}
		return false;
	}

	/**
	 * Copies the field value from one object to another. Uses getter method to obtain field value from the source object, and setter method to set the value to the destination object
	 *
	 * @param src
	 *            the source object
	 * @param dst
	 *            the destination object
	 * @param fieldName
	 *            the field name
	 * @return true if the copy operation is successful
	 * @throws IllegalAccessException
	 *             thrown if an error occurs
	 * @throws IllegalArgumentException
	 *             thrown if an error occurs
	 * @throws InvocationTargetException
	 *             thrown if an error occurs
	 */
	public static boolean copyField(final Object src, final Object dst, final String fieldName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Method getter = getGetter(src.getClass(), fieldName);
		final Method setter = getSetter(dst.getClass(), fieldName);
		final boolean result;
		if (getter != null && setter != null) {
			final Object value = getter.invoke(src);
			setter.invoke(dst, value);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Gets field value via getter method or by direct read. If getter method doesn't exists, the field value is read directly
	 *
	 * @param obj
	 *            the object
	 * @param fieldName
	 *            the field name
	 * @return the field value
	 * @throws IllegalArgumentException
	 *             thrown if an error occurs
	 * @throws IllegalAccessException
	 *             thrown if an error occurs
	 * @throws InvocationTargetException
	 *             thrown if an error occurs
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		final Method getter = getGetter(obj.getClass(), fieldName);
		final Object value;
		if (getter != null) {
			value = getter.invoke(obj);
		} else {
			final Field f = getField(obj.getClass(), fieldName);
			;
			f.setAccessible(true);
			value = f.get(obj);
		}
		return value;
	}

	/**
	 * Construct a set with the field names from two lists
	 *
	 * @param fields1
	 *            list 1
	 * @param fields2
	 *            list 2
	 * @return set of field names contained in list 1 and list 2
	 */
	private static Set<String> getFieldNamesSet(final List<Field> fields1, final List<Field> fields2) {
		final Set<String> fieldNames = new TreeSet<>();
		fields1.forEach(f -> fieldNames.add(f.getName()));
		fields2.forEach(f -> fieldNames.add(f.getName()));
		return fieldNames;
	}

	/**
	 * Copy the values of all fields from source object to the target object that are annotated with provided annotation
	 *
	 * @param src
	 *            the source object
	 * @param dst
	 *            the target object
	 * @param annotation
	 *            the annotation to indicate which field to copy
	 */
	public static void copyFields(final Object src, final Object dst, final Class<? extends Annotation> annotation) {
		final List<Field> fields1 = ReflectionUtils.getAllFieldsWithAnnotation(src.getClass(), annotation);
		final List<Field> fields2 = ReflectionUtils.getAllFieldsWithAnnotation(dst.getClass(), annotation);
		final Set<String> fieldNames = getFieldNamesSet(fields1, fields2);
		for (final String f : fieldNames) {
			try {
				if (!ReflectionUtils.copyField(src, dst, f)) {
					throw new IllegalStateException(String.format("Cannot copy field '%s.%s'. Missing getter or setter.", src.getClass().getSimpleName(), f));
				}
			} catch (final ReflectiveOperationException ex) {
				throw new IllegalStateException(String.format("Cannot copy field '%s.%s'", src.getClass().getSimpleName(), f), ex);
			}
		}
	}

	/**
	 * Utility routine to paper over array type names (see Field#getTypeName())
	 *
	 * @param type
	 *            the class
	 * @return the string name of the class
	 */
	public static String getClassName(final Class<?> type) {
		if (type.isArray()) {
			try {
				Class<?> cl = type;
				int dimensions = 0;
				while (cl.isArray()) {
					dimensions++;
					cl = cl.getComponentType();
				}
				final StringBuilder sb = new StringBuilder();
				sb.append(cl.getName());
				for (int i = 0; i < dimensions; i++) {
					sb.append("[]");
				}
				return sb.toString();
			} catch (final Throwable e) { // NOSONAR
				// ignore the exception and return default implementation
			}
		}
		return type.getName();
	}

	/**
	 * Creates a new instance of the provided class
	 *
	 * @param objectClass
	 *            the object class
	 * @param <T>
	 *            object type
	 * @return a new instance of <tt>objectClass</tt>
	 * @throws NoSuchMethodException
	 *             thrown if an error occurs
	 * @throws SecurityException
	 *             thrown if an error occurs
	 * @throws InstantiationException
	 *             thrown if an error occurs
	 * @throws IllegalAccessException
	 *             thrown if an error occurs
	 * @throws IllegalArgumentException
	 *             thrown if an error occurs
	 * @throws InvocationTargetException
	 *             thrown if an error occurs
	 */
	public static <T> T newInstance(final Class<T> objectClass) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Constructor<T> constructor;
		constructor = objectClass.getDeclaredConstructor();
		constructor.setAccessible(true);
		final T object = constructor.newInstance();
		return object;
	}

	/**
	 * An utility method to determine proper class loader for a given class.
	 *
	 * @param clazz
	 *            The given class.
	 * @return Class loader.
	 */
	public static ClassLoader getClassLoader(final Class<?> clazz) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = clazz.getClassLoader();
		}
		return loader;
	}
}
