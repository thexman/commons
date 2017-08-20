package com.a9ski.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class dealing with dates and date ranges
 *
 * @author Kiril Arabadzhiyski
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	/**
	 * ISO 8601:2004 date format &lt;date&gt;T&lt;time&gt;
	 * <p>
	 * &lt;date&gt; := YYYY-MM-DD
	 * <p>
	 * &lt;time&gt; := hh:mm:ss.sss
	 */
	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/**
	 * Returns "the epoch", namely January 1, 1970, 00:00:00 GMT.
	 *
	 * @return the minimal date range
	 */
	public static Date minDate() {
		return DateRange.minDate();
	}

	/**
	 * Returns the max date that can be stored in Date object
	 *
	 * @return the max date that can be stored in Date object
	 */
	public static Date maxDate() {
		return DateRange.maxDate();
	}

	/**
	 * Gets calendar for given time zone set to given date
	 *
	 * @param timeZone
	 *            the time zone
	 * @param date
	 *            the date
	 * @return calendar set to the date in given time zone
	 */
	public static Calendar getCalendar(final TimeZone timeZone, final Date date) {
		final Calendar userCal = Calendar.getInstance(timeZone);
		userCal.setTime(date);
		return userCal;
	}

	/**
	 * Returns the date with time part set to 00:00:00.000 in given time zone
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return the date with part set to 00:00:00.000 in given time zone
	 */
	public static Date getDayStart(final Date date, final TimeZone timeZone) {
		if (date != null) {
			final Calendar userCal = getCalendar(timeZone, date);
			userCal.set(Calendar.HOUR_OF_DAY, 0);
			userCal.set(Calendar.MINUTE, 0);
			userCal.set(Calendar.SECOND, 0);
			userCal.set(Calendar.MILLISECOND, 0);

			return userCal.getTime();
		}
		return null;
	}

	/**
	 * Returns the date with time part set to 00:00:00.000 in given time zone
	 *
	 * @param date
	 *            the date
	 * @param timeZoneId
	 *            the time zone id
	 * @return the date with part set to 00:00:00.000 in given time zone
	 */
	public static Date getDayStart(final Date date, final String timeZoneId) {
		final TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
		return getDayStart(date, timeZone);
	}

	/**
	 * Returns the date with time part set to 23:59:58.999 in given time zone. It represents the date end (inclusive value of the the last milliseconds of the day)
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return day end inclusive. It is the date with time part set to 23:59:58.999 in given time zone.
	 */
	public static Date getDayEnd(final Date date, final TimeZone timeZone) {
		if (date != null) {
			final Calendar cal = getCalendar(timeZone, date);

			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);

			return cal.getTime();
		}
		return null;
	}

	/**
	 * Returns the date with time part set to 23:59:58.999 in given time zone. It represents the date end (inclusive value of the the last milliseconds of the day)
	 *
	 * @param date
	 *            the date
	 * @param timeZoneId
	 *            the time zone
	 * @return day end inclusive. It is the date with time part set to 23:59:58.999 in given time zone.
	 */
	public static Date getDayEnd(final Date date, final String timeZoneId) {
		final TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
		return getDayEnd(date, timeZone);
	}

	/**
	 * Returns date range with day start ({@link #getDayStart(Date, TimeZone)} and day end {@link #getDayEnd(Date, TimeZone)} in given time zone.
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return day range
	 */
	public static DateRange getDayRange(final Date date, final TimeZone timeZone) {
		return new DateRange(getDayStart(date, timeZone), getDayEnd(date, timeZone));
	}

	/**
	 * Returns date range with day start ({@link #getDayStart(Date, TimeZone)} and day end {@link #getDayEnd(Date, TimeZone)} in given time zone.
	 *
	 * @param date
	 *            the date
	 * @param timeZoneId
	 *            the time zone
	 * @return day range
	 */
	public static DateRange getDayRange(final Date date, final String timeZoneId) {
		return getDayRange(date, TimeZone.getTimeZone(timeZoneId));
	}

	/**
	 * Formats a date according to a {@link SimpleDateFormat} pattern in given time zone
	 *
	 * @param pattern
	 *            the {@link SimpleDateFormat} pattern
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone of the output string value
	 * @return the formatted date
	 * @throws IllegalArgumentException
	 *             if the pattern is invalid
	 */
	public static String formatDate(final String pattern, final Date date, final TimeZone timeZone) throws IllegalArgumentException {
		if (date != null && pattern != null) {
			final DateFormat format = new SimpleDateFormat(pattern);
			if (timeZone != null) {
				format.setTimeZone(timeZone);
			}
			return format.format(date);
		} else {
			return "";
		}
	}

	/**
	 * Format dates according to {@link #ISO_DATE_FORMAT} (ISO 8601:2004) in given time zone
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return the formatted date
	 * @throws IllegalArgumentException
	 *             if the pattern specified pattern is invalid
	 */
	public static String formatIsoDate(final Date date, final TimeZone timeZone) throws IllegalArgumentException {
		return formatDate(ISO_DATE_FORMAT, date, timeZone);
	}

	/**
	 * Gets week's Monday (European Union calendar). The time is set to 0:00:00.000
	 *
	 * @param week
	 *            the week number (value between 1 and 53)
	 * @param year
	 *            the year
	 * @param timeZone
	 *            the time zone
	 * @return the date of the Monday
	 */
	public static Date getMondayFromWeekNumberEU(final int week, final int year, final TimeZone timeZone) {
		final Calendar cal = getWeekCalendarEU(timeZone);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		return cal.getTime();
	}

	/**
	 * Gets a European Union calendar used for calculating the week numbers
	 *
	 * @param timeZone
	 *            the time zone
	 * @return calendar used for calculating the week numbers
	 */
	public static Calendar getWeekCalendarEU(final TimeZone timeZone) {
		final Calendar cal = new GregorianCalendar(timeZone);
		cal.setMinimalDaysInFirstWeek(4);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * Gets the week's Friday (European Union calendar). The time is set to 0:00:00.000
	 *
	 * @param week
	 *            the week number (value between 1 and 53)
	 * @param year
	 *            the year
	 * @param timeZone
	 *            the time zone
	 * @return the week's Friday.
	 */
	public static Date getFridayFromWeekNumberEU(final int week, final int year, final TimeZone timeZone) {
		return add(getMondayFromWeekNumberEU(week, year, timeZone), Calendar.DATE, 4);
	}

	/**
	 * Adds dates to given date without changing the time.
	 *
	 * @param d
	 *            the origin date
	 * @param field
	 *            field to be changed @see {@link Calendar#add(int, int)}
	 * @param amount
	 *            amount of time be added. Use minus sign to substract amount.
	 * @return new date with offset <code>amount</code> from the original date.
	 */
	public static Date add(final Date d, final int field, final int amount) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * Gets the current year
	 *
	 * @param timeZone
	 *            the time zone
	 * @return the current year
	 */
	public static int getCurrentYear(final TimeZone timeZone) {
		return getYear(new Date(), timeZone);
	}

	/**
	 * Gets the year from date
	 *
	 * @param date
	 *            the date that whose year will be returned
	 * @param timeZone
	 *            the time zone
	 * @return the year that contains the given date
	 */
	public static int getYear(final Date date, final TimeZone timeZone) {
		final Calendar cal = new GregorianCalendar(timeZone);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Returns a new Date for provided year, month, day, hours, minutes, seconds, milliseconds
	 *
	 * @param year
	 *            the year
	 * @param month
	 *            the month 1-12
	 * @param day
	 *            the day 1-31
	 * @param hours
	 *            hours of the day 0-23
	 * @param minutes
	 *            the minutes 0-59
	 * @param seconds
	 *            the seconds 0-50
	 * @param ms
	 *            the milliseconds 0-999
	 * @param timeZone
	 *            the time zone
	 *
	 * @return a new date
	 */
	public static Date date(final int year, final int month, final int day, final int hours, final int minutes, final int seconds, final int ms, final TimeZone timeZone) {
		final Calendar cal = Calendar.getInstance(timeZone);

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);

		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.MILLISECOND, ms);

		return cal.getTime();
	}

	/**
	 * Returns the smaller value of two dates. If one is null the other is returned
	 *
	 * @param a
	 *            first date
	 * @param b
	 *            second date
	 * @return the smaller date of <tt>a</tt> and <tt>b</tt>. If one is null the other is returned
	 */
	public static Date min(final Date a, final Date b) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		if (a.before(b)) {
			return a;
		} else {
			return b;
		}
	}

	/**
	 * Returns the largest value of two dates. If one is null the other is returned
	 *
	 * @param a
	 *            first date
	 * @param b
	 *            second date
	 * @return the largest date of <tt>a</tt> and <tt>b</tt>. If one is null the other is returned
	 */
	public static Date max(final Date a, final Date b) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		if (a.before(b)) {
			return b;
		} else {
			return a;
		}
	}

	/**
	 * Converts a date to time stamp
	 *
	 * @param d
	 *            the date
	 * @return a time stamp representing the date
	 */
	public static Timestamp toTimestamp(final Date d) {
		if (d != null) {
			return new Timestamp(d.getTime());
		} else {
			return null;
		}
	}

	/**
	 * Converts a times tamp to a date
	 *
	 * @param ts
	 *            the time stamp
	 * @return the date representing the time stamp
	 */
	public static Date toDate(final Timestamp ts) {
		if (ts != null) {
			return new Date(ts.getTime());
		} else {
			return null;
		}
	}

	/**
	 * Gets the week number withing the year using European Union calendar
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return the EU week number
	 */
	public static int getWeekNumberEU(final Date date, final TimeZone timeZone) {
		final Calendar cal = getWeekCalendarEU(timeZone);
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Gets the week number within the month using European Union calendar
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return the EU week number
	 */
	public static int getWeekOfMonthEU(final Date date, final TimeZone timeZone) {
		final Calendar cal = getWeekCalendarEU(timeZone);
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * Get the actual maximum of the week of year.
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return the actual maximum of the week of year.
	 */
	public static int getMaxWeekOfYear(final Date date, final TimeZone timeZone) {
		final Calendar cal = getWeekCalendarEU(timeZone);
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Get the actual maximum of the week of year.
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return the actual maximum of the week of year.
	 */
	public static int getMaxWeekOfYear(final int year, final TimeZone timeZone) {
		return getMaxWeekOfYear(date(year, 2, 1, 0, 0, 0, 0, timeZone), timeZone);
	}

	/**
	 * Creates a copy of the time stamp
	 *
	 * @param ts
	 *            the time stamp
	 * @return a copy
	 */
	public static Timestamp copy(final Timestamp ts) {
		if (ts != null) {
			return new Timestamp(ts.getTime());
		} else {
			return null;
		}
	}

	/**
	 * Creates a copy of the date
	 *
	 * @param d
	 *            the date
	 * @return a copy
	 */
	public static Date copy(final Date d) {
		if (d != null) {
			return new Date(d.getTime());
		} else {
			return null;
		}
	}

	/**
	 * Gets the week start (MONDAY or SUNDAY, etc.) according to the locale calendar
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @param locale
	 *            the locale
	 * @return the week start (MONDAY or SUNDAY depending on the locale)
	 */
	public static Date getWeekStart(final Date date, final TimeZone timeZone, final Locale locale) {
		final Calendar cal = Calendar.getInstance(timeZone, locale);
		cal.setTime(getDayStart(date, timeZone));
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	}

	/**
	 * Gets the week end (SUNDAY or SATURDAY, etc.) according to the locale calendar
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @param locale
	 *            the locale
	 * @return the week start (SUNDAY or SATURDAY depending on the locale)
	 */
	public static Date getWeekEnd(final Date date, final TimeZone timeZone, final Locale locale) {
		return getDayEnd(add(getWeekStart(date, timeZone, locale), Calendar.DATE, 6), timeZone);

	}

	/**
	 * Gets a DateRange with week start and week end
	 *
	 * @param date
	 *            the date within the week
	 * @param timeZone
	 *            the time zone
	 * @param locale
	 *            the locale
	 * @return DateRange with week start and week end
	 */
	public static DateRange getWeekRange(final Date date, final TimeZone timeZone, final Locale locale) {
		return new DateRange(getWeekStart(date, timeZone, locale), getWeekEnd(date, timeZone, locale));
	}

	/**
	 * Gets a DateRange with start date set to the first day of given number of weeks, and DateRange end date set to the last day of given number of weeks.
	 * <p>
	 * In other words
	 * <p>
	 * the DateRange start date = getWeekStart( date - weeksBefore );
	 * <p>
	 * the DateRange end date = getWeekEnd( date + weeksAfter );
	 *
	 * @param date
	 *            the date inside the date range
	 * @param weeksBefore
	 *            number of weeks before the <tt>date</tt>
	 * @param weeksAfter
	 *            number of weeks after the <tt>date</tt>
	 * @param timeZone
	 *            the time zone
	 * @param locale
	 *            the locale
	 * @return DateRange with start date set to the first day of given number of weeks, and DateRange end date set to the last day of given number of weeks.
	 */
	public static DateRange getWeeksRange(final Date date, final int weeksBefore, final int weeksAfter, final TimeZone timeZone, final Locale locale) {
		final Date startWeek;
		if (weeksBefore > 0) {
			startWeek = add(date, Calendar.WEEK_OF_MONTH, -weeksBefore);
		} else {
			startWeek = date;
		}

		final Date endWeek;
		if (weeksAfter > 0) {
			endWeek = add(date, Calendar.WEEK_OF_MONTH, weeksAfter);
		} else {
			endWeek = date;
		}
		return new DateRange(getWeekStart(startWeek, timeZone, locale), getWeekEnd(endWeek, timeZone, locale));
	}

	/**
	 * Check if two dates are in the same day in given time zone
	 *
	 * @param date1
	 *            the first date
	 * @param date2
	 *            the first date
	 * @param timeZone
	 *            the time zone
	 * @return true if two dates are in the same day in given time zone
	 */
	public static boolean isSameDay(final Date date1, final Date date2, final TimeZone timeZone) {
		final Calendar cal1 = new GregorianCalendar(timeZone);
		cal1.setTime(date1);
		final Calendar cal2 = new GregorianCalendar(timeZone);
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * Check if the date is today day in given time zone
	 *
	 * @param date
	 *            the date to be checked
	 * @param timeZone
	 *            the time zone
	 * @return true if the date is today day in given time zone
	 */
	public static boolean isToday(final Date date, final TimeZone timeZone) {
		return isSameDay(date, new Date(), timeZone);
	}

	/**
	 * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT (EPOCH) represented by provided <tt>Date</tt> object. Returns null if the date is null
	 *
	 * @param d
	 *            the date
	 * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT (EPOCH) represented by the date or null if the date is null
	 */
	public static Long getTime(final Date d) {
		if (d != null) {
			return d.getTime();
		} else {
			return null;
		}
	}

	/**
	 * Checks if two dates are equals
	 *
	 * @param d1
	 *            the first date
	 * @param d2
	 *            the second date
	 * @return true if two dates are equals
	 */
	public static boolean equals(final Date d1, final Date d2) { // NOSONAR there is no naming collision
		return d1 == d2 || (d1 != null && d2 != null && d1.equals(d2)); // NOSONAR we check the reference and nulls
	}

	/**
	 * Checks if two dates are NOT equals
	 *
	 * @param d1
	 *            the first date
	 * @param d2
	 *            the second date
	 * @return true if two dates are NOT equals
	 */
	public static boolean notEquals(final Date d1, final Date d2) {
		return !equals(d1, d2);
	}

	/**
	 * Gets a date from provided milliseconds since since January 1, 1970, 00:00:00 GMT (EPOCH)
	 *
	 * @param time
	 *            milliseconds since since January 1, 1970, 00:00:00 GMT (EPOCH)
	 * @return a date object
	 */
	public static Date getDate(final Long time) {
		if (time != null) {
			return new Date(time);
		}
		return null;
	}

	/**
	 * Adds a duration to date. Returns the a copy of the original date if duration is null. Returns null if the date is null
	 *
	 * @param date
	 *            the date
	 * @param duration
	 *            the duration
	 * @return new date with added duration. If duration is null returns a copy of the original date. If the date is null, then null is returned
	 */
	public static Date addDuration(final Date date, final Duration duration) {
		if (duration != null) {
			return addMills(date, duration.toMillis());
		} else {
			return copy(date);
		}
	}

	/**
	 * Adds milliseconds to a date. If the date is null, returns null
	 *
	 * @param date
	 *            the date
	 * @param mills
	 *            milliseconds to dad
	 * @return a new date with added milliseconds or null
	 */
	public static Date addMills(final Date date, final long mills) {
		if (date != null) {
			return new Date(date.getTime() + mills);
		}
		return null;
	}

	/**
	 * Returns delta (in milliseconds) between <tt>a</tt> and <tt>b</tt>. Returns 0 if one of the dates is null
	 *
	 * @param a
	 *            date
	 * @param b
	 *            date
	 * @return <tt>a - b</tt> or 0 if a or b is null
	 */
	public static long delta(final Date a, final Date b) {
		if (b != null && a != null) {
			return a.getTime() - b.getTime();
		} else {
			return 0;
		}
	}

	/**
	 * Creates a cron expression for given date
	 *
	 * @param date
	 *            the date
	 * @param timeZone
	 *            the time zone
	 * @return string representing cron expression
	 */
	public static String toCron(final Date date, final TimeZone timeZone) {
		/*
		 * Seconds 0-59,-* / Minutes 0-59,-* / Hours 0-23,-* / Day of month 1-31,-*? / L W Month 1-12,JAN-DEC,-* / Day of week 1-7,SUN-SAT,-*? / L # Year empty,1970-2099,-*
		 */
		final Calendar cal = getCalendar(timeZone, date);
		final StringBuilder sb = new StringBuilder();
		sb.append(cal.get(Calendar.SECOND)).append(" ");
		sb.append(cal.get(Calendar.MINUTE)).append(" ");
		sb.append(cal.get(Calendar.HOUR_OF_DAY)).append(" ");
		sb.append(cal.get(Calendar.DAY_OF_MONTH)).append(" ");
		sb.append(cal.get(Calendar.MONTH) + 1).append(" ");
		sb.append("? ");
		sb.append(cal.get(Calendar.YEAR));
		return sb.toString();
	}

	/**
	 * Returns a default value if the object passed is {@code null}.
	 *
	 * @param value
	 *            the value to test, may be {@code null}
	 * @param defaultValue
	 *            the default value to return, may be {@code null}
	 * @return value if it is not {@code null}, defaultValue otherwise
	 */
	protected Date defaultDate(final Date value, final Date defaultValue) {
		return (value != null ? value : defaultValue);
	}

}
