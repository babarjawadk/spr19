/** Interface for a deque.
 * @author Jawad
 */
public interface Deque<T> {
    /** Destructive method that adds an element to the front of the Deque.
     * @param item value of type T to be added */
    public void addFirst(T item);
    /** Destructive method that adds an element to the end of the Deque.
     * @param item value of type T to be added */
    public void addLast(T item);
    /** Returns the size of the Deque. */
    public int size();
    /** Prints the LinkedListDeque. */
    public void printDeque();
    /** Removes and returns the first item of the Deque. */
    public T removeFirst();
    /** Removes and returns the last item of the Deque. */
    public T removeLast();
    /** Returns the ith item of the ArrayDeque.
     * @param index ith item to be returned
     */
    public T get(int index);

    /** Returns wether or not the Deque is empty. */
    public default boolean isEmpty() {
        return size() == 0;
    }

}
