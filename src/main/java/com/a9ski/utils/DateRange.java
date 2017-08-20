package com.a9ski.utils;

import java.util.Date;

/**
 *
 * Date range represents a period between two dates (start/end)
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class DateRange {

	public static final long MILLISECONDS_PER_DAY = 1 * 24 * 60 * 60 * 1000;
	public static final long MILLISECONDS_PER_HOUR = 1 * 60 * 60 * 1000;
	public static final long MILLISECONDS_PER_MINUTE = 1 * 60 * 1000;
	public static final long MILLISECONDS_PER_SECOND = 1 * 1000;

	private static final Date MIN_DATE = new Date(0);
	private static final Date MAX_DATE = new Date(Long.MAX_VALUE);

	private Date start;
	private Date end;

	public DateRange() {
		super();
	}

	public DateRange(final Date start, final Date end) {
		setStart(start);
		setEnd(end);
	}

	/**
	 * Gets the start date
	 *
	 * @return start date
	 */
	public Date getStart() {
		return DateUtils.copy(start);
	}

	/**
	 * Sets the start date
	 *
	 * @param start
	 *            the date
	 */
	public void setStart(final Date start) {
		this.start = DateUtils.copy(start);
	}

	/**
	 * Gets the end date
	 *
	 * @return end date
	 */
	public Date getEnd() {
		return DateUtils.copy(end);
	}

	/**
	 * Sets the end date
	 *
	 * @param end
	 *            the date
	 */
	public void setEnd(final Date end) {
		this.end = DateUtils.copy(end);
	}

	/**
	 * Returns a new normalized DateRange.
	 * <p>
	 * If both start date and end date are set the startDate is the min(startDate, endDate), endDate is the max(startDate, endDate).
	 * <p>
	 * If the startDate is missing, {@link #minValue()} is assumed. If endDate is missing {@link #maxValue()} is assumed
	 *
	 * @return a new normalized DateRange
	 */
	public DateRange normalize() {
		if (start != null && end != null) {
			return new DateRange(min(start, end), max(start, end));
		} else {
			return new DateRange(defaultDate(start, minDate()), defaultDate(end, maxDate()));
		}
	}

	/**
	 * Check if either start or end is set.
	 *
	 * @return false if start and end dates are null
	 */
	public boolean isEmpty() {
		return start == null && end == null;
	}

	/**
	 * Checks if this date range overlaps with provided date range
	 *
	 * @param dateRange
	 *            the date range to be checked for overlapping
	 * @return true if this date range overlaps with provided <tt>dateRange</tt>
	 */
	public boolean intersect(final DateRange dateRange) {
		return DateRange.intersect(this, dateRange);
	}

	/**
	 * Returns true if two periods of time has intersection.
	 *
	 * @param dateRange1
	 *            first period
	 * @param dateRange2
	 *            second period
	 * @return true if two intervals are overlapping
	 */
	public static boolean intersect(final DateRange dateRange1, final DateRange dateRange2) {
		final DateRange d1 = dateRange1.normalize();
		final DateRange d2 = dateRange2.normalize();

		final Date s1 = d1.getStart();
		final Date e1 = d1.getEnd();
		final Date s2 = d2.getStart();
		final Date e2 = d2.getEnd();

		return !e1.before(s2) && !e2.before(s1); // (e1 >= s2) && (e2 >= s1)
	}

	private static Date min(final Date a, final Date b) {
		if (a.before(b)) {
			return a;
		} else {
			return b;
		}
	}

	private static Date max(final Date a, final Date b) {
		if (a.before(b)) {
			return b;
		} else {
			return a;
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

	/**
	 * Returns either d or <tt>defaultValue</tt> if d is null
	 *
	 * @param d
	 *            the date to be retruned
	 * @param defaultDate
	 *            the value returned if <tt>d</tt> is null
	 * @return <tt>d</tt> or <tt>defaultValue</tt> if <tt>d</tt> is null
	 */
	public static Date defaultDate(final Date d, final Date defaultDate) {
		return (d != null ? d : defaultDate);
	}

	/**
	 * Checks if x is contained in this date range.
	 * <p>
	 * If the date range start is null {@link #minDate()} is assumed. If the date range end is null {@link #maxDate()} is assumed
	 *
	 * @param x
	 *            the date to be checked
	 * @return true if x is contained in the date range
	 */
	public boolean contains(final Date x) {
		if (x == null) {
			return false;
		}
		final DateRange dr = normalize();

		return !x.before(dr.getStart()) && !x.after(dr.getEnd());
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DateRange)) {
			return false;
		}
		final DateRange other = (DateRange) obj;
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!end.equals(other.end)) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!start.equals(other.start)) {
			return false;
		}
		return true;
	}

}
