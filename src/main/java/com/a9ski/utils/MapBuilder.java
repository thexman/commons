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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

/**
 * Utility class allowing method chaining for creation of a map
 *
 * @author Kiril Arabadzhiyski
 *
 * @param <K>
 *            the key class
 * @param <V>
 *            the value clas
 * @param <M>
 *            the map class
 */
public class MapBuilder<K, V, M extends Map<K, V>> {
	private final M map;

	/**
	 * Creates a new map builder with provided <tt>map</tt>
	 *
	 * @param map
	 *            the map
	 */
	public MapBuilder(final M map) {
		if (map == null) {
			throw new IllegalArgumentException("map cannot be null");
		}
		this.map = map;
	}

	/**
	 * Puts a new key and value in the map
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the map builder
	 */
	public MapBuilder<K, V, M> put(final K key, final V value) {
		map.put(key, value);
		return this;
	}

	/**
	 * Puts a new key and value in the map, if the key is absend
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the map builder
	 */
	public MapBuilder<K, V, M> putIfAbsent(final K key, final V value) {
		map.putIfAbsent(key, value);
		return this;
	}

	/**
	 * Removes a key and value from the map
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the map builder
	 */
	public MapBuilder<K, V, M> remove(final K key, final V value) {
		map.remove(key, value);
		return this;
	}

	/**
	 * Removes a key from the map
	 *
	 * @param key
	 *            the key
	 * @return the map builder
	 */
	public MapBuilder<K, V, M> remove(final K key) {
		map.remove(key);
		return this;
	}

	/**
	 * Clears the map
	 *
	 * @return the map builder
	 */
	public MapBuilder<K, V, M> clear() {
		map.clear();
		return this;
	}

	/**
	 * Puts all values of <tt>m</tt> into the map
	 *
	 * @param m
	 *            the map to be put
	 * @return the map builder
	 */
	public MapBuilder<K, V, M> putAll(final Map<K, V> m) {
		if (m != null) {
			map.putAll(m);
		}
		return this;
	}

	/**
	 * Returns the map
	 *
	 * @return the map
	 */
	public M build() {
		return map;
	}

	/**
	 * Creates a new map builder
	 *
	 * @param map
	 *            the map used to store values
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 * @param <M>
	 *            map type
	 * @return new map builder
	 */
	public static <K, V, M extends Map<K, V>> MapBuilder<K, V, M> create(final M map) {
		return new MapBuilder<>(map);
	}

	/**
	 * Create new map builder for HashMap and stores a new entry
	 *
	 * @param key
	 *            the key of the new entry
	 * @param value
	 *            the value of the new entry
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 * @return new map builder for HashMap
	 */
	public static <K, V> MapBuilder<K, V, Map<K, V>> createMap(final K key, final V value) {
		return create((Map<K, V>) new HashMap<K, V>()).put(key, value);
	}

	/**
	 * Create new map builder for HashMap and stores a new entry
	 *
	 * @param key
	 *            the key of the new entry
	 * @param value
	 *            the value of the new entry
	 * @param mapFactory
	 *            factory used to create the map
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 * @param <M>
	 *            map type
	 *
	 * @return new map builder for HashMap
	 */
	public static <K, V, M extends Map<K, V>> MapBuilder<K, V, M> createMap(final K key, final V value, final Supplier<M> mapFactory) {
		return create(mapFactory.get()).put(key, value);
	}

	/**
	 * Create new map builder for HashMap and stores a new entry
	 *
	 * @param key
	 *            the key of the new entry
	 * @param value
	 *            the value of the new entry
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 * @return new map builder for HashMap
	 */
	public static <K, V> MapBuilder<K, V, HashMap<K, V>> createHashMap(final K key, final V value) {
		return create(new HashMap<K, V>()).put(key, value);
	}

	/**
	 * Create new map builder for TreeMap and stores a new entry
	 *
	 * @param key
	 *            the key of the new entry
	 * @param value
	 *            the value of the new entry
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 * @return new map builder for TreeMap
	 */
	public static <K, V> MapBuilder<K, V, TreeMap<K, V>> createTreeMap(final K key, final V value) {
		return create(new TreeMap<K, V>()).put(key, value);
	}

	/**
	 * Create new map builder for LinkedHashMap and stores a new entry
	 *
	 * @param key
	 *            the key of the new entry
	 * @param value
	 *            the value of the new entry
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 * @return new map builder for LinkedHashMap
	 */
	public static <K, V> MapBuilder<K, V, LinkedHashMap<K, V>> createLinkedHashMap(final K key, final V value) {
		return create(new LinkedHashMap<K, V>()).put(key, value);
	}
}
