package com.a9ski.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExtCollectionUtils {

	/**
	 * Creates a new equity comparator that uses {@link java.util.Objects#equals}
	 *
	 * @return new equity comparator that uses {@link java.util.Objects#equals}
	 */
	public static <V> EquityComparator<V> equalsEquityComparator() {
		return (a, b) -> Objects.equals(a, b);
	}

	/**
	 * Returns either the original <tt>collection</tt> or the a newly created ArrayList if original <tt>collection</tt> is null
	 *
	 * @param collection
	 *            the collection to be returned
	 * @return the collection or a newly created ArrayList, if <tt>collection</tt> is null
	 */
	public static <V> Collection<V> defaultCollection(final Collection<V> collection) {
		return (collection == null ? new ArrayList<>() : collection);
	}

	/**
	 * Returns either the original <tt>collection</tt> or the a newly created ArrayList if original <tt>collection</tt> is null
	 *
	 * @param collection
	 *            the collection to be returned
	 * @return the collection or a newly created ArrayList, if <tt>collection</tt> is null
	 */
	public static <V, C extends Collection<V>> C defaultCollection(final C collection, final C defaultCollection) {
		return (collection == null ? defaultCollection : collection);
	}

	/**
	 * Returns either the original <tt>list</tt> or the a newly created ArrayList if original <tt>list</tt> is null
	 *
	 * @param list
	 *            the list to be returned
	 * @return the list or a newly created ArrayList, if <tt>list</tt> is null
	 */
	public static <D> ArrayList<D> defaultArrayList(final ArrayList<D> list) {
		return (list == null ? new ArrayList<>() : list);
	}

	/**
	 * Returns either the original <tt>list</tt> or the a newly created ArrayList if original <tt>list</tt> is null
	 *
	 * @param list
	 *            the list to be returned
	 * @return the list or a newly created ArrayList, if <tt>list</tt> is null
	 */
	public static <D> LinkedList<D> defaultLinkedList(final LinkedList<D> list) {
		return (list == null ? new LinkedList<>() : list);
	}

	/**
	 * Returns either the original <tt>list</tt> or the a newly created ArrayList if original <tt>list</tt> is null
	 *
	 * @param list
	 *            the list to be returned
	 * @return the list or a newly created ArrayList, if <tt>list</tt> is null
	 */
	public static <D> List<D> defaultList(final List<D> list) {
		return (list == null ? new ArrayList<>() : list);
	}

	/**
	 * Returns either the original <tt>map</tt> or the a newly created HashMap if original <tt>map</tt> is null
	 *
	 * @param map
	 *            the map to be returned
	 * @return the map or a newly created HashMap, if <tt>map</tt> is null
	 */
	public static <K, V> Map<K, V> defaultMap(final Map<K, V> map) {
		return (map == null ? new HashMap<>() : map);
	}

	/**
	 * Returns either the original <tt>map</tt> or the a newly created TreeMap if original <tt>map</tt> is null
	 *
	 * @param map
	 *            the map to be returned
	 * @return the map or a newly created TreeMap, if <tt>map</tt> is null
	 */
	public static <K, V> TreeMap<K, V> defaultTreeMap(final TreeMap<K, V> map) {
		return (map == null ? new TreeMap<>() : map);
	}

	/**
	 * Returns either the original <tt>map</tt> or the a newly created LinkedHashMap if original <tt>map</tt> is null
	 *
	 * @param map
	 *            the map to be returned
	 * @return the map or a newly created LinkedHashMap, if <tt>map</tt> is null
	 */
	public static <K, V> LinkedHashMap<K, V> defaultLinkedHashMap(final LinkedHashMap<K, V> map) {
		return (map == null ? new LinkedHashMap<>() : map);
	}

	/**
	 * Returns either the original <tt>map</tt> or the a newly created HashMap if original <tt>map</tt> is null
	 *
	 * @param map
	 *            the map to be returned
	 * @return the map or a newly created HashMap, if <tt>map</tt> is null
	 */
	public static <K, V> HashMap<K, V> defaultHashMap(final HashMap<K, V> map) {
		return (map == null ? new HashMap<>() : map);
	}

	/**
	 * Clears collection
	 *
	 * @param collection
	 *            the collection to be cleared
	 * @return the <tt>collection</tt>
	 */
	public <V, C extends Collection<V>> C clear(final C collection) {
		if (collection != null) {
			collection.clear();
		}
		return collection;
	}

	/**
	 * Combines a non null values of a map of collections into a new single collection
	 *
	 * @param map
	 *            the map of collections
	 * @param collectionFactory
	 *            the new collection factory
	 * @return a new collection that contains all non null values of the map
	 */
	public static <V, C extends Collection<V>> C combineValues(final Map<?, ? extends Collection<V>> map, final Supplier<C> collectionFactory) {
		if (map != null) {
			return combine(map.values(), collectionFactory);
		} else {
			return collectionFactory.get();
		}
	}

	/**
	 * Combines a non null values of a collection-of-collections into a new single collection
	 *
	 * @param collection
	 *            the collection-of-collections
	 * @param collectionFactory
	 *            the new collection factory
	 * @return a new collection that contains all non null values of collection-of-collections
	 */
	public static <V, C extends Collection<V>> C combine(final Collection<? extends Collection<V>> collection, final Supplier<C> collectionFactory) {
		final C allValues = collectionFactory.get();
		defaultCollection(collection).stream().filter(s -> s != null).forEach(s -> allValues.addAll(s));
		return allValues;
	}

	/**
	 * Combines a non null values of a collection-of-collections into a new single collection
	 *
	 * @param collectionFactory
	 *            the new collection factory
	 * @param collections
	 *            the collection-of-collections
	 * @return a new collection that contains all non null values of collection-of-collections
	 */
	@SafeVarargs
	public static <V, C extends Collection<V>> C combine(final Supplier<C> collectionFactory, final Collection<V>... collections) {
		if (collections != null) {
			return combine(Arrays.asList(collections), collectionFactory);
		} else {
			return collectionFactory.get();
		}
	}

	/**
	 * Replaces null values inside the map with <tt>newNullValue</tt>
	 *
	 * @param map
	 *            the map
	 * @param newNullValue
	 *            the new value to be set
	 */
	public static <K, V> void replaceNullValuesWith(final Map<K, V> map, final V newNullValue) {
		//@formatter:off
		map.entrySet().stream()
			.filter(entry -> entry.getValue() == null)
			.forEach(entry -> entry.setValue(newNullValue));
		//@formatter:on
	}

	/**
	 * Gets the size of the collection/array/iterator/map
	 *
	 * @param object
	 *            the collection/array/iterator/map
	 * @return the size of the collection/array/iterator/map
	 */
	public static int size(final Object object) {
		int total = 0;
		if (object instanceof Map) {
			total = ((Map<?, ?>) object).size();
		} else if (object instanceof Collection) {
			total = ((Collection<?>) object).size();
		} else if (object instanceof Object[]) {
			total = ((Object[]) object).length;
		} else if (object instanceof Iterator) {
			final Iterator<?> it = (Iterator<?>) object;
			while (it.hasNext()) {
				total++;
				it.next();
			}
		} else if (object instanceof Enumeration) {
			final Enumeration<?> it = (Enumeration<?>) object;
			while (it.hasMoreElements()) {
				total++;
				it.nextElement();
			}
		} else if (object == null) {
			total = 0;
		} else {
			try {
				total = Array.getLength(object);
			} catch (final IllegalArgumentException ex) {
				throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName(), ex);
			}
		}
		return total;
	}

	/**
	 * Gets the first element. Returns null if the collection is empty or null
	 *
	 * @param collection
	 *            the collection
	 * @return Gets the first element. Returns null if the collection is empty or null
	 */
	public static <V> V get0(final Collection<V> collection) {
		if (isNotEmpty(collection)) {
			return collection.iterator().next();
		}
		return null;
	}

	/**
	 * Gets the Nth element. Returns the <tt>defaultValue</tt> if the list is empty OR the element doesn't exists or is null
	 *
	 * @param collection
	 *            the list
	 * @param index
	 *            the elementh index
	 * @param defaultValue
	 *            the default value if the elmenth is null or the index doesn't exists
	 * @return Gets the Nth element. Returns the <tt>defaultValue</tt> if the list is empty OR the element doesn't exists or is null
	 */
	public static <V> V getN(final Collection<V> collection, final int index, final V defaultValue) {
		if (index < 0) {
			throw new IllegalArgumentException("Expecting index to be greater or equals to 0");
		}
		int ind = index;
		V result = defaultValue;
		if (collection != null) {
			final Iterator<V> it = collection.iterator();
			while (ind > 0 && it.hasNext()) {
				it.next();
				ind--;
			}
			if (it.hasNext()) {
				result = it.next();
			}
		}
		return result;
	}

	/**
	 * Sets the Nth element. If the index is greater than the list size, elements with <tt>defaultValue</tt> are added
	 *
	 * @param list
	 *            the list
	 * @param index
	 *            the elementh index
	 * @param value
	 *            the value of the elementh
	 * @param defaultValue
	 *            the default value for the elements added between list <tt>size</tt> and <tt>index</tt>
	 */
	public static <V> void setN(final List<V> list, final int index, final V value, final V defaultValue) {
		if (list != null) {
			while (list.size() <= index) {
				list.add(defaultValue);
			}
			list.set(index, value);
		}
	}

	/**
	 * Sets the list first element.
	 *
	 * @param list
	 *            the list
	 * @param element
	 *            element to be added
	 */
	public static <V> void set0(final List<V> list, final V element) {
		if (list != null) {
			list.set(0, element);
		}
	}

	/**
	 * Returns a factory that creates TreeSets with give comparator
	 *
	 * @param cmp
	 *            the comparator for the newly created TreeSets
	 * @return factory that creates TreeSets with give comparator
	 */
	public static <V> Supplier<TreeSet<V>> treeSetSupplier(final Comparator<V> cmp) {
		return () -> (new TreeSet<>(cmp));
	}

	/**
	 * Creates a new collection populated with the <tt>values</tt> or <tt>emptyCollection</tt> if <tt>values</tt> is null
	 *
	 * @param values
	 *            the initial values
	 * @param collectionFactory
	 *            the new collection factory
	 * @param emptyCollection
	 *            the collection returned if <tt>values</tt> is null
	 * @return a new collection populated with the <tt>values</tt> or <tt>emptyCollection</tt> if <tt>values</tt> is null
	 */
	public static <V, C extends Collection<V>> C collection(final Collection<V> values, final Supplier<C> collectionFactory, final C emptyCollection) {
		if (values != null) {
			final C c = collectionFactory.get();
			c.addAll(defaultCollection(values));
			return c;
		} else {
			return emptyCollection;
		}
	}

	/**
	 * Creates a new collection populated with the <tt>values</tt>
	 *
	 * @param values
	 *            the initial values
	 * @param collectionFactory
	 *            the new collection factory
	 * @return a new collection populated with the <tt>values</tt>
	 */
	public static <V, C extends Collection<V>> C collection(final Collection<V> values, final Supplier<C> collectionFactory) {
		return collection(values, collectionFactory, collectionFactory.get());
	}

	/**
	 * Creates a new collection populated with the <tt>values</tt>
	 *
	 * @param collectionFactory
	 *            the new collection factory
	 * @param values
	 *            the initial values
	 * @return a new collection populated with the <tt>values</tt>
	 */
	@SafeVarargs
	public static <V, C extends Collection<V>> C collection(final Supplier<C> collectionFactory, final V... values) {
		if (values != null) {
			return collection(Arrays.asList(values), collectionFactory);
		} else {
			return collectionFactory.get();
		}
	}

	/**
	 * Creates a new collection populated with the <tt>values</tt> or <tt>emptyCollection</tt> if <tt>values</tt> is null
	 *
	 * @param collectionFactory
	 *            the new collection factory
	 * @param values
	 *            the initial values
	 * @param emptyCollection
	 *            the collection returned if <tt>values</tt> is null
	 * @return a new collection populated with the <tt>values</tt> or <tt>emptyCollection</tt> if <tt>values</tt> is null
	 */
	@SafeVarargs
	public static <V, C extends Collection<V>> C collection(final Supplier<C> collectionFactory, final C emptyCollection, final V... values) {
		if (values != null) {
			return collection(Arrays.asList(values), collectionFactory);
		} else {
			return emptyCollection;
		}
	}

	/**
	 * Returns a HashSet initialized with <tt>items</tt>
	 *
	 * @param items
	 *            the values of the set
	 * @return HashSet initialized with <tt>items</tt>
	 */
	@SafeVarargs
	public static <V> Set<V> toSet(final V... items) {
		return collection(HashSet::new, new HashSet<>(), items);
	}

	/**
	 * Returns a HashSet initialized with <tt>items</tt>
	 *
	 * @param items
	 *            the values of the set
	 * @return HashSet initialized with <tt>items</tt>
	 */
	public static <V> Set<V> toSet(final Collection<V> items) {
		return collection(items, HashSet::new, new HashSet<>());
	}

	/**
	 * Returns a ArrayList initialized with <tt>items</tt>
	 *
	 * @param items
	 *            the values of the set
	 * @return ArrayList initialized with <tt>items</tt>
	 */
	@SafeVarargs
	public static <V> List<V> toList(final V... items) {
		return collection(ArrayList::new, new ArrayList<>(), items);
	}

	/**
	 * Returns a HashSet initialized with <tt>items</tt>
	 *
	 * @param items
	 *            the values of the set
	 * @return HashSet initialized with <tt>items</tt>
	 */
	public static <V> List<V> toList(final Collection<V> items) {
		return collection(items, ArrayList::new, new ArrayList<>());
	}

	/**
	 * Null-safe check if a map is not empty
	 * <p>
	 * Null returns true.
	 *
	 * @param map
	 *            map to be checked
	 * @return true if map is not empty
	 */
	public static boolean isNotEmpty(final Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * Null-safe check if a map is empty
	 * <p>
	 * Null returns true.
	 *
	 * @param map
	 *            map to be checked
	 * @return true if the map is empty or null
	 */
	public static boolean isEmpty(final Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

	/**
	 * Null-safe check if the specified collection is empty.
	 * <p>
	 * Null returns true.
	 *
	 * @param coll
	 *            the collection to check, may be null
	 * @return true if empty or null
	 */
	public static boolean isEmpty(final Collection<?> coll) {
		return coll == null || coll.isEmpty();
	}

	/**
	 * Null-safe check if the specified collection is not empty.
	 * <p>
	 * Null returns false.
	 *
	 * @param coll
	 *            the collection to check, may be null
	 * @return true if non-null and non-empty
	 */
	public static boolean isNotEmpty(final Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * Returns the index of the <tt><element</tt>
	 *
	 * @param list
	 *            the list to be checked
	 * @param element
	 *            element searched for
	 * @param comparator
	 *            comparator used to compare equity
	 * @return the index of the element or -1 if there is no such element
	 */
	public static <D> int indexOf(final Collection<D> list, final D element, final int startIndex, final EquityComparator<D> comparator) {
		final Iterator<D> it = defaultCollection(list).iterator();
		int found = -1;
		int ind = -1;
		while (it.hasNext()) {
			ind++;
			final D e = it.next();
			if (ind >= startIndex) {
				if (comparator.equals(element, e)) {
					found = ind;
					break;
				}
			}
		}
		return found;
	}

	/**
	 * Returns the index of the <tt><element</tt> using {@link ExtCollectionUtils#equalsEquityComparator()}
	 *
	 * @param list
	 *            the list to be searched
	 * @param element
	 *            element searched for
	 * @return the index of the element or -1 if there is no such element
	 */
	public static <D> int indexOf(final Collection<D> list, final D element, final int startIndex) {
		return indexOf(list, element, startIndex, equalsEquityComparator());
	}

	/**
	 * Returns a true if <tt>list</tt> contains <tt><element</tt>
	 *
	 * @param list
	 *            the list to be checked
	 * @param element
	 *            element searched for
	 * @param comparator
	 *            comparator used to compare equity
	 * @return true if <tt>list</tt> contains <tt><element</tt>
	 */
	public static <D> List<Integer> allIndexesOf(final Collection<D> list, final D element, final int startIndex, final EquityComparator<D> comparator) {
		final List<Integer> indexes = new ArrayList<>();
		int ind = startIndex - 1;
		while ((ind = indexOf(list, element, ind + 1, comparator)) != -1) {
			indexes.add(ind);
		}
		return indexes;
	}

	/**
	 * Returns a true if <tt>list</tt> contains <tt><element</tt>
	 *
	 * @param list
	 *            the list to be checked
	 * @param element
	 *            element searched for
	 * @param comparator
	 *            comparator used to compare equity
	 * @return true if <tt>list</tt> contains <tt><element</tt>
	 */
	public static <D> List<Integer> allIndexesOf(final Collection<D> list, final D element, final int startIndex) {
		return allIndexesOf(list, element, startIndex, equalsEquityComparator());
	}

	/**
	 * Returns a true if <tt>list</tt> contains <tt><element</tt>
	 *
	 * @param list
	 *            the list to be checked
	 * @param element
	 *            element to be searched for
	 * @param comparator
	 *            comparator used to compare equity
	 * @return true if <tt>list</tt> contains <tt><element</tt>
	 */
	public static <D> boolean contains(final Collection<D> list, final D element, final EquityComparator<D> comparator) {
		return indexOf(list, element, 0, comparator) != -1;
	}

	/**
	 * Returns a true if <tt>list</tt> contains <tt><element</tt> using {@link ExtCollectionUtils#equalsEquityComparator()}
	 *
	 * @param list
	 *            the list to be checked
	 * @param element
	 *            element to be searched for
	 * @return true if <tt>list</tt> contains <tt><element</tt>
	 */
	public static <D> boolean contains(final Collection<D> list, final D element) {
		return contains(list, element, equalsEquityComparator());
	}

	/**
	 * Returns the index of the <tt><element</tt>
	 *
	 * @param list
	 *            the list to be checked
	 * @param element
	 *            element searched for
	 * @param comparator
	 *            comparator used to compare equity
	 * @return the index of the element or -1 if there is no such element
	 */
	public static <D> D removeIndex(final Collection<D> list, final int index, final EquityComparator<D> comparator) {
		final Iterator<D> it = defaultCollection(list).iterator();
		D found = null;
		int ind = -1;
		while (it.hasNext()) {
			ind++;
			final D element = it.next();
			if (ind == index) {
				it.remove();
				found = element;
				break;
			}
		}
		return found;
	}

	/**
	 * Gets map values for each key
	 *
	 * @param map
	 *            the map
	 * @param keys
	 *            collection of keys
	 * @return list containing map values of each key
	 */
	public static <K, V> List<V> getMapValues(final Map<K, V> map, final Collection<K> keys) {
		if (isNotEmpty(map) && isNotEmpty(keys)) {
			return keys.stream().filter(k -> map.containsKey(k)).map(k -> map.get(k)).collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * Gets map values for each key
	 *
	 * @param map
	 *            the map
	 * @param keys
	 *            collection of keys
	 * @return list containing map values of each key
	 */
	@SafeVarargs
	public static <K, V> List<V> getMapValues(final Map<K, V> map, final K... keys) {
		return getMapValues(map, toList(keys));
	}

	/**
	 * Changes values that are equals to <tt>oldValue</tt> with <tt>newValue</tt>
	 *
	 * @param map
	 *            maps whose values are changed
	 * @param oldValue
	 *            the old value
	 * @param newValue
	 *            the new value
	 * @return
	 * @return returns the provided map <tt>map</tt>
	 */
	public static <K, V> Map<K, V> changeMapValues(final Map<K, V> map, final V oldValue, final V newValue, final EquityComparator<V> comparator) {
		if (isNotEmpty(map)) {
			map.entrySet().stream().filter(e -> comparator.equals(e.getValue(), oldValue)).forEach(e -> e.setValue(newValue));
		}
		return map;
	}

	/**
	 * Changes values that are equals to <tt>oldValue</tt> with <tt>newValue</tt>
	 *
	 * @param map
	 *            maps whose values are changed
	 * @param oldValue
	 *            the old value
	 * @param newValue
	 *            the new value
	 * @return
	 * @return returns the provided map <tt>map</tt>
	 */
	public static <K, V> Map<K, V> changeMapValues(final Map<K, V> map, final V oldValue, final V newValue) {
		return changeMapValues(map, oldValue, newValue, equalsEquityComparator());
	}

	/**
	 * Removes all values that are equal to <tt>values</tt>
	 *
	 * @param map
	 *            the map
	 * @param values
	 *            the values to be removed
	 * @param comparator
	 *            equity comparator
	 * @return the provided map <tt>map</tt>
	 */
	public static <K, V> Map<K, V> removeMapValues(final Map<K, V> map, final Collection<V> values, final EquityComparator<V> comparator) {
		if (isNotEmpty(map) && isNotEmpty(values)) {
			for (final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator(); it.hasNext();) {
				final Map.Entry<K, V> e = it.next();
				if (contains(values, e.getValue(), comparator)) {
					it.remove();
				}
			}
		}
		return map;
	}

	/**
	 * Removes all values that are equal to <tt>values</tt>
	 *
	 * @param map
	 *            the map
	 * @param values
	 *            the values to be removed
	 * @return the provided map <tt>map</tt>
	 */
	public static <K, V> Map<K, V> removeMapValues(final Map<K, V> map, final Collection<V> values) {
		return removeMapValues(map, values, equalsEquityComparator());
	}

	/**
	 * Removes all values that are equal to <tt>values</tt>
	 *
	 * @param map
	 *            the map
	 * @param values
	 *            the values to be removed
	 * @return the provided map <tt>map</tt>
	 */
	@SafeVarargs
	public static <K, V> Map<K, V> removeMapValues(final Map<K, V> map, final V... values) {
		return removeMapValues(map, toList(values));
	}

	/**
	 * Adds all elements from <tt>dest</tt> to <tt>src</tt>. if src is null and <tt>collectionFactory</tt> is not null a new collection is created
	 *
	 * @param dest
	 *            destination collection
	 * @param src
	 *            source collection
	 * @param collectionFactory
	 *            collection factory to be used when <tt>dest</tt> is null. Could be null, then no collection will be created
	 * @return <tt>dest</tt> if <tt>dest</tt> is not null OR newly created collection if <tt>collectionFactory</tt> is not null. Returns null otherwise (dest is null and collectionFactory is null)
	 */
	public static <V, C extends Collection<V>> C addAll(C dest, final Collection<V> src, final Supplier<C> collectionFactory) {
		if (isNotEmpty(src) && (dest != null || collectionFactory != null)) {
			if (dest == null) {
				dest = collectionFactory.get();
			}
			dest.addAll(src);
		}
		return dest;
	}

	/**
	 * Returns a new {@link Collection} containing <tt><i>a</i> - <i>b</i></tt>.
	 *
	 * @param a
	 *            the collection to subtract from (if null empty list is returned)
	 * @param b
	 *            the collection to subtract (may be null, then a new list equals to <tt><i>a</i></tt> is returned)
	 * @param comparator
	 *            comparator used to check for equity
	 * @return a new collection with the results
	 * @see Collection#removeAll
	 */
	public static <D> List<D> subtract(final Collection<D> a, final Collection<D> b, final EquityComparator<D> comparator) {
		final List<D> result = new ArrayList<>();
		if (a != null) {
			result.addAll(a.stream().filter(e -> !contains(b, e, comparator)).collect(Collectors.toList()));

		}
		return result;
	}

	/**
	 * Returns a new {@link Collection} containing <tt><i>a</i> - <i>b</i></tt>. Comparison is using {@link ExtCollectionUtils#equalsEquityComparator()}.
	 *
	 * @param a
	 *            the collection to subtract from (if null empty list is returned)
	 * @param b
	 *            the collection to subtract (may be null, then a new list equals to <tt><i>a</i></tt> is returned)
	 * @return a new collection with the results
	 * @see Collection#removeAll
	 */
	public static <D> List<D> subtract(final Collection<D> a, final Collection<D> b) {
		return subtract(a, b, equalsEquityComparator());
	}

	/**
	 * Remove all occurrences of <tt>element</tt> in collection.
	 *
	 * @param collection
	 *            collection
	 * @param element
	 *            element to be removed
	 * @param comparator
	 *            equity comparator
	 * @return the <tt>collection</tt>
	 */
	public static <D, C extends Collection<D>> C removeAll(final C collection, final D element, final EquityComparator<D> comparator) {
		int ind = 0;
		while ((ind = indexOf(collection, element, ind, comparator)) != -1) {
			removeIndex(collection, ind, comparator);
		}
		return collection;
	}

	/**
	 * Remove all occurrences of <tt>element</tt> in collection.
	 *
	 * @param collection
	 *            collection
	 * @param element
	 *            element to be removed
	 * @return the <tt>collection</tt>
	 */
	public static <D, C extends Collection<D>> C removeAll(final C collection, final D element) {
		return removeAll(collection, element, equalsEquityComparator());
	}

	/**
	 * Removes all <tt>elementsToRemove</tt> from <tt>dest</tt>
	 *
	 * @param dest
	 *            destination where the elements are removed
	 * @param elementsToRemove
	 *            elements to be removed
	 * @param comparator
	 *            equity comparator
	 * @return <tt>dest</tt>
	 */
	public static <V, C extends Collection<V>> C removeAll(final C dest, final C elementsToRemove, final EquityComparator<V> comparator) {
		if (isNotEmpty(elementsToRemove)) {
			if (dest != null) {
				elementsToRemove.forEach(e -> removeAll(dest, e, comparator));
			}
		}
		return dest;
	}

	/**
	 * Removes all <tt>elementsToRemove</tt> from <tt>dest</tt>
	 *
	 * @param dest
	 *            destination where the elements are removed
	 * @param elementsToRemove
	 *            elements to be removed
	 * @return <tt>dest</tt>
	 */
	public static <V, C extends Collection<V>> C removeAll(final C dest, final C elementsToRemove) {
		return removeAll(dest, elementsToRemove, equalsEquityComparator());
	}

	/**
	 * Puts value in map. If the map is null, no operation is performed.
	 *
	 * @param map
	 *            the map
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the map
	 */
	public static <K, V, M extends Map<K, V>> M put(final M map, final K key, final V value) {
		if (map != null) {
			map.put(key, value);
		}
		return map;
	}

	/**
	 * Returns a new collection containing the portion of the provided <tt>collection</tt> between the specified <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive. If <tt>fromIndex</tt> and <tt>toIndex</tt> are equal or the range is invalid, the returned list is empty.
	 *
	 * @param collection
	 *            the collection which will be used for creating the sublists
	 * @param fromIndex
	 *            start index inclusive
	 * @param toIndex
	 *            end index exclusive
	 * @param collectionFactory
	 *            collection factory used to create the returned collection
	 * @return a new collection containing the values between <tt>fromIndex</tt> (inclusive) and <tt>toIndex</tt> (exclusive).
	 */
	public static <V, C extends Collection<V>> C subCollection(final Collection<V> collection, final int fromIndex, final int toIndex, final Supplier<C> collectionFactory) {
		final C sub = collectionFactory.get();
		if (collection != null) {
			final Iterator<V> it = collection.iterator();
			int ind = 0;
			while (it.hasNext() && ind < toIndex) {
				final V value = it.next();
				if (ind >= fromIndex && ind < toIndex) {
					sub.add(value);
				}
				ind++;
			}
		}
		return sub;
	}

	/**
	 * Creates a copy of the list (ArrayList)
	 *
	 * @param list
	 *            the list to be copied
	 * @return an ArrayList copied from the <tt>list</tt>
	 */
	public static <T> List<T> copy(final List<T> list) {
		final List<T> result;
		if (list != null) {
			result = new ArrayList<>(list);
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * Creates a copy of the set (HashSet)
	 *
	 * @param set
	 *            the set to be copied
	 * @return an HashSet copied from the <tt>set</tt>
	 */
	public static <T> Set<T> copy(final Set<T> set) {
		final Set<T> result;
		if (set != null) {
			result = new HashSet<>(set);
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * Creates a copy of the map (HashMap)
	 *
	 * @param map
	 *            the map to be copied
	 * @return an HashMap copied from the <tt>map</tt>
	 */
	public static <K, V> Map<K, V> copy(final Map<K, V> map) {
		final Map<K, V> result;
		if (map != null) {
			result = new HashMap<>(map);
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * Returns an unmodifiable view of the specified list. This method allows modules to provide users with "read-only" access to internal lists. Query operations on the returned list "read through" to the specified list, and attempts to modify the returned list, whether direct or via its iterator,
	 * result in an <tt>UnsupportedOperationException</tt>.
	 * <p>
	 *
	 * The returned list will be serializable if the specified list is serializable. Similarly, the returned list will implement {@link RandomAccess} if the specified list does.
	 *
	 * @param <T>
	 *            the class of the objects in the list
	 * @param list
	 *            the list for which an unmodifiable view is to be returned.
	 * @return an unmodifiable view of the specified list.
	 */
	public static <T> List<T> unmodifiableList(final List<T> list) {
		if (list != null) {
			return Collections.unmodifiableList(list);
		} else {
			return null;
		}
	}

	/**
	 * Returns an unmodifiable view of the specified set. This method allows modules to provide users with "read-only" access to internal sets. Query operations on the returned set "read through" to the specified set, and attempts to modify the returned set, whether direct or via its iterator,
	 * result in an <tt>UnsupportedOperationException</tt>.
	 * <p>
	 *
	 * The returned set will be serializable if the specified set is serializable.
	 *
	 * @param <T>
	 *            the class of the objects in the set
	 * @param sset
	 *            the set for which an unmodifiable view is to be returned.
	 * @return an unmodifiable view of the specified set.
	 */
	public static <T> Set<T> unmodifiableSet(final Set<T> set) {
		if (set != null) {
			return Collections.unmodifiableSet(set);
		} else {
			return null;
		}
	}

	/**
	 * Returns an unmodifiable view of the specified map. This method allows modules to provide users with "read-only" access to internal maps. Query operations on the returned map "read through" to the specified map, and attempts to modify the returned map, whether direct or via its collection
	 * views, result in an <tt>UnsupportedOperationException</tt>.
	 * <p>
	 *
	 * The returned map will be serializable if the specified map is serializable.
	 *
	 * @param <K>
	 *            the class of the map keys
	 * @param <V>
	 *            the class of the map values
	 * @param map
	 *            the map for which an unmodifiable view is to be returned.
	 * @return an unmodifiable view of the specified map.
	 */
	public static <K, V> Map<K, V> unmodifiableMap(final Map<K, V> map) {
		if (map != null) {
			return Collections.unmodifiableMap(map);
		} else {
			return null;
		}
	}

	/**
	 * Returns an unmodifiable view of the specified sorted map. This method allows modules to provide users with "read-only" access to internal sorted maps. Query operations on the returned sorted map "read through" to the specified sorted map. Attempts to modify the returned sorted map, whether
	 * direct, via its collection views, or via its <tt>subMap</tt>, <tt>headMap</tt>, or <tt>tailMap</tt> views, result in an <tt>UnsupportedOperationException</tt>.
	 * <p>
	 *
	 * The returned sorted map will be serializable if the specified sorted map is serializable.
	 *
	 * @param <K>
	 *            the class of the map keys
	 * @param <V>
	 *            the class of the map values
	 * @param map
	 *            the sorted map for which an unmodifiable view is to be returned.
	 * @return an unmodifiable view of the specified sorted map.
	 */
	public static <K, V> SortedMap<K, V> unmodifiableSortedMap(final SortedMap<K, V> map) {
		if (map != null) {
			return Collections.unmodifiableSortedMap(map);
		} else {
			return null;
		}
	}

	/**
	 * Returns an unmodifiable view of the specified navigable map. This method allows modules to provide users with "read-only" access to internal navigable maps. Query operations on the returned navigable map "read through" to the specified navigable map. Attempts to modify the returned navigable
	 * map, whether direct, via its collection views, or via its {@code subMap}, {@code headMap}, or {@code tailMap} views, result in an {@code UnsupportedOperationException}.
	 * <p>
	 *
	 * The returned navigable map will be serializable if the specified navigable map is serializable.
	 *
	 * @param <K>
	 *            the class of the map keys
	 * @param <V>
	 *            the class of the map values
	 * @param map
	 *            the navigable map for which an unmodifiable view is to be returned
	 * @return an unmodifiable view of the specified navigable map
	 * @since 1.8
	 */
	public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(final NavigableMap<K, V> map) {
		if (map != null) {
			return Collections.unmodifiableNavigableMap(map);
		} else {
			return null;
		}
	}

	/**
	 * Creates intersection between two collections
	 *
	 * @param l1
	 *            first collection
	 * @param l2
	 *            second collection
	 * @param comparator
	 *            equity comparator used for element comparison
	 * @return HasSet representing intersection between two collections
	 */
	public static <V> Set<V> intersect(final Collection<V> l1, final Collection<V> l2, final EquityComparator<V> comparator) {
		final Set<V> intersection;
		if (isNotEmpty(l1) && isNotEmpty(l2)) {
			intersection = l1.stream().filter(e -> contains(l2, e, comparator)).collect(Collectors.toSet());
		} else {
			intersection = new HashSet<>();
		}
		return intersection;
	}

	/**
	 * Splits a collection into list of collection with max number of elements equals to <tt>maxSize</tt>
	 *
	 * @param collection
	 *            collection to be split
	 * @param maxSize
	 *            max size of the sub collections
	 * @param collectionFactory
	 *            collection factory used to create sub collections
	 * @return a list of collections, each collection having size <tt>maxSize</tt>. The last collection could contain less than <tt>maxSize</tt> elements
	 */
	public static <V, C extends Collection<V>> List<C> split(final Collection<V> collection, final int maxSize, final Supplier<C> collectionFactory) {
		final List<C> list = new ArrayList<>();

		if (isNotEmpty(collection) && maxSize > 0) {
			final Iterator<V> it = collection.iterator();
			while (it.hasNext()) {
				int count = 0;
				final C subCollection = collectionFactory.get();
				list.add(subCollection);
				while (it.hasNext() && count < maxSize) {
					subCollection.add(it.next());
					count++;
				}
			}
		}

		return list;
	}
}
