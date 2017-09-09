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

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

public class DateUtilsTest {

	private final TimeZone tz = TimeZone.getTimeZone("Europe/Sofia");

	@Test
	public void testGetDayStart() throws Exception {
		final Date d = DateUtils.date(2017, 8, 21, 20, 38, 42, 23, tz);
		final Date start = DateUtils.getDayStart(d, tz.getID());
		final Calendar cal = Calendar.getInstance(tz, Locale.US);
		cal.setTime(start);
		assertEquals(2017, cal.get(Calendar.YEAR));
		assertEquals(Calendar.AUGUST, cal.get(Calendar.MONTH));
		assertEquals(21, cal.get(Calendar.DATE));
		assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, cal.get(Calendar.MINUTE));
		assertEquals(0, cal.get(Calendar.SECOND));
		assertEquals(0, cal.get(Calendar.MILLISECOND));
	}

	@Test
	public void testGetDayEnd() throws Exception {
		final Date d = DateUtils.date(2017, 8, 21, 20, 38, 42, 23, tz);
		final Date end = DateUtils.getDayEnd(d, tz.getID());
		final Calendar cal = Calendar.getInstance(tz, Locale.US);
		cal.setTime(end);
		assertEquals(2017, cal.get(Calendar.YEAR));
		assertEquals(Calendar.AUGUST, cal.get(Calendar.MONTH));
		assertEquals(21, cal.get(Calendar.DATE));
		assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(59, cal.get(Calendar.MINUTE));
		assertEquals(59, cal.get(Calendar.SECOND));
		assertEquals(999, cal.get(Calendar.MILLISECOND));
	}

	@Test
	public void testDayRange() throws Exception {
		final Date d = new Date();
		final DateRange dr = DateUtils.getDayRange(d, tz.getID());
		assertEquals(DateUtils.getDayStart(d, tz), dr.getStart());
		assertEquals(DateUtils.getDayEnd(d, tz), dr.getEnd());
	}

	@Test
	public void testIsoFormat() throws Exception {
		final Date d = DateUtils.date(2017, 8, 1, 7, 2, 3, 4, tz);
		assertEquals("2017-08-01T07:02:03.004", DateUtils.formatIsoDate(d, tz));
	}

	@Test
	public void testMondayFromWeekNumber() throws Exception {
		final Date d = DateUtils.date(2017, 8, 21, 0, 0, 0, 0, tz);
		assertEquals(d, DateUtils.getMondayFromWeekNumberEU(34, 2017, tz));
	}

	@Test
	public void testCurrentYear() throws Exception {
		final int year = Calendar.getInstance(tz).get(Calendar.YEAR);
		assertEquals(year, DateUtils.getCurrentYear(tz));
	}

	@Test
	public void testFridayFromWeekNumber() throws Exception {
		final Date d = DateUtils.date(2017, 8, 25, 0, 0, 0, 0, tz);
		assertEquals(d, DateUtils.getFridayFromWeekNumberEU(34, 2017, tz));
	}

	@Test
	public void testMinMax() throws Exception {
		final Date d1 = new Date(100);
		final Date d2 = new Date(200);

		assertEquals(d1, DateUtils.min(d1, d2));
		assertEquals(d2, DateUtils.max(d1, d2));
		assertEquals(d1, DateUtils.min(null, d1));
		assertEquals(d1, DateUtils.max(d1, null));
		assertNull(DateUtils.min(null, null));
		assertNull(DateUtils.max(null, null));
	}

	@Test
	public void testWeekNumber() throws Exception {
		assertEquals(34, DateUtils.getWeekNumberEU(DateUtils.date(2017, 8, 24, 0, 0, 0, 0, tz), tz));
		assertEquals(4, DateUtils.getWeekOfMonthEU(DateUtils.date(2017, 8, 24, 0, 0, 0, 0, tz), tz));
	}

	@Test
	public void testMaxWeekOfYear() throws Exception {
		assertEquals(52, DateUtils.getMaxWeekOfYear(2017, tz));
	}

	@Test
	public void testWeekStartEnd() throws Exception {
		final Date d = DateUtils.date(2017, 8, 22, 0, 0, 0, 0, tz);
		assertEquals(DateUtils.date(2017, 8, 21, 0, 0, 0, 0, tz), DateUtils.getWeekStart(d, tz, Locale.GERMANY));
		assertEquals(DateUtils.date(2017, 8, 20, 0, 0, 0, 0, tz), DateUtils.getWeekStart(d, tz, Locale.US));

		assertEquals(DateUtils.date(2017, 8, 27, 23, 59, 59, 999, tz), DateUtils.getWeekEnd(d, tz, Locale.GERMANY));
		assertEquals(DateUtils.date(2017, 8, 26, 23, 59, 59, 999, tz), DateUtils.getWeekEnd(d, tz, Locale.US));
	}

	@Test
	public void testWeekRange() throws Exception {
		final Date d = DateUtils.date(2017, 8, 22, 0, 0, 0, 0, tz);
		final DateRange us = new DateRange(DateUtils.date(2017, 8, 20, 0, 0, 0, 0, tz), DateUtils.date(2017, 8, 26, 23, 59, 59, 999, tz));
		assertEquals(us, DateUtils.getWeekRange(d, tz, Locale.US));

		final DateRange de = new DateRange(DateUtils.date(2017, 8, 21, 0, 0, 0, 0, tz), DateUtils.date(2017, 8, 27, 23, 59, 59, 999, tz));
		assertEquals(de, DateUtils.getWeekRange(d, tz, Locale.GERMANY));
	}

	@Test
	public void testWeekRangeBeforeAfter() throws Exception {
		final Date d = DateUtils.date(2017, 8, 22, 0, 0, 0, 0, tz);
		final DateRange us = new DateRange(DateUtils.date(2017, 8, 6, 0, 0, 0, 0, tz), DateUtils.date(2017, 9, 16, 23, 59, 59, 999, tz));
		assertEquals(us, DateUtils.getWeeksRange(d, 2, 3, tz, Locale.US));

		final DateRange de = new DateRange(DateUtils.date(2017, 8, 7, 0, 0, 0, 0, tz), DateUtils.date(2017, 9, 17, 23, 59, 59, 999, tz));
		assertEquals(de, DateUtils.getWeeksRange(d, 2, 3, tz, Locale.GERMANY));

		assertEquals(DateUtils.getWeekRange(d, tz, Locale.US), DateUtils.getWeeksRange(d, 0, 0, tz, Locale.US));
		assertEquals(DateUtils.getWeekRange(d, tz, Locale.GERMANY), DateUtils.getWeeksRange(d, 0, 0, tz, Locale.GERMANY));
	}

	@Test
	public void testSameDay() throws Exception {
		assertTrue(DateUtils.isSameDay(DateUtils.date(2017, 8, 22, 1, 0, 0, 0, tz), DateUtils.date(2017, 8, 22, 23, 0, 0, 0, tz)));
	}

	@Test
	public void testIsToday() throws Exception {
		assertTrue(DateUtils.isToday(new Date(), TimeZone.getDefault()));
	}

	@Test
	public void testEquals() throws Exception {
		final Date d = DateUtils.date(2017, 8, 22, 1, 0, 0, 0, tz);
		assertTrue(DateUtils.equals(d, DateUtils.copy(d)));
		assertTrue(DateUtils.equals(d, DateUtils.getDate(d.getTime())));
		assertFalse(DateUtils.equals(d, DateUtils.getDate(d.getTime() + 1)));
		assertFalse(DateUtils.equals(d, null));
		assertFalse(DateUtils.equals(null, d));
		assertTrue(DateUtils.equals(null, null));
	}

	@Test
	public void testNotEquals() throws Exception {
		final Date d = DateUtils.date(2017, 8, 22, 1, 0, 0, 0, tz);
		assertFalse(DateUtils.notEquals(d, DateUtils.copy(d)));
		assertFalse(DateUtils.notEquals(d, DateUtils.getDate(d.getTime())));
		assertTrue(DateUtils.notEquals(d, DateUtils.getDate(d.getTime() + 1)));
		assertTrue(DateUtils.notEquals(d, null));
		assertTrue(DateUtils.notEquals(null, d));
		assertFalse(DateUtils.notEquals(null, null));
	}

	@Test
	public void testDelta() throws Exception {
		final Date d = new Date();
		assertEquals(24 * 3600 * 1000 - 1, DateUtils.delta(DateUtils.getDayEnd(d, tz), DateUtils.getDayStart(d, tz)));
	}

	@Test
	public void testConvertToTimeZoneDateString() {
		final Calendar ieCal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Dublin"), Locale.US);
		ieCal.set(2017, Calendar.AUGUST, 21, 8, 00);
		ieCal.set(Calendar.SECOND, 0);
		ieCal.set(Calendar.MILLISECOND, 0);
		final Date d = ieCal.getTime();

		final Calendar bgCal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Sofia"), Locale.US);
		bgCal.setTime(d);

		System.out.println(DateUtils.getDayStart(d, "Europe/Dublin"));
		System.out.println(DateUtils.getDayStart(d, "Europe/Sofia"));

		// System.out.println(d);

	}

}
