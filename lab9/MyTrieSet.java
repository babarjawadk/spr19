import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private static class Node {
        private boolean isKey;
        private Node[] next = new Node[R];



    }


    private static final int R = 128;

    /** Root of the trie. */
    private Node root;


    /** Clears all items out of Trie */
    public void clear() {
        root = new Node();
    }

    public MyTrieSet() {
        root = new Node();
    }

    private Node get(String key, Node node) {
        if (key.isEmpty()) {
            return node;
        }
        if (node.next[(int) key.charAt(0)] == null) {
            return null;
        }
        return get(key.substring(1), node.next[(int) key.charAt(0)]);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {


        Node returnNode = get(key, root);
        if (returnNode == null) {
            return false;
        }
        return returnNode.isKey;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }



        Node iter = root;
        for (int i = 0; i < key.length(); i++) {
            int pos = key.charAt(i);
            if (iter.next[pos] == null) {
                iter.next[pos] = new Node();
            }
            iter = iter.next[pos];


        }
        iter.isKey = true;

    }

    private void collect(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        for (int i = 0; i < R; i++) {
            if (n.next[i] != null) {
                collect(s + (char) i, x, n.next[i]);
            }

        }
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> outputList = new ArrayList<>();

        Node node = get("sa", root);

        collect(prefix, outputList, node);

        return outputList;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet test = new MyTrieSet();
        test.add("a");
        test.add("ab");
        test.add("adC");


    }
}
