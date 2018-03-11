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

import java.util.Comparator;
import java.util.Date;

import org.apache.commons.collections4.comparators.ComparableComparator;

/**
 *
 * Date range represents a period between two dates (start/end)
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class DateRange extends Range<Date> {

	public static final long MILLISECONDS_PER_DAY = 1 * 24 * 60 * 60 * 1000;
	public static final long MILLISECONDS_PER_HOUR = 1 * 60 * 60 * 1000;
	public static final long MILLISECONDS_PER_MINUTE = 1 * 60 * 1000;
	public static final long MILLISECONDS_PER_SECOND = 1 * 1000;

	private static final Date MIN_DATE = new Date(0);
	private static final Date MAX_DATE = new Date(Long.MAX_VALUE);

	/**
	 * Creates a new object with minDate = {@link #MIN_DATE} and maxDate = {@link #MAX_DATE}
	 */
	public DateRange() {
		super(MIN_DATE, MAX_DATE, new ComparableComparator<Date>());
	}

	/**
	 * Creates a new object with minDate = {@link #MIN_DATE} and maxDate = {@link #MAX_DATE} and provided <tt>start</tt> and <tt>end</tt> dates
	 * 
	 * @param start
	 *            the start date
	 * @param end
	 *            the end date
	 */
	public DateRange(final Date start, final Date end) {
		this(start, end, MIN_DATE, MAX_DATE);
	}

	/**
	 * Creates a new object with provided <tt>start</tt>, <tt>end</tt>, <tt>start</tt>, <tt>end</tt> dates
	 * 
	 * @param start
	 *            the start date
	 * @param end
	 *            the end date
	 * @param minDate
	 *            the absolute min date
	 * @param maxDate
	 *            the absolute max date
	 */
	public DateRange(final Date start, final Date end, Date minDate, Date maxDate) {
		this(new ComparableComparator<Date>(), start, end, minDate, maxDate);
	}

	/**
	 * Creates a new object with provided <tt>start</tt>, <tt>end</tt>, <tt>start</tt>, <tt>end</tt> dates and comparator
	 * 
	 * @param cmp
	 *            date comparator
	 * @param start
	 *            the start date
	 * @param end
	 *            the end date
	 * @param minDate
	 *            the absolute min date
	 * @param maxDate
	 *            the absolute max date
	 */
	public DateRange(final Comparator<Date> cmp, final Date start, final Date end, Date minDate, Date maxDate) {
		super(cmp, start, end, minDate, maxDate);
	}

	/**
	 * Gets the start date
	 *
	 * @return start date
	 */
	@Override
	public Date getStart() {
		return DateUtils.copy(start);
	}

	/**
	 * Sets the start date
	 *
	 * @param start
	 *            the date
	 */
	@Override
	public void setStart(final Date start) {
		this.start = DateUtils.copy(start);
	}

	/**
	 * Gets the end date
	 *
	 * @return end date
	 */
	@Override
	public Date getEnd() {
		return DateUtils.copy(end);
	}

	/**
	 * Sets the end date
	 *
	 * @param end
	 *            the date
	 */
	@Override
	public void setEnd(final Date end) {
		this.end = DateUtils.copy(end);
	}

	/**
	 * Returns a new normalized DateRange.
	 * <p>
	 * If both start date and end date are set the startDate is the min(startDate, endDate), endDate is the max(startDate, endDate).
	 * <p>
	 * If the startDate is missing, {@link DateRange#getMinValue()} is assumed. If endDate is missing {@link DateRange#getMaxValue()} is assumed
	 *
	 * @return a new normalized DateRange
	 */
	@Override
	public DateRange normalize() {
		if (start != null && end != null) {
			return new DateRange(min(start, end), max(start, end));
		} else {
			return new DateRange(defaultValue(start, minDate()), defaultValue(end, maxDate()));
		}
	}

	/**
	 * Returns "the epoch", namely January 1, 1970, 00:00:00 GMT.
	 *
	 * @return the minimal date range
	 */
	public static Date minDate() {
		return copy(MIN_DATE);
	}

	/**
	 * Returns the max date that can be stored in Date object
	 *
	 * @return the max date that can be stored in Date object
	 */
	public static Date maxDate() {
		return copy(MAX_DATE);
	}

	/**
	 * Duplicates a date object
	 *
	 * @param d
	 *            date to copied
	 * @return a copy of the date
	 */
	public static Date copy(final Date d) {
		if (d != null) {
			return new Date(d.getTime());
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DateRange [start=%s, end=%s]", start, end);
	}

	/**
	 * Gets the duration in full days
	 *
	 * @return the duration in full days
	 */
	public long durationInDays() {
		return calculateDuration(MILLISECONDS_PER_DAY);
	}

	/**
	 * Gets the duration in full hours
	 *
	 * @return the duration in full hours
	 */
	public long durationInHours() {
		return calculateDuration(MILLISECONDS_PER_HOUR);
	}

	/**
	 * Gets the duration in full minutes
	 *
	 * @return the duration in full minutes
	 */
	public long durationInMinutes() {
		return calculateDuration(MILLISECONDS_PER_MINUTE);
	}

	/**
	 * Gets the duration in full seconds
	 *
	 * @return the duration in full seconds
	 */
	public long durationInSeconds() {
		return calculateDuration(MILLISECONDS_PER_SECOND);
	}

	/**
	 * Gets the duration in milliseconds
	 *
	 * @return the duration in milliseconds
	 */
	public long durationInMilliseconds() {
		return calculateDuration(1L);
	}

	/**
	 * Calculates the duration for given period
	 *
	 * @param period
	 *            the period ( milliseconds per day, milliseconds per hour, etc.)
	 * @return the duration
	 */
	public long calculateDuration(final long period) {
		if (start == null || end == null) {
			return 0;
		} else {
			final DateRange r = normalize();
			final long sign = (!start.after(end) ? 1 : -1);
			return (sign * (r.end.getTime() + 1 - r.start.getTime()) / period);
		}
	}
}
