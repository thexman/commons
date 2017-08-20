package com.a9ski.utils;

/**
 *
 * Long range represents a boundary (start/end)
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class LongRange {
	private Long start;
	private Long end;

	/**
	 * Creates a new long range
	 */
	public LongRange() {
		super();
	}

	/**
	 * Creates a new long range
	 *
	 * @param start
	 *            the start value
	 * @param end
	 *            the end value
	 */
	public LongRange(final Long start, final Long end) {
		super();
		this.start = start;
		this.end = end;
	}

	/**
	 * Gets the start value
	 *
	 * @return the start value
	 */
	public Long getStart() {
		return start;
	}

	/**
	 * Sets the start value
	 *
	 * @param start
	 *            the start value
	 */
	public void setStart(final Long start) {
		this.start = start;
	}

	/**
	 * Gets the end value
	 *
	 * @return the end value
	 */
	public Long getEnd() {
		return end;
	}

	/**
	 * Sets the end value
	 *
	 * @param end
	 *            the end value
	 */
	public void setEnd(final Long end) {
		this.end = end;
	}

	/**
	 * Check if either start or end is set.
	 *
	 * @return false if start and end values are null
	 */

	public boolean isEmpty() {
		return start == null && end == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("LongRange [start=%s, end=%s]", start, end);
	}
}
