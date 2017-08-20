package com.a9ski.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum utilities
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class EnumUtils extends org.apache.commons.lang3.EnumUtils {
	protected EnumUtils() {
		super();
	}

	/**
	 * Parses a value to enum
	 *
	 * @param value
	 *            value to be parsed
	 * @param enumClass
	 *            the enum class beign parsed
	 * @param defaultValue
	 *            the default value to be returned if the object cannot be parsed to enum value
	 * @return the parsed enum value
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> E parseEnum(final Object value, final Class<E> enumClass, final E defaultValue) {
		final boolean empty = (value == null || ((value instanceof String) && (StringUtils.isBlank((String) value))));

		if (empty) {
			return defaultValue;
		}
		if (value instanceof String) {
			String name = (String) value;
			if (name.contains(".")) {
				name = name.substring(name.lastIndexOf(".") + 1);
			}
			try {
				return Enum.valueOf(enumClass, name);
			} catch (final IllegalArgumentException ex) {
				return defaultValue;
			}
		} else if (enumClass.isAssignableFrom(value.getClass())) {
			return (E) value;
		} else {
			throw new IllegalArgumentException(String.format("Invalid enum value %s of type %s. Expected value of type %s", value, value.getClass(), enumClass));
		}
	}
}
