package es.datastructur.synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T>  extends  AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    //private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;
        private int position;


        public ArrayRingBufferIterator() {
            wizPos = first;
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[wizPos];
            wizPos = nextItem(wizPos);
            position++;
            return returnItem;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int n) {
        capacity = n;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    // return size of the buffer
    /*@Override
    public int capacity() {
        return rb.length;
    }*/

    // return number of items currently in the buffer
    /*@Override
    public int fillCount() {
        return fillCount;
    }*/

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = nextItem(last);
        fillCount += 1;
    }

    private int nextItem(int i) {
        if (i == capacity() - 1) {
            return 0;
        }
        return i + 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        rb[first] = null;
        first = nextItem(first);
        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; }
        if (this.getClass() != o.getClass()) { return false; }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (this.fillCount() != other.fillCount || this.capacity() != other.capacity()) { return false; }

        Iterator<T> seer1 = this.iterator();
        Iterator<T> seer2 = other.iterator();
        while (seer1.hasNext()) {
            if (seer1.next() != seer2.next()) {
                return false;
            }
        }

        return true;
    }
}
