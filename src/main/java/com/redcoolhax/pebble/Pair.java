package com.redcoolhax.pebble;

/**
 * Represents a key-value pair. Immutable.
 * @param K Key type.
 * @param V Value type.
 */
public class Pair<K,V> {
    private final K key;
    private final V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}