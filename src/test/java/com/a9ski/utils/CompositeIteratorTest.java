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
