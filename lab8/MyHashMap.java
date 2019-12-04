import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private class Node {
        /** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V val;
        /** Stores the next Entry in the linked list. */
        Node next;

        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        Node(K k, V v, Node n) {
            key = k;
            val = v;
            next = n;
        }
    }

    private static final double defaultLoadFactor = 0.75;
    private static final int defaultSize = 16;


    private Node[] array;
    private int size;
    private int items;
    private double loadFactor;
    private final int resizeFactor = 2;

    public MyHashMap(int initialSize, double loadFactor) {
        this.size = initialSize;
        this.loadFactor = loadFactor;
        this.items = 0;
        this.array = (Node[]) new Object[this.size];
    }

    public MyHashMap() {
        this(defaultSize, defaultLoadFactor);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, defaultLoadFactor);
    }


    @Override
    public void clear() {
        this.size = 16;
        this.items = 0;
        this.array = (Node[]) new Object[this.size];
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    private int bucketNum(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % size;
    }

    private V find(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node.val;
        }
        return find(node.next, key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return find(array[bucketNum(key)], key);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return items;
    }

    private Node insert(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, null);
        }
        if (node.key == key) {
            node.val = value;
            return node;
        }
        node.next = insert(node.next, key, value);
        return node;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int n = bucketNum(key);
        array[n] = insert(array[n], key, value);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
