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
	 * @param <E>
	 *            enum type
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
