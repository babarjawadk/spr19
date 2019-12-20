package bearmaps.proj2ab;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private static final int resizeFactor = 2;
    private static final int startHeapLength = 8;
    private static final double resizeRatio = 0.25;

    private int size;
    private int heapLength;

    private PriorityNode[] heap;
    private HashMap<T, PriorityNode> hashMap;


    public ArrayHeapMinPQ() {
        size = 0;
        heapLength = startHeapLength;
        heap = new ArrayHeapMinPQ.PriorityNode[heapLength];
        hashMap = new HashMap<>();
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

    private void swap(int i, int j) {
        PriorityNode temp = heap[i];
        heap[i] = heap[j];
        heap[i].setIndex(i);
        heap[j] = temp;
        heap[j].setIndex(j);
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

    public boolean isEmpty() {
        return size() == 0;
    }

    public void addOrUpdate(T item, double priority) {
        if (hashMap.containsKey(item)) {
            changePriority(item, priority);
        } else {
            add(item, priority);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item: " + item + " already in PQ!");
        }
        if (size == heapLength - 1) {
            resize(heapLength * resizeFactor);
        }
        size += 1;
        PriorityNode node = new PriorityNode(item, priority, size);
        hashMap.put(item, node);

        heap[size] = node;
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
            throw new NoSuchElementException("PQ is empty!");
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
        PriorityNode node = hashMap.get(item);
        if (node == null) {
            throw new NoSuchElementException("PQ does not contain: " + item + "!");
        }
        node.setPriority(priority);
        int i = node.getIndex();

        swim(i);
        sink(i);

    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;
        private int index;

        PriorityNode(T e, double p, int i) {
            this.item = e;
            this.priority = p;
            this.index = i;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        int getIndex() {
            return index;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        void setIndex(int i) {
            this.index = i;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
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
