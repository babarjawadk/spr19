import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {

        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        Node(K k, V v, BSTMap.Node l, BSTMap.Node r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        BSTMap.Node get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (k.compareTo(key) > 0 && right != null) {
                return right.get(k);
            }
            if (k.compareTo(key) < 0 && left != null) {
                return left.get(k);
            }
            return null;
        }

        static BSTMap.Node insert(BSTMap n, K k, V v) {
            if (n == null) {
                return new BSTMap.Node(k, v, null, null);
            }
            if (k.compareTo(key) > 0 && right != null) {
                return right.get(k);
            }
            if (k.compareTo(key) < 0 && left != null) {
                return left.get(k);
            }
            return null;
        }

        /** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V val;
        /** Stores the next Entry in the linked list. */
        BSTMap.Node left;
        /** Stores the next Entry in the linked list. */
        BSTMap.Node right;
    }

    private Node tree;

    int size = 0;

    BSTMap(K k, V v) {
        tree = new BSTMap.Node(k, v, null, null);
        size = 1;
    }

    BSTMap() { }

    @Override
    public void clear() {
        tree = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (tree == null) {
            return false;
        }
        return tree.get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (tree == null) {
            return null;
        }
        Node lookup = tree.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {

        insert(tree, key, value);
        size += 1;
    }

    private static BSTMap.Node insert(BSTMap t, K k, V v) {
        if (t == null) {
            return new BSTMap(k, v);
        }
        if (t.tree.key.equals(k)) {
            t.tree.val = v;
        }
        if (t.tree.key.compareTo(k) > 0) {
            t.tree.right = insert(t.tree.right, k, v);
        }
        if (t.tree.key.compareTo(k) < 0) {
            t.tree.left = insert(t.tree.left, k, v);
        }
        return t;
    }



    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

}
