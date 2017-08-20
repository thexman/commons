package com.a9ski.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class ReflectionUtilsTest {

	@SuppressWarnings("unused")
	private class BaseBookObj {
		private int baseField;

		private int basePrivateField;

		@Deprecated
		private String author;

		public String getAuthor() {
			return author;
		}

		public void setAuthor(final String author) {
			this.author = author;
		}

		public void setTitle(final String title) {
		}

		public void getTitle() {
		}

		public int getBaseField() {
			return baseField;
		}

		private void read(final long from, final int size) {

		}
	}

	private class ChildBookObj extends BaseBookObj {
		private int childField;

		public int getChildField() {
			return childField;
		}
	}

	@Test
	public void testConstructSetterName() {
		assertEquals("setTitle", ReflectionUtils.constructSetterName("title"));
		assertEquals("setX", ReflectionUtils.constructSetterName("X"));
		assertEquals("", ReflectionUtils.constructSetterName(""));
		assertNull(ReflectionUtils.constructSetterName(null));
	}

	@Test
	public void testConstructGetterName() {
		assertEquals("getTitle", ReflectionUtils.constructGetterName("title"));
		assertEquals("getX", ReflectionUtils.constructGetterName("X"));
		assertEquals("", ReflectionUtils.constructGetterName(""));
		assertNull(ReflectionUtils.constructGetterName(null));
	}

	@Test
	public void testGetGetter() throws Exception {
		@SuppressWarnings("unused")
		final Object o = new Object() {
			String getTitle(final String x) {
				return null;
			}

			String getTitle() {
				return null;
			}
		};

		final Method getter = ReflectionUtils.getGetter(o.getClass(), "title");
		assertNotNull(getter);
		assertEquals("getTitle", getter.getName());
		assertEquals(0, getter.getParameterTypes().length);
	}

	@Test
	public void testGetSetter() throws Exception {
		@SuppressWarnings("unused")
		final Object o = new Object() {
			void setTitle() {
			}

			void setTitle(final String title) {
			}
		};

		final Method setter = ReflectionUtils.getSetter(o.getClass(), "title");
		assertNotNull(setter);
		assertEquals("setTitle", setter.getName());
		assertEquals(1, setter.getParameterTypes().length);
	}

	@Test
	public void testGetMethod() throws Exception {
		@SuppressWarnings("unused")
		final Object o = new Object() {
			void method() {
			}

			void methoD(final String m) {
			}

			void methoD(final String m, final String n) {
			}
		};

		// ignore case, parameters matching
		final Method m1 = ReflectionUtils.getMethod(o.getClass(), "method", true, 1);
		assertNotNull(m1);
		assertEquals("methoD", m1.getName());
		assertEquals(1, m1.getParameterTypes().length);

		// don't ignore case, parameters matching
		final Method m2 = ReflectionUtils.getMethod(o.getClass(), "methoD", false, 2);
		assertNotNull(m2);
		assertEquals("methoD", m2.getName());
		assertEquals(2, m2.getParameterTypes().length);

		// don't ignore case, no parameters matching
		final Method m3 = ReflectionUtils.getMethod(o.getClass(), "methoD", false, -1);
		assertNotNull(m3);
		assertEquals("methoD", m3.getName());
	}

	@Test
	public void testGetCollectionGenericTypes() throws Exception {
		@SuppressWarnings("unused")
		final Object o = new Object() {
			List<String> s;
			List<Long> l = new ArrayList<Long>();
		};
		final Field s = o.getClass().getDeclaredField("s");
		final Field l = o.getClass().getDeclaredField("l");
		assertEquals(String.class, ReflectionUtils.getCollectionGenericType(s.getGenericType()));
		assertEquals(Long.class, ReflectionUtils.getCollectionGenericType(l.getGenericType()));
	}

	@Test
	public void testGetAllMethods() throws Exception {
		final ChildBookObj o = new ChildBookObj();
		final List<Method> methods = ReflectionUtils.getAllMethods(o.getClass());
		assertFalse(methods.isEmpty());
		assertNotNull(findByName(methods, "getTitle"));
		assertNotNull(findByName(methods, "setTitle"));
	}

	@Test
	public void testGetAllFields() throws Exception {
		final ChildBookObj o = new ChildBookObj();
		final List<Field> fields = ReflectionUtils.getAllFields(o.getClass());
		assertFalse(fields.isEmpty());
		assertNotNull(findByName(fields, "childField"));
		assertNotNull(findByName(fields, "baseField"));
	}

	@Test
	public void testChildObjectGetterAndSetter() throws Exception {
		final ChildBookObj o = new ChildBookObj();

		final Method setter = ReflectionUtils.getSetter(o.getClass(), "title");
		assertNotNull(setter);
		assertEquals("setTitle", setter.getName());
		assertEquals(1, setter.getParameterTypes().length);

		final Method getter = ReflectionUtils.getGetter(o.getClass(), "title");
		assertNotNull(getter);
		assertEquals("getTitle", getter.getName());
		assertEquals(0, getter.getParameterTypes().length);
	}

	private <T extends Member> T findByName(final List<T> members, final String name) {
		for (final T m : members) {
			if (m != null && StringUtils.equals(m.getName(), name)) {
				return m;
			}
		}
		return null;
	}

	final Map<String, Integer> map = new TreeMap<>();

	@Test
	public void testGetGenericTypes() throws Exception {
		final List<Class<?>> params = (ReflectionUtils.getGenericTypes(ReflectionUtils.getField(getClass(), "map").getGenericType()));
		assertEquals(2, params.size());
		assertEquals(String.class, params.get(0));
		assertEquals(Integer.class, params.get(1));
	}

	@Test
	public void testSetFieldValue() throws Exception {
		final ChildBookObj c = new ChildBookObj();

		assertEquals(0, c.getBaseField());
		ReflectionUtils.setFieldValue(c, "baseField", 42);
		assertEquals(42, c.getBaseField());

		assertEquals(0, c.getChildField());
		ReflectionUtils.setFieldValue(c, "childField", 42);
		assertEquals(42, c.getChildField());
	}

	@Test
	public void testGetSetFieldValue() throws Exception {
		final BaseBookObj baseObj = new BaseBookObj();
		ReflectionUtils.setFieldValueSafe(baseObj, "basePrivateField", 42);
		assertEquals(42, ReflectionUtils.getFieldValue(baseObj, "basePrivateField"));
	}

	@Test
	public void testGetFieldWithAnnotation() throws Exception {
		final List<Field> fields = ReflectionUtils.getAllFieldsWithAnnotation(BaseBookObj.class, Deprecated.class);

		assertEquals(1, fields.size());
		assertEquals("author", fields.get(0).getName());
	}

	@Test
	public void testGetFieldType() throws Exception {
		assertEquals(int.class, ReflectionUtils.getFieldType(BaseBookObj.class, "basePrivateField"));
	}

	@Test
	public void testGetMethodByType() throws Exception {
		final Method m = ReflectionUtils.getMethod(BaseBookObj.class, "read", false, Arrays.asList(long.class, int.class));
		assertNotNull(m);
		assertEquals("read", m.getName());
	}

	@Test
	public void testGetClassName() throws Exception {
		final long[] t = new long[] { 42 };
		assertEquals(String.class.getName(), ReflectionUtils.getClassName(String.class));
		assertEquals("long[]", ReflectionUtils.getClassName(t.getClass()));
	}

	@Test
	public void testCopyField() throws Exception {
		final BaseBookObj o1 = new BaseBookObj();
		final BaseBookObj o2 = new BaseBookObj();
		ReflectionUtils.setFieldValueViaSetter(o1, "author", "George R. R. Martin");
		assertNull(ReflectionUtils.getFieldValue(o2, "author"));
		ReflectionUtils.copyFields(o1, o2, Deprecated.class);
		assertEquals("George R. R. Martin", ReflectionUtils.getFieldValue(o2, "author"));
	}
}
