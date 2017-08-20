package com.a9ski.utils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Iterator supporting lazy loading
 *
 * @author Kiril Arabadzhiyski
 *
 * @param <E>
 *            the items type
 */
public class LazyLoadingIterator<E> implements Iterator<E> {

	private final int pageSize;

	private int nextPage = 0;

	private List<E> items = null;

	private int nextItemIndex = 0;

	private final LazyLoadingProvider<E> lazyLoadingProvider;

	private final Consumer<E> deleteOperation;

	protected LazyLoadingIterator(final int pageSize, final LazyLoadingProvider<E> lazyLoadingProvider, final Consumer<E> deleteOperation) {
		if (pageSize < 0) {
			throw new IllegalArgumentException("pageSize must be positive integer");
		}
		this.pageSize = pageSize;

		if (lazyLoadingProvider == null) {
			throw new IllegalArgumentException("lazyLoadingProvider must not be null");
		}
		this.lazyLoadingProvider = lazyLoadingProvider;
		this.deleteOperation = deleteOperation;
	}

	protected void deleteItem(final int index, final E item) {
		if (deleteOperation != null) {
			deleteOperation.accept(item);
		} else {
			throw new UnsupportedOperationException("Item deletion is not supported");
		}
	}

	/**
	 * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next} would return an element rather than throwing an exception.)
	 *
	 * @return {@code true} if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		if (items == null) {
			nextPage = 0;
			items = Collections.emptyList();
		}
		final boolean hasNext;
		if (nextItemIndex >= items.size()) {
			final int count = lazyLoadingProvider.countItems();
			final int first = getFirstItemIndex(nextPage);
			if (first < count) {
				items = loadItems(nextPage);
				nextPage++;
				nextItemIndex = 0;
				hasNext = ExtCollectionUtils.isNotEmpty(items);
			} else {
				items = Collections.emptyList();
				nextItemIndex = 0;
				hasNext = false;
			}
		} else {
			hasNext = true;
		}
		return hasNext;
	}

	/**
	 * Calls the lazy loading provider and returns a copy of the items
	 *
	 * @param page
	 *            the page number
	 * @return a copy of the items loaded with the lazy load provider
	 */
	protected List<E> loadItems(final int page) {
		return ExtCollectionUtils.toList(lazyLoadingProvider.loadItems(page));
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		final E item = items.get(nextItemIndex);
		nextItemIndex++;

		return item;
	}

	/**
	 * Removes from the underlying collection the last element returned by this iterator (optional operation). This method can be called only once per call to {@link #next}. The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any
	 * way other than by calling this method.
	 *
	 * @throws IllegalStateException
	 *             if the {@code next} method has not yet been called, or the {@code remove} method has already been called after the last call to the {@code next} method
	 */
	@Override
	public void remove() {
		if (nextItemIndex <= 0) {
			throw new IllegalStateException("next() has not yet been called");
		}
		final int ind = nextItemIndex - 1;
		final E e = items.get(ind);
		deleteItem(ind, e);
		items.remove(ind);
		if (items.isEmpty()) {
			nextPage--;
		}
		nextItemIndex--;
	}

	/**
	 * Gets the index of the first element of the page
	 *
	 * @param page
	 *            the page number
	 * @return the index of the first element of the page
	 */
	public int getFirstItemIndex(final int page) {
		return page * pageSize;
	}

	/**
	 * Gets the page size
	 *
	 * @return the page size
	 */
	public int getPageSize() {
		return pageSize;
	}
}
