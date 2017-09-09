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

import static com.a9ski.utils.DateRange.maxDate;
import static com.a9ski.utils.DateRange.minDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class DateRangeTest {
	private final Date d1 = new Date(100L);
	private final Date d2 = new Date(200L);
	private final Date d3 = new Date(300L);
	private final Date d4 = new Date(400L);

	@Test
	public void testNormalize() {
		final Date d1 = new Date(100L);
		final Date d2 = new Date(200L);
		assertEquals(new DateRange(minDate(), maxDate()), new DateRange().normalize());
		assertEquals(new DateRange(d1, maxDate()), new DateRange(d1, null).normalize());
		assertEquals(new DateRange(minDate(), d2), new DateRange(null, d2).normalize());
		assertEquals(new DateRange(d1, d2), new DateRange(d2, d1).normalize());
		assertEquals(new DateRange(d1, d2).hashCode(), new DateRange(d2, d1).normalize().hashCode());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(new DateRange().isEmpty());
		assertTrue(new DateRange(null, null).isEmpty());
		assertFalse(new DateRange(new Date(), null).isEmpty());
		assertFalse(new DateRange(null, new Date()).isEmpty());
		assertFalse(new DateRange(new Date(), new Date()).isEmpty());
	}

	@Test
	public void testIntersectDateRange() {
		assertFalse(new DateRange(d2, d1).intersect(new DateRange(d4, d3)));
		assertFalse(new DateRange(d3, d4).intersect(new DateRange(d1, d2)));
		assertFalse(new DateRange(null, d2).intersect(new DateRange(d3, d4)));
		assertFalse(new DateRange(null, d2).intersect(new DateRange(d3, null)));

		assertTrue(new DateRange(d3, d1).intersect(new DateRange(d4, d2)));
		assertTrue(new DateRange(d2, d4).intersect(new DateRange(d1, d3)));
		assertTrue(new DateRange(d4, d1).intersect(new DateRange(d2, d3)));
		assertTrue(new DateRange(d3, d2).intersect(new DateRange(d1, d4)));
		assertTrue(new DateRange(d1, d2).intersect(new DateRange(d2, d3)));
		assertTrue(new DateRange(null, d2).intersect(new DateRange(null, d4)));
		assertTrue(new DateRange(d1, null).intersect(new DateRange(d3, null)));
	}

	@Test
	public void testDefaultDate() {
		final Date d1 = new Date(0L);
		final Date d2 = new Date(1L);

		assertTrue(d1 == new DateRange().defaultValue(d1, d2));
		assertTrue(d2 == new DateRange().defaultValue(null, d2));
	}

	@Test
	public void testContains() {
		assertTrue(new DateRange(d1, d3).contains(d2));
		assertTrue(new DateRange(d3, d1).contains(d1));
		assertTrue(new DateRange(d3, d1).contains(d3));
		assertTrue(new DateRange(null, d4).contains(d3));
		assertTrue(new DateRange(d2, null).contains(d3));
		assertTrue(new DateRange().contains(d3));

		assertFalse(new DateRange(d3, d1).contains(d4));
		assertFalse(new DateRange(null, d3).contains(d4));
		assertFalse(new DateRange(d2, null).contains(d1));
		assertFalse(new DateRange(d2, null).contains(null));
	}

	@Test
	public void testDuration() {
		final Date start = new Date(1483221600000L); // DateUtils.date(2017, 01, 01, 0, 0, 0, 0);
		final Date end = new Date(1483307999999L); // DateUtils.date(2017, 01, 01, 23, 59, 59, 999);
		final DateRange dr = new DateRange(start, end);
		assertEquals(1, dr.durationInDays());
		assertEquals(24, dr.durationInHours());
		assertEquals(24 * 60, dr.durationInMinutes());
		assertEquals(24 * 60 * 60, dr.durationInSeconds());
		assertEquals(24 * 60 * 60 * 1000, dr.durationInMilliseconds());
	}

	@Test
	public void testLongRange() throws Exception {
		final Range<Long> lr = Range.newLongRange(1L, 42L);
		assertTrue(lr.contains(20L));
		assertFalse(lr.contains(0L));
		assertFalse(lr.contains(43L));
	}

	@Test
	public void testStringRange() throws Exception {
		final Range<String> r = Range.newRange("a", "z");
		assertTrue(r.contains("qwerty"));
		assertFalse(r.contains("1"));
	}

}
