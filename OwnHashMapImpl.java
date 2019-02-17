package com.ez.collections;

/**
 * Goal: To create our own HashMap implementation with Array and linked list
 * 
 * @author ezhilanmahalingam
 *
 */
public class MyHashMap {

	public static void main(String[] args) {

		MyMap<Integer, Integer> map = new MyMapImpl<>();

		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);

		System.out.println(map.get(1));
		System.out.println(map.get(22));
		System.out.println(map.size());
	}

}

/*
 * Simple diagram of how the Entries are stored in our hashMap, index is decided by key's hash code
 * If there is a collision, we will have linkedList in the entry like index 2  
 * 
---|---|----|---------------------
 0   1   2    array
---|---|----|----------------------
 |   |   Entry<3.3>->Entry<31,4>->Entry<38,91>->null
 |   V
 |   Entry<2,2>
 V
 Entry<1,1>
 */

/**
 * Capacity is 16 for HashMap. and load factor is 0.75
 * Threshold = capacity*load_factor = 16*0.75 = 12, 
 * HashMap will double its size when we are inserting the 12th element into the hashMap 
 * We didn't handled this functionality here in our implementation
 * 
 * @author ezhilanmahalingam
 *
 * @param <K>
 * @param <V>
 */
class MyMapImpl<K, V> implements MyMap<K, V> {

	private static final int CAPACITY = 16;
	int size = 0;
	Entry<K, V> bucket[] = null;

	MyMapImpl() {
		// call the constructor with capacity
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	MyMapImpl(int _capacity) {
		bucket = new Entry[_capacity];
	}

	@Override
	public void put(K k, V v) {

		Entry<K, V> newEntry = new Entry<K, V>(k, v, null);

		// First get the index for the bucket
		int index = getIndexFor(k);

		// First time insert into the bucket
		Entry<K, V> entry = bucket[index];

		if (entry == null) {
			bucket[index] = newEntry;
			size++;
			return;
		}
		// If the bucket is already present - Collision
		else {
			while (entry.next != null) {
				// 1. check is the same key is already there. if exists update its value
				if (entry.key.equals(k)) {
					entry.value = v;
					return;
				}
				entry = entry.next;
			}
			// Check for last entry
			if (entry.key.equals(k)) {
				entry.value = v;
				return;
			} else {
				// 2. If key doesn't exists in collision - add new Entry at the end of linked list
				entry.next = newEntry;
				size++;
			}
		} // else
	}

	@Override
	public V get(K k) {
		// First get the index for the bucket
		int index = getIndexFor(k);

		// If element is not found in the buckets
		if (bucket[index] == null) {
			return null;
		}

		Entry<K, V> entry = bucket[index];

		while (entry != null) {

			if (entry.key.equals(k)) {
				return entry.value;
			}
			entry = entry.next;
		}

		return null;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * HashCode modulo with the length will always return an integer within the length range
	 * 
	 * @param k
	 * @return
	 */
	private int getIndexFor(K k) {
		int index = (k == null ? 0 : k.hashCode() % bucket.length);
		return index;
	}

}

/**
 * Create a class for Map Entry with Key, Value and next pointer, Should
 * override equals and hashcode function
 * 
 * @author ezhilanmahalingam
 *
 * @param <K>
 * @param <V>
 */
class Entry<K, V> {

	K key;
	V value;
	Entry<K, V> next;

	public Entry(K k, V v, Entry<K, V> new_next) {
		key = k;
		value = v;
		next = new_next;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Entry) {
			@SuppressWarnings("unchecked")
			Entry<K, V> e = (Entry<K, V>) obj;
			if (e.key.equals(key) && e.value.equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 13;
		hash = hash * 31 + (key == null ? 0 : key.hashCode());
		hash = hash * 31 + (value == null ? 0 : value.hashCode());
		System.out.println(hash);
		return hash;
	}

}

/**
 * Collection interface for core functions
 * 
 * @author ezhilanmahalingam
 *
 * @param <K>
 * @param <V>
 */
interface MyMap<K, V> {
	public void put(K k, V v);
	public V get(K k);
	public int size();
}
