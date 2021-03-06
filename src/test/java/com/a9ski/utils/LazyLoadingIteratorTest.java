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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class LazyLoadingIteratorTest {

	@Test
	public void testNextAndRemove() {
		final int pageSize = 2;
		final List<Integer> list = ExtCollectionUtils.toList(0, 1, 2, 3, 4, 5);

		final LazyLoadingProvider<Integer> p = new LazyLoadingProvider<Integer>() {

			@Override
			public int countItems() {
				return list.size();
			}

			@Override
			public List<Integer> loadItems(final int page) {
				final int ind = page * pageSize;
				return list.subList(ind, Math.min(list.size(), ind + pageSize));
			}
		};

		final LazyLoadingIterator<Integer> it = new LazyLoadingIterator<>(pageSize, p, (e) -> list.remove(e));
		assertTrue(it.hasNext()); // 0
		assertEquals(Integer.valueOf(0), it.next());

		assertTrue(it.hasNext()); // 1
		assertEquals(Integer.valueOf(1), it.next());

		assertTrue(it.hasNext()); // 2
		assertEquals(Integer.valueOf(2), it.next());
		it.remove(); // 2

		assertTrue(it.hasNext()); // 3
		assertEquals(Integer.valueOf(3), it.next());
		it.remove(); // 3

		assertTrue(it.hasNext()); // 4
		assertEquals(Integer.valueOf(4), it.next());
		it.remove(); // 4

		assertTrue(it.hasNext()); // 5
		assertEquals(Integer.valueOf(5), it.next());
		it.remove(); // 5

		assertFalse(it.hasNext());
	}

	@Test
	public void testRemoveAll() {
		final int pageSize = 2;
		final List<Integer> list = ExtCollectionUtils.toList(0, 1, 2, 3, 4, 5);

		final LazyLoadingProvider<Integer> p = new LazyLoadingProvider<Integer>() {

			@Override
			public int countItems() {
				return list.size();
			}

			@Override
			public List<Integer> loadItems(final int page) {
				final int ind = page * pageSize;
				return list.subList(ind, Math.min(list.size(), ind + pageSize));
			}
		};

		final LazyLoadingIterator<Integer> it = new LazyLoadingIterator<>(pageSize, p, (e) -> list.remove(e));
		assertTrue(it.hasNext()); // 0
		assertEquals(Integer.valueOf(0), it.next());
		it.remove(); // 0

		assertTrue(it.hasNext()); // 1
		assertEquals(Integer.valueOf(1), it.next());
		it.remove(); // 1

		assertTrue(it.hasNext()); // 2
		assertEquals(Integer.valueOf(2), it.next());
		it.remove(); // 2

		assertTrue(it.hasNext()); // 3
		assertEquals(Integer.valueOf(3), it.next());
		it.remove(); // 3

		assertTrue(it.hasNext()); // 4
		assertEquals(Integer.valueOf(4), it.next());
		it.remove(); // 4

		assertTrue(it.hasNext()); // 5
		assertEquals(Integer.valueOf(5), it.next());
		it.remove(); // 5

		assertFalse(it.hasNext());
	}

}
