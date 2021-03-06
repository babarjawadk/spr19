import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private static class TrieNode {
        private boolean isKey;
        private Map<Character, TrieNode> next = new HashMap<>();
    }

    /** Root of the trie. */
    private TrieNode root;

    /** Constructs an empty Trie. */
    public MyTrieSet() {
        root = new TrieNode();
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = new TrieNode();
    }

    private TrieNode get(String key, TrieNode node) {
        if (key.isEmpty()) {
            return node;
        }
        if (node == null) {
            return null;
        }
        return get(key.substring(1), node.next.get(key.charAt(0)));
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        TrieNode returnNode = get(key, root);
        if (returnNode == null) {
            return false;
        }
        return returnNode.isKey;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() == 0) {
            return;
        }
        TrieNode iter = root;
        for (int i = 0; i < key.length(); i++) {
            if (iter.next.get(key.charAt(i)) == null) {
                iter.next.put(key.charAt(i), new TrieNode());
            }
            iter = iter.next.get(key.charAt(i));
        }
        iter.isKey = true;
    }

    private void collect(String s, List<String> x, TrieNode n) {
        if (n.isKey) {
            x.add(s);
        }

        for (Character c : n.next.keySet()) {
            collect(s + c, x, n.next.get(c));
        }
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> outputList = new ArrayList<>();
        TrieNode node = get(prefix, root);
        collect(prefix, outputList, node);
        return outputList;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
