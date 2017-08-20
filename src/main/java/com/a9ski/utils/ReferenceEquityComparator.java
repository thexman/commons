package com.a9ski.utils;

/**
 * Implements {@link EquityComparator} based on reference check
 * 
 * @author Kiril Arabadzhiyski
 *
 * @param <O>
 */
public class ReferenceEquityComparator<O> implements EquityComparator<O> {

	@Override
	public boolean equals(final O o1, final O o2) {
		return o1 == o2; // NOSONAR this is the purpose of this comparator - to compare references
	}

}
