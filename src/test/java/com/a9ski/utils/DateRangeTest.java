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

		assertTrue(d1 == DateRange.defaultDate(d1, d2));
		assertTrue(d2 == DateRange.defaultDate(null, d2));
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

}
