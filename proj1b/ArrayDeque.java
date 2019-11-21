/** Array based Deque (usually pronounced like “deck”)
 * is an irregular acronym of double-ended queue.
 * Double-ended queues are sequence containers with dynamic sizes
 * that can be expanded or contracted on both ends
 * (either its front or its back).
 * @author Jawad
 */
public class ArrayDeque<T> implements Deque<T> {
    /** Array containing the elements of the Deque. */
    private T[] items;
    /** Number of items currently in the ArrayDeque. */
    private int size;
    /** Length of the Array. */
    private int length;
    /** Size/Length. */
    private double capacity;
    /** Index of the next element to be inserted in the front. */
    private int nextFirst;
    /** Index of the next element to be inserted in the back. */
    private int nextLast;

    /** Used when resizing the Array. */
    private final int rFactor = 2;

    /** Creates an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        length = items.length;
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Constructs a ArrayDeque from an existing one.
     * @param other original LinkedListDeque to be copied
     */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.length];
        length = other.length;
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        capacity = other.capacity;
        System.arraycopy(other.items, 0, items, 0, length);
    }

    /** Returns a boolean that checks if the LinkedListDeque is empty.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** Helper function that returns the previous index.
     * @param index index of ArrayDeque
     */
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    /** Helper function that returns the next index.
     * @param index index of ArrayDeque
     */
    private int plusOne(int index) {
        if (index == length - 1) {
            return 0;
        }
        return index + 1;
    }

    /** Resizes the ArrayDeque.
     * @param newLength new capacity of the ArrayDeque
     */
    private void resize(int newLength) {
        T[] tmp = (T[]) new Object[newLength];
        if (plusOne(nextFirst) == 0 || plusOne(nextFirst) + size < length) {
            System.arraycopy(items, plusOne(nextFirst), tmp, 0, size);
        } else {
            System.arraycopy(items, plusOne(nextFirst), tmp, 0, length - plusOne(nextFirst));
            System.arraycopy(items, 0, tmp, length - plusOne(nextFirst), nextLast);
        }
        nextFirst = newLength - 1;
        nextLast = size;
        items = tmp;
        length = items.length;
    }

    /** Destructive method that adds an element to the front of the list.
     * @param item value of type T to be added
     */
    public void addFirst(T item) {
        if (size == length) {
            resize(size * rFactor);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
        capacityUpdate(size, length);
    }

    /** Destructive method that adds an element to the back of the list.
     * @param item value of type T to be added
     */
    public void addLast(T item) {
        if (size == length) {
            resize(size * rFactor);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
        capacityUpdate(size, length);
    }

    /** Returns the size of the ArrayDeque. */
    public int size() {
        return size;
    }

    /** Prints the LinkedListDeque. */
    public void printDeque() {
        for (int i = plusOne(nextFirst); items[i] != null; i = plusOne(i)) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    /** Updates the capacity of the ArrayDeque.
     * @param s current size of the ArrayDeque
     * @param l current length of the ArrayDeque
     */
    private void capacityUpdate(double s, double l) {
        capacity = s / l;
    }

    /** Removes and returns the first item of the ArrayDeque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if (length >= 16 && capacity < 0.25) {
            resize(length / rFactor);
        }
        nextFirst = plusOne(nextFirst);
        size -= 1;
        T item = items[nextFirst];
        capacityUpdate(size, length);
        items[nextFirst] = null;
        return item;
    }

    /** Removes and returns the last item of the ArrayDeque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (length >= 16 && capacity < 0.25) {
            resize(length / rFactor);
        }
        nextLast = minusOne(nextLast);
        size -= 1;
        T item = items[nextLast];
        capacityUpdate(size, length);
        items[nextLast] = null;
        return item;
    }

    /** Returns the ith item of the ArrayDeque.
     * @param index ith item to be returned
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + nextFirst + 1) % length];
    }
}
