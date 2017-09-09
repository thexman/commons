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

import java.util.Locale;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Locale Utils
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class LocaleUtils extends org.apache.commons.lang3.LocaleUtils {

	/**
	 * Returns the locale display name (US name) or returns empty string if the locale is null
	 *
	 * @param locale
	 *            the locale
	 * @param displayNameLocale
	 *            The locale for which to retrieve the display name.
	 * @return the display name or empty string
	 */
	public static String getDisplayName(final Locale locale, final Locale displayNameLocale) {
		final String s;
		if (locale != null) {
			s = locale.getDisplayName(ObjectUtils.defaultIfNull(displayNameLocale, Locale.US));
		} else {
			s = "";
		}
		return s;
	}

	/**
	 * Returns the locale display name (US name) or returns empty string if the locale is null
	 *
	 * @param locale
	 *            the locale
	 * @return the display name or empty string
	 */
	public static String getDisplayName(final Locale locale) {
		return getDisplayName(locale, Locale.US);
	}

}
