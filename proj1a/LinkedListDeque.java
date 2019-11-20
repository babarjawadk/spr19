public class LinkedListDeque<T> {

    /** Private: class not used by any other class outside LinkedListDeque
     * Static: TNode never uses any details of the LinkedListDeque class
     * but here we use T from enclosing class -> we don't use static
     */
    private class TNode {
        public T item;
        public TNode next;
        public TNode prev;

        TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }

        TNode() {};
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
/*
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;


        TNode p = other.sentinel.next;
        for(int i = 0; i < other.size(); i += 1) {
            addLast((T) p.item); // cast

            p = p.next;
        }



    }
*/


    public void addFirst(T item) {
        TNode newNode = new TNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        TNode newNode = new TNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode p = sentinel.next;
        for(int i = 0; i < size; i++) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println();
    }

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

    private T getRecursive(TNode currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursive(currentNode.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }





}
