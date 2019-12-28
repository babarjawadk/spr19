package bearmaps.lab9;

import bearmaps.hw4.streetmap.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet {

    private static class TrieNode {
        private boolean isKey;
        private List<Node> content = new ArrayList<>();
        private Map<Character, TrieNode> next = new HashMap<>();
    }

    /** Root of the trie. */
    private TrieNode root;

    /** Constructs an empty Trie. */
    public MyTrieSet() {
        root = new TrieNode();
    }

    /** Clears all items out of Trie */
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

    public List<Node> get(String key) {
        return get(key, root).content;
    }


    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        TrieNode returnNode = get(key, root);
        if (returnNode == null) {
            return false;
        }
        return returnNode.isKey;
    }

    /** Inserts string KEY into Trie */
    public void add(String key, Node node) {
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
        iter.content.add(node);
        iter.isKey = true;
    }

    private void collect(String s, List<Node> x, TrieNode n) {
        if (n.isKey) {
            for (Node i : n.content) {
                x.add(i);
            }
        }

        for (Character c : n.next.keySet()) {
            collect(s + c, x, n.next.get(c));
        }
    }

    /** Returns a list of all words that start with PREFIX */
    public List<Node> keysWithPrefix(String prefix) {
        List<Node> outputList = new ArrayList<>();
        TrieNode node = get(prefix, root);
        collect(prefix, outputList, node);
        return outputList;
    }
}
