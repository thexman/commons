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

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class CompositeIteratorTest {

	@Test
	public void testNext() {
		final Map<Long, String> map = MapBuilder.createTreeMap(1L, "one").put(2L, "two").put(3L, "three").build();
		final CompositeIterator<Long, String> it = new CompositeIterator<Long, String>(map.keySet().iterator(), k -> map.containsKey(k), key -> map.get(key), null);
		assertTrue(it.hasNext());
		assertEquals(Pair.of(1L, "one"), it.next());

		assertTrue(it.hasNext());
		assertEquals(Pair.of(2L, "two"), it.next());

		assertTrue(it.hasNext());
		assertEquals(Pair.of(3L, "three"), it.next());

		assertFalse(it.hasNext());
	}

	@Test
	public void testRemove2() {
		final Map<Long, String> map = MapBuilder.createTreeMap(1L, "one").put(2L, "two").put(3L, "three").build();
		final CompositeIterator<Long, String> it = new CompositeIterator<Long, String>(ExtCollectionUtils.copy(map).keySet().iterator(), k -> map.containsKey(k), k -> map.get(k), (k, v) -> map.remove(k));
		assertTrue(it.hasNext());
		assertEquals(Pair.of(1L, "one"), it.next());
		it.hasNext();
		it.remove();

		assertTrue(it.hasNext());
		assertEquals(Pair.of(2L, "two"), it.next());
		it.hasNext();
		it.remove();

		assertTrue(it.hasNext());
		assertEquals(Pair.of(3L, "three"), it.next());
		it.hasNext();
		it.remove();

		assertFalse(it.hasNext());
	}

	@Test
	public void testRemove() {
		final Map<Long, String> map = MapBuilder.createTreeMap(1L, "one").put(2L, "two").put(3L, "three").build();
		final CompositeIterator<Long, String> it = new CompositeIterator<Long, String>(ExtCollectionUtils.copy(map).keySet().iterator(), k -> map.containsKey(k), k -> map.get(k), (k, v) -> map.remove(k));
		assertTrue(it.hasNext());
		assertEquals(Pair.of(1L, "one"), it.next());
		it.remove();

		assertTrue(it.hasNext());
		assertEquals(Pair.of(2L, "two"), it.next());
		it.remove();

		assertTrue(it.hasNext());
		assertEquals(Pair.of(3L, "three"), it.next());
		it.remove();

		assertFalse(it.hasNext());
	}

}
