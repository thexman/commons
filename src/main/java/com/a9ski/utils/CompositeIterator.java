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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Creates an iterator that composes a pairs of key values. The keys are provided by different iterator.
 *
 * @author Kiril Arabadzhiyski
 *
 * @param <K>
 *            the key class
 * @param <V>
 *            the value class
 */
public class CompositeIterator<K, V> implements Iterator<Pair<K, V>> {

	private final Iterator<K> keysIterator;

	private K nextKey = null;
	private V nextValue = null;
	private boolean hasValueForKey = false;
	private boolean noMoreElements = false;

	private final Function<K, Boolean> hasValue;

	private final Function<K, V> getValue;

	private final BiConsumer<K, V> removeValue;

	protected CompositeIterator(final Iterator<K> keysIterator, final Function<K, Boolean> hasValue, final Function<K, V> getValue, final BiConsumer<K, V> removeValue) {
		NullUtils.checkNotNull(keysIterator, "keysIterator must not be null");
		this.keysIterator = keysIterator;

		NullUtils.checkNotNull(hasValue, "hasValue must not be null");
		this.hasValue = hasValue;

		NullUtils.checkNotNull(hasValue, "getValue must not be null");
		this.getValue = getValue;

		this.removeValue = removeValue;
	}

	/**
	 * Checks if there is value for given key
	 *
	 * @param key
	 *            the key
	 * @return true if there is value for given key
	 */
	protected boolean hasValueForKey(final K key) {
		return hasValue.apply(key);
	}

	/**
	 * Get the value for given key
	 *
	 * @param key
	 *            the key
	 * @return the value of the key
	 */
	protected V getValueForKey(final K key) {
		return getValue.apply(key);
	}

	/**
	 * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next} would return an element rather than throwing an exception.) has
	 *
	 * @return {@code true} if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		advanceNextKeyAndValue();

		return hasValueForKey;
	}

	protected void advanceNextKeyAndValue() {
		if (!hasValueForKey) {
			while (keysIterator.hasNext() && !hasValueForKey) {
				nextKey = keysIterator.next();
				hasValueForKey = hasValueForKey(nextKey);
			}

			if (hasValueForKey) {
				nextValue = getValueForKey(nextKey);
			} else {
				noMoreElements = true;
			}
		}
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	@Override
	public Pair<K, V> next() {
		if (!hasValueForKey) {
			if (noMoreElements) {
				throw new NoSuchElementException();
			}
			advanceNextKeyAndValue();
			if (noMoreElements) {
				throw new NoSuchElementException();
			}
		}

		hasValueForKey = false; // reset, so next call we finds the new
								// key,value

		return Pair.of(nextKey, nextValue);
	}

	/**
	 * Removes from the underlying collection the last element returned by this iterator (optional operation). This method can be called only once per call to {@link #next}. The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any
	 * way other than by calling this method.
	 *
	 * @throws UnsupportedOperationException
	 *             if the {@code remove} operation is not supported by this iterator
	 *
	 * @throws IllegalStateException
	 *             if the {@code next} method has not yet been called, or the {@code remove} method has already been called after the last call to the {@code next} method
	 */
	@Override
	public void remove() {
		if (removeValue == null) {
			throw new UnsupportedOperationException();
		}
		removeValue.accept(nextKey, nextValue);
	}

}
