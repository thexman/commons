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
import static org.junit.Assert.fail;

import org.junit.Test;

public class EnumUtilsTest {
	private static enum TestEnum {
		A, B, C;
	}

	@Test
	public void testParseEnum() {
		assertEquals(TestEnum.C, EnumUtils.parseEnum(null, TestEnum.class, TestEnum.C));
		assertEquals(TestEnum.C, EnumUtils.parseEnum("", TestEnum.class, TestEnum.C));
		assertEquals(TestEnum.B, EnumUtils.parseEnum(TestEnum.B, TestEnum.class, TestEnum.C));
		assertEquals(TestEnum.B, EnumUtils.parseEnum("TestEnum.B", TestEnum.class, TestEnum.C));
		assertEquals(TestEnum.B, EnumUtils.parseEnum("B", TestEnum.class, TestEnum.C));
		assertEquals(TestEnum.C, EnumUtils.parseEnum("X", TestEnum.class, TestEnum.C));
		try {
			EnumUtils.parseEnum(new Object(), TestEnum.class, TestEnum.C);
			fail("Expectged to throw IllegalArgumentException");
		} catch (final IllegalArgumentException ex) {
			// expected
		}
	}

}
