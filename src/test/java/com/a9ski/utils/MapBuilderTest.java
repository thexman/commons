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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapBuilderTest {

	@Test
	public void testPut() {
		final Map<Integer, String> map = MapBuilder.createMap(1, "xxx").put(1, "one").put(2, "two").build();
		checkMap(map);
	}

	private void checkMap(final Map<Integer, String> map) {
		assertNotNull(map);
		assertEquals(2, map.size());
		assertEquals("one", map.get(1));
		assertEquals("two", map.get(2));
		map.put(0, "zero"); // ensure not read-only
	}

	@Test
	public void testPutIfAbsent() {
		final Map<Integer, String> map = MapBuilder.createMap(1, "one").putIfAbsent(1, "xxx").putIfAbsent(2, "two").build();
		checkMap(map);
	}

	@Test
	public void testRemove() {
		final Map<Integer, String> map = MapBuilder.createMap(3, "three").put(1, "one").put(2, "two").put(0, "zero").remove(1, "xxx").remove(3, "three").remove(0).build();
		checkMap(map);
	}

	@Test
	public void testClear() {
		final Map<Integer, String> map = MapBuilder.createMap(3, "three").put(0, "zero").clear().put(1, "one").put(2, "two").build();
		checkMap(map);
	}

	@Test
	public void testPutAll() {
		final Map<Integer, String> map1 = MapBuilder.createMap(2, "two").put(0, "zero").build();
		final Map<Integer, String> map2 = MapBuilder.createMap(1, "one").putAll(map1).remove(0).build();
		checkMap(map2);
	}

	@Test
	public void testBuild() {
		final MapBuilder<Integer, String, Map<Integer, String>> mb = MapBuilder.createMap(3, "three");
		mb.build();
		final Map<Integer, String> map1 = mb.put(0, "zero").build();
		assertTrue(map1 == mb.build());
		final Map<Integer, String> map2 = MapBuilder.create(map1).clear().put(1, "one").put(2, "two").build();
		assertTrue(map1 == map2);
		checkMap(map2);
	}

	@Test
	public void testCreateMap() {
		final Map<Integer, String> map1 = MapBuilder.createMap(1, "one").put(2, "two").build();
		checkMap(map1);

		final TreeMap<Integer, String> map2 = MapBuilder.createMap(1, "one", TreeMap::new).put(2, "two").build();
		checkMap(map2);
	}

	@Test
	public void testCreateHashMap() {
		final HashMap<Integer, String> map2 = MapBuilder.createHashMap(1, "one").put(2, "two").build();
		checkMap(map2);
	}

	@Test
	public void testCreateTreeMap() {
		final TreeMap<Integer, String> map2 = MapBuilder.createTreeMap(1, "one").put(2, "two").build();
		checkMap(map2);
	}

	@Test
	public void testCreateLinkedHashMap() {
		final LinkedHashMap<Integer, String> map2 = MapBuilder.createLinkedHashMap(1, "one").put(2, "two").build();
		checkMap(map2);
	}

}
