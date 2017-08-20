package com.a9ski.utils;

import org.apache.commons.lang3.StringUtils;

public class EnumUtils extends org.apache.commons.lang3.EnumUtils {
	protected EnumUtils() {
		super();
	}

	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> E parseEnum(final Object o, final Class<E> enumClass, final E defaultValue) {
		final boolean empty = (o == null || ((o instanceof String) && (StringUtils.isBlank((String) o))));

		if (empty) {
			return defaultValue;
		}
		if (o instanceof String) {
			String name = (String) o;
			if (name.contains(".")) {
				name = name.substring(name.lastIndexOf(".") + 1);
			}
			try {
				return Enum.valueOf(enumClass, name);
			} catch (final IllegalArgumentException ex) {
				return defaultValue;
			}
		} else if (enumClass.isAssignableFrom(o.getClass())) {
			return (E) o;
		} else {
			throw new IllegalArgumentException(String.format("Invalid enum value %s of type %s. Expected value of type %s", o, o.getClass(), enumClass));
		}
	}
}
