package com.a9ski.utils;

@FunctionalInterface
public interface EquityComparator<O> {
	public boolean equals(O o1, O o2); // NOSONAR no name collision
}
