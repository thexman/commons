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
