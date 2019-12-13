package bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private static final int resizeFactor = 2;
    private static final int startHeapLength = 8;
    private static final double resizeRatio = 0.25;

    private int size;
    private int heapLength;

    private PriorityNode[] heap;
    private HashMap<T, Double> hashMap;


    public ArrayHeapMinPQ() {
        size = 0;
        heapLength = startHeapLength;
        heap = new ArrayHeapMinPQ.PriorityNode[heapLength];
        hashMap = new HashMap<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already present in the Priority Queue!");
        }
        if (size == heapLength - 1) {
            resize(heapLength * resizeFactor);
        }

        hashMap.put(item, priority);
        size += 1;
        heap[size] = new PriorityNode(item, priority);
        swim(size);
    }

    @Override
    public boolean contains(T item) {
        return hashMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return heap[1].getItem();
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        if (size > 8 && size <= heapLength * resizeRatio) {
            resize(heapLength / resizeFactor);
        }
        T smallestItem = heap[1].getItem();
        hashMap.remove(smallestItem);
        heap[1] = heap[size];
        heap[size] = null;
        size -= 1;
        sink(1);
        return smallestItem;
    }

    @Override
    public void changePriority(T item, double priority) {
        Double oldPriority = hashMap.replace(item, priority);
        if (oldPriority == null) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        int i = find(1, new PriorityNode(item, oldPriority));
        heap[i].setPriority(priority);
        sink(i);
        swim(i);
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if (j < size && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }



    private boolean exists(int i) { return (i > 0 && i <= size); }

    private int parent(int k) { return k / 2; }

    private int leftChild(int k) { return k * 2; }

    private int rightChild(int k) { return k * 2 + 1; }

    private void swap(int i, int j) {
        PriorityNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int find(int i, PriorityNode p) {
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        if (heap[i].equals(p)) {
            return i;
        }
        int j = 0;
        if (exists(leftChild) && heap[leftChild].compareTo(p) <= 0) {
            j = find(leftChild, p);
        }
        if (j == 0 && exists(rightChild) && heap[rightChild].compareTo(p) <= 0) {
            j = find(rightChild, p);
        }
        return j;
    }

    private boolean greater(int i, int j) {
        return heap[i].compareTo(heap[j]) > 0;
    }

    private void resize(int length) {
        heapLength = length;
        PriorityNode[] newHeap = new ArrayHeapMinPQ.PriorityNode[heapLength];
        System.arraycopy(heap, 1, newHeap, 1, size);
        heap = newHeap;
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
