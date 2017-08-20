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
