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
