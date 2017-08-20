package com.a9ski.utils;

/**
 * Functional interface for implementing equity comparator
 * 
 * @author Kiril Arabadzhiyski
 *
 * @param <O>
 *            the elementh type
 */
@FunctionalInterface
public interface EquityComparator<O> {
	public boolean equals(O o1, O o2); // NOSONAR no name collision
}
