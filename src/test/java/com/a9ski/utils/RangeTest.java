package com.a9ski.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.Test;

public class RangeTest {

	@Test
	public void testRange() {
		final Range<Long> r = new Range<>(-42L, 42L, new ComparableComparator<Long>());
		assertEquals(Long.valueOf(-42L), r.getMinValue());
		assertEquals(Long.valueOf(42L), r.getMaxValue());

		try {
			new Range<>(null, 42L, new ComparableComparator<Long>());
			fail("Expected to throw IllegalArgumentException");
		} catch (IllegalArgumentException ex) {
			// expected exception
		}

		try {
			new Range<>(-42L, null, new ComparableComparator<Long>());
			fail("Expected to throw IllegalArgumentException");
		} catch (IllegalArgumentException ex) {
			// expected exception
		}

		try {
			new Range<>(-42L, 42L, null);
			fail("Expected to throw IllegalArgumentException");
		} catch (IllegalArgumentException ex) {
			// expected exception
		}
	}

}
