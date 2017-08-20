package com.a9ski.utils;

import java.util.List;

/**
 * Interface for lazy loading provider
 * 
 * @author Kiril Arabadzhiyski
 *
 * @param <E>
 *            type of the provided elements
 */
public interface LazyLoadingProvider<E> {
	/**
	 * Returns the total count of elements
	 * 
	 * @return total count of elements
	 */
	public int countItems();

	/**
	 * Loads elements from given page
	 * 
	 * @param page
	 *            the page number
	 * @return elements belonging to given page
	 */
	public List<E> loadItems(int page);

}
