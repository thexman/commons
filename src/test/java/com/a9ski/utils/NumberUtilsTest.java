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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void testDefaultValue() {
		assertEquals(Long.valueOf(1L), NumberUtils.defaultValue(1L, 0L));
		assertEquals(Long.valueOf(0L), NumberUtils.defaultValue(null, 0L));
	}

	@Test
	public void testDefaultLong() {
		assertEquals(1L, NumberUtils.defaultLong(1L, 0L));
		assertEquals(0L, NumberUtils.defaultLong(null, 0L));
	}

	@Test
	public void testDefaultInt() {
		assertEquals(1, NumberUtils.defaultInt(1, 0));
		assertEquals(0, NumberUtils.defaultInt(null, 0));
	}

	@Test
	public void testDefaultDouble() {
		assertEquals(1, NumberUtils.defaultDouble(1, 0D), 0.00000001D);
		assertEquals(0, NumberUtils.defaultDouble(null, 0D), 0.00000001D);
	}

	@Test
	public void testIsNull() {
		assertTrue(NumberUtils.isNull(null));
		assertFalse(NumberUtils.isNull(1L));
	}

	@Test
	public void testAddLongLong() {
		assertEquals(Long.valueOf(3L), NumberUtils.add(1L, 2L));
		assertEquals(Long.valueOf(3L), NumberUtils.add(3L, null));
		assertEquals(Long.valueOf(3L), NumberUtils.add(null, 3L));
		assertNull(NumberUtils.add((Long) null, null));
	}

	@Test
	public void testAddDoubleDouble() {
		assertEquals(Double.valueOf(3D), NumberUtils.add(1D, 2D));
		assertEquals(Double.valueOf(3D), NumberUtils.add(3D, null));
		assertEquals(Double.valueOf(3D), NumberUtils.add(null, 3D));
		assertNull(NumberUtils.add((Double) null, null));
	}

	@Test
	public void testAddIntegerInteger() {
		assertEquals(Integer.valueOf(3), NumberUtils.add(1, 2));
		assertEquals(Integer.valueOf(3), NumberUtils.add(3, null));
		assertEquals(Integer.valueOf(3), NumberUtils.add(null, 3));
		assertNull(NumberUtils.add((Integer) null, null));
	}

	@Test
	public void testSubtractLongLong() {
		assertEquals(Long.valueOf(3L), NumberUtils.subtract(4L, 1L));
		assertEquals(Long.valueOf(3L), NumberUtils.subtract(3L, null));
		assertEquals(Long.valueOf(3L), NumberUtils.subtract(null, -3L));
		assertNull(NumberUtils.subtract((Long) null, null));
	}

	@Test
	public void testSubtractDoubleDouble() {
		assertEquals(Double.valueOf(3D), NumberUtils.subtract(4D, 1D));
		assertEquals(Double.valueOf(3D), NumberUtils.subtract(3D, null));
		assertEquals(Double.valueOf(3D), NumberUtils.subtract(null, -3D));
		assertNull(NumberUtils.subtract((Double) null, null));
	}

	@Test
	public void testSubtractIntegerInteger() {
		assertEquals(Integer.valueOf(3), NumberUtils.subtract(4, 1));
		assertEquals(Integer.valueOf(3), NumberUtils.subtract(3, null));
		assertEquals(Integer.valueOf(3), NumberUtils.subtract(null, -3));
		assertNull(NumberUtils.subtract((Integer) null, null));
	}

	@Test
	public void testSignLong() {
		assertEquals(-1, NumberUtils.sign(-10L));
		assertEquals(0, NumberUtils.sign(0L));
		assertEquals(1, NumberUtils.sign(10L));
	}

	@Test
	public void testSignInt() {
		assertEquals(-1, NumberUtils.sign(-10));
		assertEquals(0, NumberUtils.sign(0));
		assertEquals(1, NumberUtils.sign(10));
	}

	@Test
	public void testSignDouble() {
		assertEquals(-1, NumberUtils.sign(-10D));
		assertEquals(0, NumberUtils.sign(0D));
		assertEquals(1, NumberUtils.sign(10D));
	}

	@Test
	public void testMax() {
		assertEquals(Long.valueOf(2), NumberUtils.max(1L, 2L));
		assertEquals(Long.valueOf(2), NumberUtils.max(2L, 1L));
		assertEquals(Long.valueOf(2), NumberUtils.max(null, 2L));
		assertEquals(Long.valueOf(2), NumberUtils.max(2L, null));
		assertNull(NumberUtils.max((Long) null, null));
	}

	@Test
	public void testMin() {
		assertEquals(Long.valueOf(2), NumberUtils.min(3L, 2L));
		assertEquals(Long.valueOf(2), NumberUtils.min(2L, 3L));
		assertEquals(Long.valueOf(2), NumberUtils.min(null, 2L));
		assertEquals(Long.valueOf(2), NumberUtils.min(2L, null));
		assertNull(NumberUtils.min((Long) null, null));
	}

	@Test
	public void testAverage() {
		assertEquals(4D, NumberUtils.average(Arrays.asList(2, 6, 4, 4, null)), 0.0000000001D);
		assertEquals(4D, NumberUtils.average(Arrays.asList(2D, 6D, 4D, 4D, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)), 0.0000000001D);
		assertEquals(0D, NumberUtils.average(null), 0.00001D);
	}

	@Test
	public void testToLongNumber() {
		assertEquals(Long.valueOf(1L), NumberUtils.toLong(Double.valueOf(1D)));
		assertNull(NumberUtils.toLong((Number) null));

		assertEquals(Long.valueOf(1L), NumberUtils.parseLong("1", null));
		assertEquals(Long.valueOf(1L), NumberUtils.parseLong("2X", Long.valueOf(1L)));
		assertNull(NumberUtils.parseLong("aaaa", null));
	}

	@Test
	public void testToLongPrimitiveDouble() {
		assertEquals(1L, NumberUtils.toLongPrimitive(1D));
	}

	@Test
	public void testToLongPrimitiveInt() {
		assertEquals(1L, NumberUtils.toLongPrimitive(1));
	}

	@Test
	public void testToLongPrimitiveNumberLong() {
		assertEquals(1L, NumberUtils.toLongPrimitive(Double.valueOf(1D), 2));
		assertEquals(1L, NumberUtils.toLongPrimitive((Number) null, 1));
	}

	@Test
	public void testToIntPrimitiveDouble() {
		assertEquals(1, NumberUtils.toIntPrimitive(1D));
	}

	@Test
	public void testToIntPrimitiveLong() {
		assertEquals(1, NumberUtils.toIntPrimitive(1L));
	}

	@Test
	public void testToIntPrimitiveNumberInt() {
		assertEquals(1, NumberUtils.toIntPrimitive(Double.valueOf(1D), 2));
		assertEquals(1, NumberUtils.toIntPrimitive(null, 1));
	}

	@Test
	public void testToDoubleNumber() {
		assertEquals(Double.valueOf(1L), NumberUtils.toDouble(Long.valueOf(1)));
		assertNull(NumberUtils.toDouble((Number) null));

		assertEquals(Double.valueOf(1L), NumberUtils.parseDouble("1", null));
		assertEquals(Double.valueOf(1L), NumberUtils.parseDouble("2X", Double.valueOf(1L)));
		assertNull(NumberUtils.parseDouble("aaaa", null));
	}

	@Test
	public void testToDoublePrimitiveLong() {
		assertEquals(1D, NumberUtils.toDoublePrimitive(1L), 0.000001D);
	}

	@Test
	public void testToDoublePrimitiveInt() {
		assertEquals(1D, NumberUtils.toDoublePrimitive(1), 0.000001D);
	}

	@Test
	public void testToDoublePrimitiveNumberDouble() {
		assertEquals(1, NumberUtils.toDoublePrimitive(Double.valueOf(1D), 2D), 0.000001D);
		assertEquals(1, NumberUtils.toDoublePrimitive(null, 1D), 0.000001D);
	}

	@Test
	public void testIsValid() {
		assertFalse(NumberUtils.isValid((Double) null));
		assertFalse(NumberUtils.isValid(Double.NEGATIVE_INFINITY));
		assertFalse(NumberUtils.isValid(Double.POSITIVE_INFINITY));
		assertFalse(NumberUtils.isValid(Double.NaN));
		assertTrue(NumberUtils.isValid(42D));
	}

	@Test
	public void testIsInRange() {
		assertTrue(NumberUtils.isInRange(1, 1, 42));
		assertTrue(NumberUtils.isInRange(1, 42, 1));
		assertTrue(NumberUtils.isInRange(42, 1, 42));
		assertTrue(NumberUtils.isInRange(42, 42, 1));
		assertTrue(NumberUtils.isInRange(24, 1, 42));
		assertTrue(NumberUtils.isInRange(24, 42, 1));
		assertFalse(NumberUtils.isInRange(0, 1, 42));
		assertFalse(NumberUtils.isInRange(0, 42, 1));
		assertFalse(NumberUtils.isInRange(43, 1, 42));
		assertFalse(NumberUtils.isInRange(43, 42, 1));
		assertFalse(NumberUtils.isInRange(null, 1, 42));

		assertTrue(NumberUtils.isInRange(1, 1, null));
		assertTrue(NumberUtils.isInRange(42, 1, null));
		assertFalse(NumberUtils.isInRange(0, 1, null));

		assertTrue(NumberUtils.isInRange(1, null, 42));
		assertTrue(NumberUtils.isInRange(42, null, 42));
		assertFalse(NumberUtils.isInRange(43, null, 42));
	}

	@Test
	public void testIsNotInRange() {
		assertFalse(NumberUtils.isNotInRange(1, 1, 42));
		assertFalse(NumberUtils.isNotInRange(1, 42, 1));
		assertFalse(NumberUtils.isNotInRange(42, 1, 42));
		assertFalse(NumberUtils.isNotInRange(42, 42, 1));
		assertFalse(NumberUtils.isNotInRange(24, 1, 42));
		assertFalse(NumberUtils.isNotInRange(24, 42, 1));
		assertTrue(NumberUtils.isNotInRange(0, 1, 42));
		assertTrue(NumberUtils.isNotInRange(0, 42, 1));
		assertTrue(NumberUtils.isNotInRange(43, 1, 42));
		assertTrue(NumberUtils.isNotInRange(43, 42, 1));
		assertTrue(NumberUtils.isNotInRange(null, 1, 42));

		assertFalse(NumberUtils.isNotInRange(1, 1, null));
		assertFalse(NumberUtils.isNotInRange(42, 1, null));
		assertTrue(NumberUtils.isNotInRange(0, 1, 42));

		assertFalse(NumberUtils.isNotInRange(1, null, 42));
		assertFalse(NumberUtils.isNotInRange(42, null, 42));
		assertTrue(NumberUtils.isNotInRange(43, null, 42));
	}

	@Test
	public void testEqualsDoubleDouble() {
		assertTrue(NumberUtils.equals(1D, 1D));
		assertFalse(NumberUtils.equals(1D, 2D));
	}

	@Test
	public void testEqualsNN() {
		assertTrue(NumberUtils.equals(1L, 1L));
		assertTrue(NumberUtils.equals(null, null));
		assertFalse(NumberUtils.equals(1L, 2L));
		assertFalse(NumberUtils.equals(null, 2L));
		assertFalse(NumberUtils.equals(1L, null));
	}

	@Test
	public void testNotEqualsDoubleDouble() {
		assertFalse(NumberUtils.notEquals(1D, 1D));
		assertTrue(NumberUtils.notEquals(1D, 2D));
		assertTrue(NumberUtils.notEquals(null, 2L));
		assertTrue(NumberUtils.notEquals(1L, null));
	}

	@Test
	public void testNotEqualsNN() {
		assertFalse(NumberUtils.notEquals(Long.valueOf(1), Long.valueOf(1L)));
		assertTrue(NumberUtils.notEquals(Long.valueOf(1), Long.valueOf(2)));
	}
}
