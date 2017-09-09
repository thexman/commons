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

import org.apache.commons.collections4.comparators.ComparableComparator;

/**
 * Represents a range type with start and end value
 *
 * @author Kiril Arabadzhiyski
 *
 * @param <T>
 *            value type
 */
public class Range<T> {
	protected T start;
	protected T end;

	protected final Comparator<T> cmp;
	protected final T minValue;
	protected final T maxValue;

	/**
	 * Creates a new range with provided <tt>T</tt>'s absolute minValue, maxValue and comparator
	 *
	 * @param minValue
	 *            the <tt>T</tt> absolute min value
	 * @param maxValue
	 *            the <tt>T</tt> absolute max value
	 * @param cmp
	 *            the comparator
	 */
	public Range(final T minValue, final T maxValue, final Comparator<T> cmp) {
		this.cmp = cmp;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public Range(final Comparator<T> cmp, final T start, final T end, final T minValue, final T maxValue) {
		this(minValue, maxValue, cmp);
		setStart(start);
		setEnd(end);
	}

	/**
	 * Gets the range start value
	 *
	 * @return start value
	 */
	public T getStart() {
		return start;
	}

	/**
	 * Sets the range start value
	 *
	 * @param start
	 *            the range start value
	 */
	public void setStart(final T start) {
		this.start = start;
	}

	/**
	 * Gets the range end value
	 *
	 * @return end value
	 */
	public T getEnd() {
		return end;
	}

	/**
	 * Sets range end value
	 *
	 * @param end
	 *            the end value
	 */
	public void setEnd(final T end) {
		this.end = end;
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
	 * Returns a new normalized Range.
	 * <p>
	 * If both <tt>start</tt> value and <tt>end</tt> value are set the <tt>start</tt> is the min(start, end=), <tt>end</tt> is the max(start, end).
	 * <p>
	 * If the <tt>start</tt> is missing, {@link #getMinValue()} is assumed. If <tt>end</tt> is missing {@link #getMaxValue()} is assumed
	 *
	 * @return a new normalized Range
	 */
	public Range<T> normalize() {
		if (start != null && end != null) {
			return new Range<T>(cmp, min(getStart(), getEnd()), max(getStart(), getEnd()), getMinValue(), getMaxValue());
		} else {
			return new Range<T>(cmp, defaultValue(getStart(), getMinValue()), defaultValue(getEnd(), getMaxValue()), getMinValue(), getMaxValue());
		}
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
	protected T defaultValue(final T value, final T defaultValue) {
		return (value != null ? value : defaultValue);
	}

	public T getMinValue() {
		return minValue;
	}

	public T getMaxValue() {
		return maxValue;
	}

	protected T min(final T a, final T b) {
		if (cmp.compare(a, b) < 0) {
			return a;
		} else {
			return b;
		}
	}

	protected T max(final T a, final T b) {
		if (cmp.compare(a, b) < 0) {
			return b;
		} else {
			return a;
		}
	}

	/**
	 * Checks if this range intersect with provided range
	 *
	 * @param range
	 *            the range to be checked for overlapping
	 * @return true if this range overlaps with provided <tt>range</tt>
	 */
	public boolean intersect(final Range<T> range) {
		return Range.intersect(this, range, cmp);
	}

	/**
	 * Returns true if two ranges has intersection.
	 *
	 * @param range1
	 *            first range
	 * @param range2
	 *            second range
	 * @param cmp
	 *            value comparator
	 * @return true if two ranges are overlapping
	 * @param <T>
	 *            type of the range
	 */
	public static <T> boolean intersect(final Range<T> range1, final Range<T> range2, final Comparator<T> cmp) {
		if (range1 != null && range2 != null) {
			final Range<T> r1 = range1.normalize();
			final Range<T> r2 = range2.normalize();

			final T s1 = r1.getStart();
			final T e1 = r1.getEnd();
			final T s2 = r2.getStart();
			final T e2 = r2.getEnd();

			final boolean e1LessThans2 = cmp.compare(e1, s2) < 0;
			final boolean e2LessThans1 = cmp.compare(e2, s1) < 0;
			return !e1LessThans2 && !e2LessThans1; // (e1 >= s2) && (e2 >= s1)
		} else {
			return false;
		}
	}

	/**
	 * Checks if x is contained in this range.
	 * <p>
	 * If the range <tt>start</tt> is null {@link #getMinValue()} is assumed. If the range <tt>end</tt> is null {@link #getMaxValue()} is assumed
	 *
	 * @param x
	 *            the value to be checked of being contained in the range
	 * @return true if x is contained in the range
	 */
	public boolean contains(final T x) {
		if (x == null) {
			return false;
		}
		final Range<T> r = normalize();

		final boolean beforeStart = cmp.compare(x, r.getStart()) < 0;
		final boolean afterEnd = cmp.compare(x, r.getEnd()) > 0;
		return !beforeStart && !afterEnd;
	}

	public static Range<Long> newLongRange() {
		return new Range<Long>(Long.MIN_VALUE, Long.MAX_VALUE, new ComparableComparator<Long>());
	}

	public static Range<Long> newLongRange(final Long start, final Long end) {
		return new Range<Long>(new ComparableComparator<Long>(), start, end, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public static Range<Double> newDoubleRange() {
		return new Range<Double>(Double.MIN_VALUE, Double.MAX_VALUE, new ComparableComparator<Double>());
	}

	public static Range<Double> newDoubleRange(final Double start, final Double end) {
		return new Range<Double>(new ComparableComparator<Double>(), start, end, Double.MIN_VALUE, Double.MAX_VALUE);
	}

	public static Range<Integer> newIntegerRange() {
		return new Range<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, new ComparableComparator<Integer>());
	}

	public static Range<Integer> newIntegerRange(final Integer start, final Integer end) {
		return new Range<Integer>(new ComparableComparator<Integer>(), start, end, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static <C extends Comparable<C>> Range<C> newRange(final C minValue, final C maxValue) {
		return new Range<C>(minValue, maxValue, new ComparableComparator<C>());
	}

	public static <C extends Comparable<C>> Range<C> newRange(final C start, final C end, final C minValue, final C maxValue) {
		return new Range<C>(new ComparableComparator<C>(), start, end, minValue, maxValue);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Range))
			return false;
		@SuppressWarnings("unchecked")
		final Range<T> other = (Range<T>) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
}
