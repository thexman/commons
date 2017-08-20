package com.a9ski.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class NullUtilsTest {

	@Test
	public void testCheckNotNull() {
		NullUtils.checkNotNull("", "sss");
		try {
			NullUtils.checkNotNull(null, "msg");
			fail("expected to throw exception");
		} catch (final IllegalArgumentException ex) {
			assertEquals("msg", ex.getMessage());
		}
	}

}
