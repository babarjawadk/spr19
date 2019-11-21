/** Doubly Linked List based Deque (usually pronounced like “deck”)
 * is an irregular acronym of double-ended queue.
 * Double-ended queues are sequence containers with dynamic sizes
 * that can be expanded or contracted on both ends
 * (either its front or its back).
 * @author Jawad
 */
public class LinkedListDeque<T> implements Deque<T> {

    /** Private: class not used by any other class outside LinkedListDeque.
     * Static: TNode never uses any details of the LinkedListDeque class
     * but here we use T from enclosing class -> we don't use static.
     */
    private class TNode {
        /** @item: saved data in the node of any type. */
        private T item;
        /** @next: pointer to the next node in the doubly linked list. */
        private TNode next;
        /** @prev: pointer to the previous node in the diubly linked list. */
        private TNode prev;

        /** Constructor for a node of any type.
         * @param i value of the item to be saved in the node
         * @param n pointer to the next node
         * @param p pointer to the previous node
         * */
        TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }

        /** Empty constructor for a node, it doesn't initialise anything. */
        TNode() { };
    }

    /** Sentinel node for the doubly linked list deque. */
    private TNode sentinel;
    /** Size is cached for fast access. */
    private int size;

    /** Constructor for a a LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new TNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Constructs a LinkedListDeque from an existing one.
     * @param other original LinkedListDeque to be copied
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        TNode p = other.sentinel.next;
        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) p.item);
            p = p.next;
        }
    }

    /** Destructive method that adds an element to the front of the list.
     * @param item value of type T to be added
     */
    @Override
    public void addFirst(T item) {
        TNode newNode = new TNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /** Destructive method that adds an element to the back of the list.
     * @param item value of type T to be added
     */
    @Override
    public void addLast(T item) {
        TNode newNode = new TNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }



    /** Returns the size of the LinkedListDeque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the LinkedListDeque. */
    @Override
    public void printDeque() {
        TNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the first item/node of the LinkedListDeque. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return res;
    }

    /** Removes and returns the last item/node of the LinkedListDeque. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return res;
    }

    /** Returns the ith item of the LinkedListDeque.
     * @param index ith item to be returned
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        TNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Helper method for the getRecursive method that returns the ith item.
     * @param currentNode recursively iterates through the list using this param
     * @param index this decreases until it arrives to zero
     */
    private T getRecursive(TNode currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursive(currentNode.next, index - 1);
    }

    /** Returns the ith item of the LinkedListDeque recursively.
     * @param index ith item to be returned
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }
}
