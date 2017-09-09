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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class ExtCollectionUtilsTest {

	@Test
	public void testDefaultCollection() {
		final List<Integer> list = Arrays.asList(1, 2, 3);
		assertTrue(list == ExtCollectionUtils.defaultCollection(list));
		final Collection<Integer> empty = ExtCollectionUtils.defaultCollection((List<Integer>) null);
		assertEmpty(empty);
		assertTrue(empty instanceof ArrayList);
	}

	@Test
	public void testDefaultArrayList() {
		final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
		assertTrue(list == ExtCollectionUtils.defaultArrayList(list));
		final ArrayList<Object> empty = ExtCollectionUtils.defaultArrayList(null);
		assertEmpty(empty);
		empty.add(new Object()); // ensure that the list is not read-only
	}

	@Test
	public void testDefaultLinkedList() {
		final LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3));
		assertTrue(list == ExtCollectionUtils.defaultLinkedList(list));
		final LinkedList<Object> empty = ExtCollectionUtils.defaultLinkedList(null);
		assertEmpty(empty);
		empty.add(new Object()); // ensure that the list is not read-only
	}

	@Test
	public void testDefaultList() {
		final List<Integer> list = Arrays.asList(1, 2, 3);
		assertTrue(list == ExtCollectionUtils.defaultList(list));
		final List<Object> empty = ExtCollectionUtils.defaultList(null);
		assertEmpty(empty);
		empty.add(new Object()); // ensure that the list is not read-only
	}

	@Test
	public void testDefaultMap() {
		final Map<Integer, String> map = MapBuilder.createMap(1, "one").put(2, "two").build();
		assertTrue(map == ExtCollectionUtils.defaultMap(map));
		final Map<Object, Object> empty = ExtCollectionUtils.defaultMap(null);
		assertEmpty(empty);
		empty.put(1, "one"); // ensure that the list is not read-only
	}

	@Test
	public void testDefaultTreeMap() {
		final TreeMap<Integer, String> map = MapBuilder.createTreeMap(1, "one").put(2, "two").build();
		assertTrue(map == ExtCollectionUtils.defaultTreeMap(map));
		final Map<Object, Object> empty = ExtCollectionUtils.defaultTreeMap(null);
		assertNotNull(empty);
		assertEquals(0, empty.size());
		empty.put(1, "one"); // ensure that the list is not read-only
	}

	@Test
	public void testDefaultLinkedHashMap() {
		final LinkedHashMap<Integer, String> map = MapBuilder.createLinkedHashMap(1, "one").put(2, "two").build();
		assertTrue(map == ExtCollectionUtils.defaultLinkedHashMap(map));
		final Map<Object, Object> empty = ExtCollectionUtils.defaultLinkedHashMap(null);
		assertEmpty(empty);
		empty.put(1, "one"); // ensure that the list is not read-only
	}

	@Test
	public void testDefaultHashMap() {
		final HashMap<Integer, String> map = MapBuilder.createHashMap(1, "one").put(2, "two").build();
		assertTrue(map == ExtCollectionUtils.defaultHashMap(map));
		final Map<Object, Object> empty = ExtCollectionUtils.defaultHashMap(null);
		assertEmpty(empty);
		empty.put(1, "one"); // ensure that the list is not read-only
	}

	@Test
	public void testCombineValues() throws Exception {
		final Map<String, List<Integer>> map = MapBuilder.createMap("a", Arrays.asList(1, 2, 4)).put("b", Arrays.asList(2, 5, 6)).put("c", Arrays.asList(3)).build();
		final TreeSet<Integer> set = ExtCollectionUtils.combineValues(map, TreeSet::new);
		assertEquals(new TreeSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)), set);
		set.add(0); // ensure that the set is not read-only

		final TreeSet<Integer> empty = ExtCollectionUtils.combineValues(null, TreeSet::new);
		assertEmpty(empty);
		empty.add(0); // ensure that the set is not read-only
	}

	@Test
	public void testCombine() throws Exception {
		final TreeSet<Integer> set = ExtCollectionUtils.combine(TreeSet::new, null, Arrays.asList(1, 3), Arrays.asList(), Arrays.asList(1, 2, 6), Arrays.asList(4, 5));
		assertEquals(new TreeSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)), set);
		set.add(0); // ensure that the set is not read-only

		final TreeSet<Integer> empty = ExtCollectionUtils.combine(TreeSet::new);
		assertEmpty(empty);
		empty.add(0); // ensure that the set is not read-only

		final TreeSet<Integer> empty2 = ExtCollectionUtils.combine(TreeSet::new, null, Arrays.asList());
		assertEmpty(empty2);
		empty2.add(0); // ensure that the set is not read-only
	}

	private void assertEmpty(final Collection<?> empty) {
		assertNotNull(empty);
		assertEquals(0, empty.size());
	}

	private void assertEmpty(final Map<?, ?> empty) {
		assertNotNull(empty);
		assertEquals(0, empty.size());
	}

	@Test
	public void testReplaceNullValuesWith() throws Exception {
		final Map<Integer, String> map = MapBuilder.createMap(1, "one").put(2, null).put(3, null).build();
		ExtCollectionUtils.replaceNullValuesWith(map, "empty");
		final Map<Integer, String> expected = MapBuilder.createMap(1, "one").put(2, "empty").put(3, "empty").build();
		assertEquals(expected, map);
	}

	@Test
	public void testGet0() throws Exception {
		assertEquals(Integer.valueOf(1), ExtCollectionUtils.get0(Arrays.asList(1, 2, 3)));
		assertNull(ExtCollectionUtils.get0(Arrays.asList()));
		assertNull(ExtCollectionUtils.get0(null));
	}

	@Test
	public void testGetN() throws Exception {
		assertEquals(Integer.valueOf(1), ExtCollectionUtils.getN(Arrays.asList(1, 2, 3), 0, null));
		assertNull(ExtCollectionUtils.getN(Arrays.asList(), 0, null));
		assertNull(ExtCollectionUtils.getN(null, 0, null));

		assertEquals(Integer.valueOf(2), ExtCollectionUtils.getN(Arrays.asList(1, 2, 3), 1, null));
		assertNull(ExtCollectionUtils.getN(Arrays.asList(), 1, null));
		assertNull(ExtCollectionUtils.getN(null, 1, null));

		assertEquals(Integer.valueOf(3), ExtCollectionUtils.getN(Arrays.asList(1, 2, 3), 2, null));
		assertNull(ExtCollectionUtils.getN(Arrays.asList(), 2, null));
		assertNull(ExtCollectionUtils.getN(null, 2, null));

		assertEquals(Integer.valueOf(4), ExtCollectionUtils.getN(Arrays.asList(1, 2, 3), 3, 4));
		assertNull(ExtCollectionUtils.getN(Arrays.asList(), 3, null));
		assertNull(ExtCollectionUtils.getN(null, 3, null));

		assertEquals(Integer.valueOf(4), ExtCollectionUtils.getN(Arrays.asList(1, 2, 3), 100, 4));
		assertNull(ExtCollectionUtils.getN(Arrays.asList(), 100, null));
		assertNull(ExtCollectionUtils.getN(null, 100, null));

		try {
			assertEquals(Integer.valueOf(4), ExtCollectionUtils.getN(Arrays.asList(1, 2, 3), -100, 4));
			fail("Expected IllegalArgumentException to be thrown");
		} catch (final IllegalArgumentException ex) {
			// expected exception
		}
	}

	@Test
	public void testSet0() throws Exception {
		final List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		ExtCollectionUtils.set0(list, 42);
		assertEquals(Arrays.asList(42, 2, 3, 4), list);

		ExtCollectionUtils.set0(null, 42);
	}

	@Test
	public void testSetN() throws Exception {
		final List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		ExtCollectionUtils.setN(list1, 2, 42, 21);
		assertEquals(Arrays.asList(1, 2, 42, 4), list1);

		ExtCollectionUtils.setN(list1, 2, 42, 21);

		final List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2));
		ExtCollectionUtils.setN(list2, 4, 42, 21);
		assertEquals(Arrays.asList(1, 2, 21, 21, 42), list2);
	}

	@Test
	public void testCollection() throws Exception {
		assertEquals(new HashSet<Integer>(Arrays.asList(1, 2, 3)), ExtCollectionUtils.collection(Arrays.asList(1, 2, 1, 3), HashSet::new));

		assertEquals(new HashSet<Integer>(Arrays.asList(1, 2, 3)), ExtCollectionUtils.collection(HashSet::new, 1, 2, 1, 3));

		assertEquals(new HashSet<Integer>(), ExtCollectionUtils.collection(HashSet::new));

		assertEquals(new HashSet<Integer>(), ExtCollectionUtils.collection(HashSet::new, (Integer[]) null));

		assertNull(ExtCollectionUtils.collection(HashSet::new, null, (Integer[]) null));

		assertNull(ExtCollectionUtils.collection(null, HashSet::new, (HashSet<Object>) null));
	}

	@Test
	public void testTreeSetSupplier() throws Exception {
		final Comparator<Integer> cmp = (a, b) -> -Integer.compare(a, b);
		final List<Integer> list = new ArrayList<>(ExtCollectionUtils.collection(ExtCollectionUtils.treeSetSupplier(cmp), 1, 2, 2, 5));
		assertEquals(Arrays.asList(5, 2, 1), list);
	}

	@Test
	public void testToSet() throws Exception {
		assertEquals(new HashSet<Integer>(Arrays.asList(1, 2, 3)), ExtCollectionUtils.toSet(Arrays.asList(1, 2, 1, 3)));

		assertEquals(new HashSet<Integer>(Arrays.asList(1, 2, 3)), ExtCollectionUtils.toSet(1, 2, 1, 3));

		assertEquals(new HashSet<Integer>(), ExtCollectionUtils.toSet((List<Integer>) null));

		assertEquals(new HashSet<Integer>(), ExtCollectionUtils.toSet());
	}

	@Test
	public void testIsEmpty() throws Exception {
		assertTrue(ExtCollectionUtils.isEmpty((Collection<?>) null));
		assertTrue(ExtCollectionUtils.isEmpty(new ArrayList<Object>()));
		assertFalse(ExtCollectionUtils.isEmpty(Arrays.asList(42)));

		assertFalse(ExtCollectionUtils.isNotEmpty((Collection<?>) null));
		assertFalse(ExtCollectionUtils.isNotEmpty(new ArrayList<Object>()));
		assertTrue(ExtCollectionUtils.isNotEmpty(Arrays.asList(42)));

		assertTrue(ExtCollectionUtils.isEmpty((Map<?, ?>) null));
		assertTrue(ExtCollectionUtils.isEmpty(new TreeMap<Object, Object>()));
		assertFalse(ExtCollectionUtils.isEmpty(MapBuilder.createMap("a", 42).build()));

		assertFalse(ExtCollectionUtils.isNotEmpty((Map<?, ?>) null));
		assertFalse(ExtCollectionUtils.isNotEmpty(new TreeMap<Object, Object>()));
		assertTrue(ExtCollectionUtils.isNotEmpty(MapBuilder.createMap("a", 42).build()));
	}

	@Test
	public void testGetMapValues() throws Exception {
		assertEquals(new ArrayList<>(), ExtCollectionUtils.getMapValues(null, 1, 2, 3));

		final Map<Integer, String> map = MapBuilder.createMap(1, "one").put(2, "two").put(3, "three").put(4, "four").build();

		assertEquals(Arrays.asList("four", "two"), ExtCollectionUtils.getMapValues(map, 4, 2));

		assertEquals(new ArrayList<>(), ExtCollectionUtils.getMapValues(map, (Integer[]) null));
	}

	@Test
	public void testChangeMapValues() throws Exception {
		final Map<Integer, String> map1 = MapBuilder.createMap(1, "").put(2, "two").put(3, "").put(4, "").build();
		final Map<Integer, String> map2 = MapBuilder.createMap(1, "empty").put(2, "two").put(3, "empty").put(4, "empty").build();
		assertEquals(map2, ExtCollectionUtils.changeMapValues(map1, "", "empty"));
	}

	@Test
	public void testRemoveMapValues() throws Exception {
		final Map<Integer, String> map1 = MapBuilder.createMap(1, "").put(2, "two").put(3, "empty").put(4, "").build();
		final Map<Integer, String> map2 = MapBuilder.createMap(2, "two").build();
		assertEquals(map2, ExtCollectionUtils.removeMapValues(map1, "", "empty"));
	}

	@Test
	public void testAddAll() throws Exception {
		final List<Integer> list = ExtCollectionUtils.toList(1, 2);
		assertEquals(Arrays.asList(1, 2), ExtCollectionUtils.addAll(list, null, null));
		assertEquals(Arrays.asList(1, 2, 3, 4), ExtCollectionUtils.addAll(list, Arrays.asList(3, 4), null));
		assertEquals(Arrays.asList(1, 2, 3, 4), ExtCollectionUtils.addAll(null, Arrays.asList(1, 2, 3, 4), ArrayList::new));
		assertNull(ExtCollectionUtils.addAll(null, Arrays.asList(1, 2, 3, 4), null));
	}

	@Test
	public void testRemoveAll() throws Exception {
		final List<Integer> list = ExtCollectionUtils.toList(1, 1, 2, 2, 3, 4);
		assertEquals(Arrays.asList(1, 1, 2, 2, 3, 4), ExtCollectionUtils.removeAll(list, (Collection<Integer>) null));
		assertEquals(Arrays.asList(3, 4), ExtCollectionUtils.removeAll(list, Arrays.asList(1, 2)));
		assertNull(ExtCollectionUtils.removeAll((Collection<Integer>) null, Arrays.asList(1, 2, 3, 4)));
	}

	@Test
	public void testSubstract() throws Exception {
		final List<Integer> a = ExtCollectionUtils.toList(1, 1, 2, 2, 3, 4);
		final List<Integer> b = ExtCollectionUtils.toList(1, 2);
		assertEquals(Arrays.asList(3, 4), ExtCollectionUtils.subtract(a, b));
	}

	@Test
	public void testAllIndexesOf() throws Exception {
		final List<Integer> list = ExtCollectionUtils.toList(1, 1, 2, 1, 1, 4, 1);
		assertEquals(Arrays.asList(0, 1, 3, 4, 6), ExtCollectionUtils.allIndexesOf(list, 1, 0));
		assertEquals(0, ExtCollectionUtils.allIndexesOf(list, 42, 0).size());

		assertEquals(Arrays.asList(0, 1, 3, 4, 6), ExtCollectionUtils.allIndexesOf(list, 1, -1));
		assertEquals(0, ExtCollectionUtils.allIndexesOf(list, 42, -1).size());
		assertEquals(0, ExtCollectionUtils.allIndexesOf(list, 1, 7).size());
	}

	@Test
	public void testContains() throws Exception {
		final List<Integer> list = ExtCollectionUtils.toList(1, 1, 2, 1, 1, 4, 1);
		assertTrue(ExtCollectionUtils.contains(list, 4));
		assertFalse(ExtCollectionUtils.contains(list, 42));
		assertFalse(ExtCollectionUtils.contains(null, 4));
	}

	@Test
	public void testPut() throws Exception {
		assertNull(ExtCollectionUtils.put(null, "a", "b"));
		final Map<String, String> m = ExtCollectionUtils.put(new HashMap<>(), "a", "b");
		assertEquals(1, m.size());
		assertEquals("b", m.get("a"));
	}

	@Test
	public void testSubCollection() throws Exception {
		final List<Integer> list = ExtCollectionUtils.toList(0, 1, 2, 3, 4, 5, 6, 7);

		final TreeSet<Integer> subCollection = ExtCollectionUtils.subCollection(list, 2, 5, TreeSet::new);
		assertEquals(new TreeSet<>(Arrays.asList(2, 3, 4)), subCollection);

		assertEquals(new TreeSet<>(Arrays.asList(6, 7)), ExtCollectionUtils.subCollection(list, 6, 8, TreeSet::new));
		assertEquals(new TreeSet<>(Arrays.asList(6, 7)), ExtCollectionUtils.subCollection(list, 6, 10, TreeSet::new));
		assertEquals(new TreeSet<>(Arrays.asList(0, 1)), ExtCollectionUtils.subCollection(list, -5, 2, TreeSet::new));

		assertEquals(new TreeSet<Integer>(), ExtCollectionUtils.subCollection(null, -5, 2, TreeSet::new));

		assertEquals(new TreeSet<Integer>(), ExtCollectionUtils.subCollection(list, -5, -2, TreeSet::new));
		assertEquals(new TreeSet<Integer>(), ExtCollectionUtils.subCollection(list, 10, 42, TreeSet::new));
		assertEquals(new TreeSet<Integer>(), ExtCollectionUtils.subCollection(list, 3, 2, TreeSet::new));
		assertEquals(new TreeSet<Integer>(), ExtCollectionUtils.subCollection(list, 2, 2, TreeSet::new));

		assertEquals(new TreeSet<Integer>(Arrays.asList(2)), ExtCollectionUtils.subCollection(list, 2, 3, TreeSet::new));
	}

	@Test
	public void testCopy() throws Exception {
		final List<Integer> list = ExtCollectionUtils.toList(1, 2, 3);
		assertTrue(list != ExtCollectionUtils.copy(list));
		assertEquals(list, ExtCollectionUtils.copy(list));

		final Set<Integer> set = ExtCollectionUtils.toSet(1, 2, 3);
		assertTrue(set != ExtCollectionUtils.copy(set));
		assertEquals(set, ExtCollectionUtils.copy(set));

		final Map<Integer, String> map = MapBuilder.createMap(1, "").put(2, "two").put(3, "").put(4, "").build();
		assertTrue(map != ExtCollectionUtils.copy(map));
		assertEquals(map, ExtCollectionUtils.copy(map));
	}

	@Test
	public void testUnmodifiable() throws Exception {
		assertNull(null, ExtCollectionUtils.unmodifiableNavigableMap(null));
		assertNull(null, ExtCollectionUtils.unmodifiableList(null));
		assertNull(null, ExtCollectionUtils.unmodifiableSet(null));
		assertNull(null, ExtCollectionUtils.unmodifiableMap(null));
		assertNull(null, ExtCollectionUtils.unmodifiableSortedMap(null));
	}

	@Test
	public void testSplit() throws Exception {
		final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
		assertEquals(Arrays.asList(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(6, 7, 8, 9, 10), Arrays.asList(11, 12, 13, 14, 15), Arrays.asList(16, 17, 18, 19, 20), Arrays.asList(21, 22)), ExtCollectionUtils.split(list, 5, ArrayList::new));

		assertEquals(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3)), ExtCollectionUtils.split(Arrays.asList(1, 2, 3), 1, ArrayList::new));
		assertEquals(Arrays.asList(Arrays.asList(1)), ExtCollectionUtils.split(Arrays.asList(1), 1, ArrayList::new));
		assertEquals(Arrays.asList(Arrays.asList(1)), ExtCollectionUtils.split(Arrays.asList(1), 10, ArrayList::new));
		assertEquals(Arrays.asList(), ExtCollectionUtils.split(null, 1, ArrayList::new));
		assertEquals(Arrays.asList(), ExtCollectionUtils.split(Arrays.asList(), 1, ArrayList::new));
		assertEquals(Arrays.asList(), ExtCollectionUtils.split(Arrays.asList(1), -10, ArrayList::new));
	}

	private static class BuggyEquals {
		@Override
		public boolean equals(final Object obj) {
			return true;
		}
	}

	@Test
	public void testReferenceEquityComparator() throws Exception {
		final Object o1 = new BuggyEquals();
		final Object o2 = new BuggyEquals();
		final Object o3 = new BuggyEquals();
		assertEquals(0, ExtCollectionUtils.indexOf(Arrays.asList(o1, o2, o3), o2, 0));
		assertEquals(1, ExtCollectionUtils.indexOf(Arrays.asList(o1, o2, o3), o2, 0, new ReferenceEquityComparator<>()));
	}

}
