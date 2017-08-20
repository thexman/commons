package com.a9ski.utils;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class LocaleUtilsTest {
	@Test
	public void testGetDisplayNameLocaleLocale() {
		assertEquals(Locale.GERMANY.getDisplayName(Locale.GERMANY), LocaleUtils.getDisplayName(Locale.GERMANY, Locale.GERMANY));
		assertEquals(Locale.GERMANY.getDisplayName(Locale.US), LocaleUtils.getDisplayName(Locale.GERMANY, null));
		assertEquals("", LocaleUtils.getDisplayName(null, null));
	}

	@Test
	public void testGetDisplayNameLocale() {
		assertEquals(Locale.GERMANY.getDisplayName(), LocaleUtils.getDisplayName(Locale.GERMANY));
		assertEquals("", LocaleUtils.getDisplayName(null));
	}

}
