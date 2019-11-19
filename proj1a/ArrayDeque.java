public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int length;
    private double capacity;
    private int nextFirst;
    private int nextLast;

    private final int RFACTOR = 2;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        length = items.length;
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.length];
        length = other.length;
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        capacity = other.capacity;
        System.arraycopy(other.items, 0, items, 0, length);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == length - 1) {
            return 0;
        }
        return index += 1;
    }

    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];

        if (plusOne(nextFirst) == 0) {
            System.arraycopy(items, plusOne(nextFirst), tmp, 0, size);
        }
        else {
            System.arraycopy(items, plusOne(nextFirst), tmp, 0, length - plusOne(nextFirst));
            System.arraycopy(items, 0, tmp, length - plusOne(nextFirst), size - (length - plusOne(nextFirst)));
        }


        nextFirst = capacity - 1;
        nextLast = size;
        items = tmp;
        length = items.length;
    }


    public void addFirst(T item) {
        if (size == length) {
            resize(size * RFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
        capacityUpdate(size, length);
    }

    public void addLast(T item) {
        if (size == length) {
            resize(size * RFACTOR);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
        capacityUpdate(size, length);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = plusOne(nextFirst); items[i] != null; i = plusOne(i)) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    private void capacityUpdate(double s, double l) {
        capacity = s/l;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }



        if (length >= 16 && capacity <= 0.25) {
            resize(length / RFACTOR);
        }

        nextFirst = plusOne(nextFirst);
        size -= 1;
        T item = items[nextFirst];
        capacityUpdate(size, length);
        items[nextFirst] = null;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }



        if (length >= 16 && capacity <= 0.25) {
            resize(length / RFACTOR);
        }

        nextLast = minusOne(nextLast);
        size -= 1;
        T item = items[nextLast];
        capacityUpdate(size, length);
        items[nextLast] = null;
        return item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + nextFirst) % (length - 1)];
    }
}
