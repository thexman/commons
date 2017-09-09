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

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Utilities methods for dealing with Strings
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static final Pattern PATTERN_MORE_THAN_ONE_SPACES = Pattern.compile("( ){2,}");

	/**
	 * Compares two strings. If any of them is null, it is replaced with empty string
	 *
	 * @param a
	 *            first string
	 * @param b
	 *            second string
	 * @return a.compareTo(b)
	 */
	public static int compareTo(final String a, final String b) {
		return defaultString(a).compareTo(defaultString(b));
	}

	/**
	 * Compares two strings ignoring case. If any of them is null, it is replaced with empty string
	 *
	 * @param a
	 *            first string
	 * @param b
	 *            second string
	 * @return a.compareTo(b)
	 */
	public static int compareToIgnoreCase(final String a, final String b) {
		return defaultString(a).compareToIgnoreCase(defaultString(b));
	}

	/**
	 * Checks if two string are equals after trimming.
	 *
	 * @param s1
	 *            the first string or null
	 * @param s2
	 *            the second string or null
	 * @return true if s1 is equal to s2
	 */
	public static boolean equalsTrim(final String s1, final String s2) {
		return equals(trim(s1), trim(s2));
	}

	/**
	 * Checks if two string are not equals after trimming.
	 *
	 * @param s1
	 *            the first string or null
	 * @param s2
	 *            the second string or null
	 * @return true if s1 is not equal to s2
	 */
	public static boolean notEqualsTrim(final String s1, final String s2) {
		return !equalsTrim(s1, s2);
	}

	/**
	 * Checks if two string are equals after trimming, ignoring case
	 *
	 * @param s1
	 *            the first string or null
	 * @param s2
	 *            the second string or null
	 * @return true if s1 is equal to s2
	 */
	public static boolean equalsIgnoreCaseTrim(final String s1, final String s2) {
		return equalsIgnoreCase(trim(s1), trim(s2));
	}

	/**
	 * Checks if two string are not equals after trimming, ignoring case
	 *
	 * @param s1
	 *            the first string or null
	 * @param s2
	 *            the second string or null
	 * @return true if s1 is not equal to s2
	 */
	public static boolean notEqualsIgnoreCaseTrim(final String s1, final String s2) {
		return !equalsIgnoreCaseTrim(s1, s2);
	}

	/**
	 * Checks if two string are not equals
	 *
	 * @param cs1
	 *            the first string or null
	 * @param cs2
	 *            the second string or null
	 * @return true if s1 is not equal to s2
	 */
	public static boolean notEquals(final CharSequence cs1, final CharSequence cs2) {
		return !equals(cs1, cs2);
	}

	/**
	 * <p>
	 * Checks if CharSequence doesn't contain a search CharSequence, handling {@code null}. This method uses {@link String#indexOf(String)} if possible.
	 * </p>
	 *
	 * <p>
	 * A {@code null} CharSequence will return {@code true}.
	 * </p>
	 *
	 * @param seq
	 *            the CharSequence to check, may be null
	 * @param searchSeq
	 *            the CharSequence to find, may be null
	 * @return true if the CharSequence doesn't contain the search CharSequence,
	 */
	public static boolean notContains(final CharSequence seq, final CharSequence searchSeq) {
		return !contains(seq, searchSeq);
	}

	/**
	 * <p>
	 * Checks if String contains a search String, handling {@code null}. This method uses {@link String#indexOf(String)} if possible. The method trims both strings before performing search.
	 * </p>
	 *
	 * <p>
	 * A {@code null} String will return {@code false}.
	 * </p>
	 *
	 *
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return true if the String contains the search String,
	 */
	public static boolean containsTrim(final String str, final String searchStr) {
		return contains(trim(str), trim(searchStr));
	}

	/**
	 * <p>
	 * Checks if String contains a search String irrespective of case, handling {@code null}. Case-insensitivity is defined as by {@link String#equalsIgnoreCase(String)}.
	 *
	 * <p>
	 * A {@code null} String will return {@code false}.
	 * </p>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return true if the String contains the search String,
	 */
	public static boolean containsTrimIgnoreCase(final String str, final String searchStr) {
		return containsIgnoreCase(trim(str), trim(searchStr));
	}

	/**
	 * <p>
	 * Checks if String doesn't contain a search String irrespective of case, handling {@code null}. Case-insensitivity is defined as by {@link String#equalsIgnoreCase(String)}.
	 *
	 * <p>
	 * A {@code null} String will return {@code false}.
	 * </p>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return true if the String doesn't contains the search String,
	 */
	public static boolean notContainsTrim(final String str, final String searchStr) {
		return !contains(trim(str), trim(searchStr));
	}

	/**
	 * <p>
	 * Checks if String doesn't contain a search String irrespective of case, handling {@code null}. Case-insensitivity is defined as by {@link String#equalsIgnoreCase(String)}.
	 *
	 * <p>
	 * A {@code null} String will return {@code false}.
	 * </p>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return true if the String doesn't contain the search String,
	 */
	public static boolean notContainsTrimIgnoreCase(final String str, final String searchStr) {
		return !containsTrimIgnoreCase(str, searchStr);
	}

	/**
	 * Calculate a hashCode of a String. Return 0 if the string is null
	 *
	 * @param s
	 *            the string
	 * @return <tt>s.hashCode()</tt> or 0 if <tt>s</tt> is null
	 */
	public static int hashCode(final String s) {
		final int h;
		if (s != null) {
			h = s.hashCode();
		} else {
			h = 0;
		}
		return h;
	}

	/**
	 * <p>
	 * Joins the elements of the provided {@code Iterable} into single String containing the provided elements. The elements are fist filtered using <tt>predicate</tt>
	 * </p>
	 *
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator is the same as an empty String ("").
	 * </p>
	 *
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 *
	 * @param separator
	 *            the separator
	 * @param predicate
	 *            the predicate used to filter strings
	 * @param items
	 *            the items to be joined
	 * @param <C>
	 *            string type
	 * @return the joined String, {@code null} if null iterator input
	 */
	public static <C extends CharSequence> String joinWithFilter(final String separator, final Predicate<C> predicate, final Iterable<C> items) {
		if (items != null) {
			final Predicate<C> p = ObjectUtils.defaultIfNull(predicate, (x -> true));
			return join(ExtCollectionUtils.stream(items).filter(p).collect(Collectors.toList()), separator);
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into single String containing the provided elements. The elements are fist filtered using <tt>predicate</tt>
	 * </p>
	 *
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator is the same as an empty String ("").
	 * </p>
	 *
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 *
	 * @param separator
	 *            the separator
	 * @param predicate
	 *            the predicate used to filter strings
	 * @param items
	 *            the items to be joined
	 * @param <C>
	 *            string type
	 * @return the joined String, {@code null} if null array input
	 */
	@SafeVarargs
	public static <C extends CharSequence> String joinWithFilter(final String separator, final Predicate<C> predicate, final C... items) {
		if (items != null) {
			return joinWithFilter(separator, predicate, Arrays.asList(items));
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * Joins the elements of the provided {@code Iterable} into single String containing the provided elements. The elements are fist filtered using {@code #isNotBlank(CharSequence)} predicate
	 * </p>
	 *
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator is the same as an empty String ("").
	 * </p>
	 *
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 *
	 * @param separator
	 *            the separator
	 * @param items
	 *            the items to be joined
	 * @return the joined String, {@code null} if null iterator input
	 */
	public static String joinNonBlanks(final String separator, final Iterable<String> items) {
		return joinWithFilter(separator, s -> isNotBlank(s), items);
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into single String containing the provided elements. The elements are fist filtered using {@code #isNotBlank(CharSequence)} predicate
	 * </p>
	 *
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator is the same as an empty String ("").
	 * </p>
	 *
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 *
	 * @param separator
	 *            the separator
	 * @param items
	 *            the items to be joined
	 * @return the joined String, {@code null} if null array input
	 */
	public static String joinNonBlanks(final String separator, final String... items) {
		return joinWithFilter(separator, s -> isNotBlank(s), items);
	}

	/**
	 * Replaces sequence of two or more spaces with single space
	 * <p>
	 * Example: <code>replaceWithSingleSpace("hello " + " " + " " + " world")</code> will produce <code>"hello world"</code> string
	 *
	 * @param s
	 *            string to be replaced
	 * @return string with single spaces
	 */
	public static String replaceWithSingleSpace(final String s) {
		return (isNotEmpty(s) ? PATTERN_MORE_THAN_ONE_SPACES.matcher(s).replaceAll(" ") : s);
	}

	/**
	 * <p>
	 * Compares given <code>string</code> to a CharSequences vararg of <code>searchStrings</code>, returning {@code true} if the <code>string</code> is not equal to any of the <code>searchStrings</code>.
	 * </p>
	 *
	 * @param string
	 *            to compare, may be {@code null}.
	 * @param searchStrings
	 *            a vararg of strings, may be {@code null}.
	 * @return {@code true} if the string is not equal (case-sensitive) to any other element of <code>searchStrings</code>
	 */
	public static boolean notEqualsAny(final CharSequence string, final CharSequence... searchStrings) {
		return !equalsAny(string, searchStrings);
	}

	/**
	 * <p>
	 * Compares given <code>string</code> to a CharSequences vararg of <code>searchStrings</code>, returning {@code true} if the <code>string</code> is not equal to any of the <code>searchStrings</code>, ignoring case.
	 * </p>
	 *
	 * @param string
	 *            to compare, may be {@code null}.
	 * @param searchStrings
	 *            a vararg of strings, may be {@code null}.
	 * @return {@code true} if the string is not equal (case-insensitive) to any other element of <code>searchStrings</code>
	 */
	public static boolean notEqualsIgnoreCaseAny(final CharSequence string, final CharSequence... searchStrings) {
		return !equalsAnyIgnoreCase(string, searchStrings);
	}
}
