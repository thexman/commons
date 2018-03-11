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

import java.util.Collection;

/**
 * Utility for numbers
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

	/**
	 * Returns a default value if the object passed is {@code null}.
	 *
	 * @param value
	 *            the value to test, may be {@code null}
	 * @param defaultValue
	 *            the default value to return, may be {@code null}
	 * @param <N>
	 *            number type
	 * @return value if it is not {@code null}, defaultValue otherwise
	 */
	public static <N extends Number> N defaultValue(final N value, final N defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * Returns a default value if the object passed is {@code null}.
	 *
	 * @param value
	 *            the value to test, may be {@code null}
	 * @param defaultValue
	 *            the default value to return
	 * @return value if it is not {@code null}, defaultValue otherwise
	 */

	public static long defaultLong(final Number value, final long defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value.longValue();
	}

	/**
	 * Returns a default value if the object passed is {@code null}.
	 *
	 * @param value
	 *            the value to test, may be {@code null}
	 * @param defaultValue
	 *            the default value to return
	 * @return value if it is not {@code null}, defaultValue otherwise
	 */

	public static int defaultInt(final Number value, final int defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value.intValue();
	}

	/**
	 * Returns a default value if the object passed is {@code null}.
	 *
	 * @param value
	 *            the value to test, may be {@code null}
	 * @param defaultValue
	 *            the default value to return
	 * @return value if it is not {@code null}, defaultValue otherwise
	 */

	public static double defaultDouble(final Number value, final double defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value.doubleValue();
	}

	/**
	 * Check if number is null
	 *
	 * @param number
	 *            the number to be checked
	 * @return true if the number is null
	 */
	public static boolean isNull(final Number number) {
		return number == null;
	}

	/**
	 * Sums two longs. If either of the numbers is null, then the other one is returned. If both are null returns null
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return a + b
	 */
	public static Long add(final Long a, final Long b) {
		if (a != null && b != null) {
			return a + b;
		} else if (a != null) {
			return a;
		} else {
			return b;
		}
	}

	/**
	 * Sums two longs. If either of the numbers is null, then the other one is returned. If both are null returns null
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return a + b
	 */
	public static Double add(final Double a, final Double b) {
		if (a != null && b != null) {
			return a + b;
		} else if (a != null) {
			return a;
		} else {
			return b;
		}
	}

	/**
	 * Sums two longs. If either of the numbers is null, then the other one is returned. If both are null returns null
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return a + b
	 */

	public static Integer add(final Integer a, final Integer b) {
		if (a != null && b != null) {
			return a + b;
		} else if (a != null) {
			return a;
		} else {
			return b;
		}
	}

	/**
	 * Subtract two numbers. if <tt>a</tt> is null the result is -b. If both are null returns null
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return a - b
	 */
	public static Long subtract(final Long a, final Long b) {
		if (a != null && b != null) {
			return a - b;
		} else if (a != null) {
			return a;
		} else if (b != null) {
			return -b;
		} else {
			return null;
		}
	}

	/**
	 * Subtract two numbers. if <tt>a</tt> is null the result is -b. If both are null returns null
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return a - b
	 */
	public static Double subtract(final Double a, final Double b) {
		if (a != null && b != null) {
			return a - b;
		} else if (a != null) {
			return a;
		} else if (b != null) {
			return -b;
		} else {
			return null;
		}
	}

	/**
	 * Subtract two numbers. if <tt>a</tt> is null the result is -b. If both are null returns null
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return a - b
	 */
	public static Integer subtract(final Integer a, final Integer b) {
		if (a != null && b != null) {
			return a - b;
		} else if (a != null) {
			return a;
		} else if (b != null) {
			return -b;
		} else {
			return null;
		}
	}

	/**
	 * Returns the sign of the number. The return value is either -1, 0 or 1
	 *
	 * @param x
	 *            the number
	 * @return One of the values -1, 0, 1
	 */
	public static int sign(final long x) {
		if (x > 0) {
			return 1;
		} else if (x < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Returns the sign of the number. The return value is either -1, 0 or 1
	 *
	 * @param x
	 *            the number
	 * @return One of the values -1, 0, 1
	 */
	public static int sign(final int x) {
		if (x > 0) {
			return 1;
		} else if (x < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Returns the sign of the number. The return value is either -1, 0 or 1
	 *
	 * @param x
	 *            the number
	 * @return One of the values -1, 0, 1
	 */
	public static int sign(final double x) {
		return sign(Double.valueOf(x).compareTo(Double.valueOf(0)));
	}

	/**
	 * Returns the max value of two number. If one of them is null, the other one is returned. Returns null if both of them are null.
	 *
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * @param <N>
	 *            number type
	 * @return the max value
	 */
	public static <N extends Number & Comparable<N>> N max(final N a, final N b) {
		if (a == null) {
			return b;
		} else if (b == null) {
			return a;
		} else {
			return (a.compareTo(b) > 0 ? a : b);
		}
	}

	/**
	 * Returns the min value of two number. If one of them is null, the other one is returned. Returns null if both of them are null.
	 *
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * @param <N>
	 *            number type
	 * @return the min value
	 */
	public static <N extends Number & Comparable<N>> N min(final N a, final N b) {
		if (a == null) {
			return b;
		} else if (b == null) {
			return a;
		} else {
			return (a.compareTo(b) < 0 ? a : b);
		}
	}

	/**
	 * Calculates the average of collection of numbers
	 *
	 * @param values
	 *            the collection
	 * @param <N>
	 *            number type
	 * @return the average: (values[0] + values[1] + ... values[N]) / N
	 */
	public static <N extends Number> double average(final Collection<N> values) {
		if (values != null) {
			//@formatter:off
			return values.stream()
				.filter(val -> val != null)
				.filter(val -> isValid(val.doubleValue()))
				.mapToDouble(val -> val.doubleValue())
				.average()
				.orElse(0D);
			//@formatter:on
		} else {
			return 0;
		}
	}

	/**
	 * Converts string to {@link Long}. If the value cannot be parsed, returns defaultValue
	 *
	 * @param str
	 *            string to be converted
	 * @param defaultValue
	 *            the defaultValue
	 * @return {@link Long} parsed value or default value if string cannot be parsed
	 */
	public static Long parseLong(final String str, Long defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		try {
			return Long.parseLong(str);
		} catch (final NumberFormatException nfe) {
			return defaultValue;
		}
	}

	/**
	 * Converts number to {@link Long}. If the value is null, returns null
	 *
	 * @param n
	 *            number value
	 * @return {@link Long} value or null
	 */
	public static Long toLong(final Number n) {
		if (n != null) {
			return n.longValue();
		} else {
			return null;
		}
	}

	/**
	 * Converts double to long.
	 *
	 * @param d
	 *            number value
	 * @return long value
	 */
	public static long toLongPrimitive(final double d) {
		return Double.valueOf(d).longValue();
	}

	/**
	 * Converts int to long.
	 *
	 * @param i
	 *            number value
	 * @return long value
	 */
	public static long toLongPrimitive(final int i) {
		return i;
	}

	/**
	 * Converts number to long. If the number is null returns the <tt>defautlVal</tt>
	 *
	 * @param n
	 *            the number
	 * @param defaultVal
	 *            the value returned in case <tt>n</tt> is null
	 * @return n converted to long, or <tt>defautlVal</tt> if n is null
	 */
	public static long toLongPrimitive(final Number n, final long defaultVal) {
		if (n != null) {
			return n.longValue();
		} else {
			return defaultVal;
		}
	}

	/**
	 * Converts string to {@link Long}. If the value cannot be parsed, returns defaultValue
	 *
	 * @param str
	 *            string to be converted
	 * @param defaultValue
	 *            the defaultValue
	 * @return {@link Long} parsed value or default value if string cannot be parsed
	 */
	public static Integer parseInt(final String str, Integer defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(str);
		} catch (final NumberFormatException nfe) {
			return defaultValue;
		}
	}

	/**
	 * Converts number to {@link Integer}. If the value is null, returns null
	 *
	 * @param n
	 *            number value
	 * @return {@link Integer} value or null
	 */
	public static Integer toInt(final Number n) {
		if (n != null) {
			return n.intValue();
		} else {
			return null;
		}
	}

	/**
	 * Converts double to int.
	 *
	 * @param d
	 *            number value
	 * @return int value
	 */
	public static int toIntPrimitive(final double d) {
		return Double.valueOf(d).intValue();
	}

	/**
	 * Converts long to int.
	 *
	 * @param l
	 *            number value
	 * @return int value
	 */
	public static int toIntPrimitive(final long l) {
		return (int) l;
	}

	/**
	 * Converts number to int. If the number is null returns the <tt>defautlVal</tt>
	 *
	 * @param n
	 *            the number
	 * @param defaultVal
	 *            the value returned in case <tt>n</tt> is null
	 * @return n converted to int, or <tt>defautlVal</tt> if n is null
	 */
	public static int toIntPrimitive(final Number n, final int defaultVal) {
		if (n != null) {
			return n.intValue();
		} else {
			return defaultVal;
		}
	}

	/**
	 * Converts string to {@link Double}. If the value cannot be parsed, returns defaultValue
	 *
	 * @param str
	 *            string to be converted
	 * @param defaultValue
	 *            the defaultValue
	 * @return {@link Double} parsed value or default value if string cannot be parsed
	 */
	public static Double parseDouble(final String str, Double defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		try {
			return Double.parseDouble(str);
		} catch (final NumberFormatException nfe) {
			return defaultValue;
		}
	}

	/**
	 * Converts number to {@link Double}. If the value is null, returns null
	 *
	 * @param n
	 *            number value
	 * @return {@link Double} value or null
	 */
	public static Double toDouble(final Number n) {
		if (n != null) {
			return n.doubleValue();
		} else {
			return null;
		}
	}

	/**
	 * Converts long to double.
	 *
	 * @param l
	 *            number value
	 * @return double value
	 */
	public static double toDoublePrimitive(final long l) {
		return Long.valueOf(l).doubleValue();
	}

	/**
	 * Converts int to double.
	 *
	 * @param l
	 *            number value
	 * @return double value
	 */
	public static double toDoublePrimitive(final int l) {
		return Long.valueOf(l).doubleValue();
	}

	/**
	 * Converts number to double. If the number is null returns the <tt>defautlVal</tt>
	 *
	 * @param n
	 *            the number
	 * @param defaultVal
	 *            the value returned in case <tt>n</tt> is null
	 * @return n converted to double, or <tt>defautlVal</tt> if n is null
	 */
	public static double toDoublePrimitive(final Number n, final double defaultVal) {
		if (n != null) {
			return n.doubleValue();
		} else {
			return defaultVal;
		}
	}

	/**
	 * Checks if a double is valid number (not null, not inifinite, not NaN)
	 *
	 * @param d
	 *            the double
	 * @return true if d is not null, d is not infinite and d is not NaN
	 */
	public static boolean isValid(final Double d) {
		return d != null && !d.isInfinite() && !d.isNaN();
	}

	/**
	 * Checks if x is in the range of [a,b] (inclusive)
	 *
	 * @param x
	 *            the number. If null returns false
	 * @param a
	 *            the range start value.
	 * @param b
	 *            the range end value.
	 * @param <N>
	 *            number type
	 * @return true if x is in the range of [a,b] (inclusive)
	 */
	public static <N extends Number & Comparable<N>> boolean isInRange(final N x, N a, N b) {
		if (x == null) {
			return false;
		} else if (a != null && b != null) {
			if (a.compareTo(b) > 0) {
				final N t = a;
				a = b;
				b = t;
			}
			return a.compareTo(x) <= 0 && x.compareTo(b) <= 0;
		} else {
			if (a != null && a.compareTo(x) > 0) {
				return false;
			}
			if (b != null && b.compareTo(x) < 0) {
				return false;
			}
			return true;
		}
	}

	/**
	 * Checks if x is not in the range of [a,b] (inclusive)
	 *
	 * @param x
	 *            the number. If null returns true
	 * @param a
	 *            the range start value.
	 * @param b
	 *            the range end value.
	 * @param <N>
	 *            number type
	 * @return true if x is not in the range of [a,b] (inclusive)
	 */
	public static <N extends Number & Comparable<N>> boolean isNotInRange(final N x, final N a, final N b) {
		return !isInRange(x, a, b);
	}

	/**
	 * Check if two numbers are equals
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return true if a is equal to b
	 */
	public static boolean equals(final double a, final double b) { // NOSONAR
		return Double.compare(a, b) == 0;
	}

	/**
	 * Check if two numbers are equals
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @param <N>
	 *            number type
	 * @return true if a is equal to b
	 */
	public static <N extends Number & Comparable<N>> boolean equals(final N a, final N b) { // NOSONAR
		return (a != null && b != null && a.compareTo(b) == 0) || (a == null && b == null);
	}

	/**
	 * Check if two numbers are not equals
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @return true if a is not equal to b
	 */
	public static boolean notEquals(final double a, final double b) {
		return !equals(a, b);
	}

	/**
	 * Check if two numbers are not equals
	 *
	 * @param a
	 *            the first number
	 * @param b
	 *            the second number
	 * @param <N>
	 *            number type
	 * @return true if a is not equal to b
	 */
	public static <N extends Number & Comparable<N>> boolean notEquals(final N a, final N b) {
		return !equals(a, b);
	}
}
