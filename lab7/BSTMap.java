import java.util.*;

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

        /** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V val;
        /** Stores the next Entry in the linked list. */
        BSTMap.Node left;
        /** Stores the next Entry in the linked list. */
        BSTMap.Node right;
    }

    private Node head;

    int size = 0;

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (head == null) {
            return false;
        }
        return head.get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (head == null) {
            return null;
        }
        Node lookup = head.get(key);
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
        head = insert(head, key, value);
        size += 1;
    }

    private BSTMap.Node insert(BSTMap.Node n, K k, V v) {
        if (n == null) {
            n = new BSTMap.Node(k, v, null, null);
        }
        if (n.key.equals(k)) {
            n.val = v;
        }
        if (n.key.compareTo(k) > 0) {
            n.left = insert(n.left, k, v);
        }
        if (n.key.compareTo(k) < 0) {
            n.right = insert(n.right, k, v);
        }
        return n;
    }



    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(set, head);
        return set;
    }

    private void keySet(Set set, BSTMap.Node node) {
        if (node != null) {
            keySet(set, node.left);
            set.add(node.key);
            keySet(set, node.right);
        }
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
        return keySet().iterator();
    }

    public void printInOrder() {
        printInOrder(head);
    }

    private void printInOrder(BSTMap.Node n) {
        if (n == null) {
            return;
        }
        printInOrder(n.left);
        System.out.println("<" + n.key + ">, <" + n.val + ">");
        printInOrder(n.right);
    }




    public static void main(String[] args) {
        BSTMap<String, String> m = new BSTMap<>();
        m.put("Paris", "France");
        m.put("Amsterdam", "Netherlands");
        m.put("Washington", "United States of America");
        m.put("London", "United Kingdom");
        m.put("Brussels", "Belgium");
        m.put("Stockholm", "Sweden");
        m.put("Dublin", "Ireland");
        m.put("Islamabad", "Pakistan");
        m.put("Moscow", "Russia");
        m.put("Warsaw", "Poland");
        m.put("Istanbul", "Turkey");
        m.put("Madrid", "Spain");
        m.put("Berlin", "Germany");
        m.put("Sydney", "Australia");
        m.put("Auckland", "New Zealand");

        m.printInOrder();

        for (String s : m) {
            System.out.println(s);
        }

    }

}
