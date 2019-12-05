import java.lang.reflect.Array;
import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private class Node {
        K key;
        V val;
        Node next;

        Node(K k, V v, Node n) {
            key = k;
            val = v;
            next = n;
        }
    }

    private static final double defaultLoadFactor = 0.75;
    private static final int defaultSize = 16;
    private static final int resizeFactor = 2;


    private Node[] buckets;
    private int size;
    private int items;
    private double loadFactor;


    public MyHashMap() {
        this(defaultSize, defaultLoadFactor);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, defaultLoadFactor);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.size = initialSize;
        this.loadFactor = loadFactor;
        this.items = 0;
        this.buckets = (Node[]) Array.newInstance(Node.class, this.size);
    }


    @Override
    public void clear() {
        this.size = defaultSize;
        this.items = 0;
        this.buckets = (Node[]) Array.newInstance(Node.class, this.size);
    }

    private int bucketNum(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % size;
    }

    private V find(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return node.val;
        }
        return find(node.next, key);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return find(buckets[bucketNum(key)], key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return find(buckets[bucketNum(key)], key);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return items;
    }

    private Node insert(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value, null);
            items++;
        }
        if (node.key.equals(key)) {
            node.val = value;
        } else {
            node.next = insert(node.next, key, value);
        }
        return node;
    }

    private void add(Node[] nodes, K key, V val) {
        int n = bucketNum(key);
        nodes[n] = insert(nodes[n], key, val);
    }

    private void resize(int capacity) {
        Node[] newBuckets = (Node[]) Array.newInstance(Node.class, capacity);
        this.items = 0;
        this.size = capacity;
        for (Node n : buckets) {
            Node iter = n;
            while (iter != null) {
                add(newBuckets, iter.key, iter.val);
                iter = iter.next;
            }
        }
        this.buckets = newBuckets;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if ((double) items / (double) size >= loadFactor) {
            resize(size * resizeFactor);
        }
        add(this.buckets, key, value);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Node n : buckets) {
            Node iter = n;
            while (iter != null) {
                set.add(iter.key);
                iter = iter.next;
            }
        }
        return set;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
