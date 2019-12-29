import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        assertTrue(isSorted(QuickSort.quickSort(createQueue1())));
        assertTrue(isSorted(QuickSort.quickSort(createQueue2())));
    }

    @Test
    public void testMergeSort() {
        assertTrue(isSorted(MergeSort.mergeSort(createQueue1())));
        assertTrue(isSorted(MergeSort.mergeSort(createQueue2())));
    }

    private static Queue<String> createQueue1() {
        Queue<String> queue = new Queue<String>();
        queue.enqueue("zulu");
        queue.enqueue("juliette");
        queue.enqueue("whisky");
        queue.enqueue("delta");
        queue.enqueue("tango");
        queue.enqueue("bravo");
        queue.enqueue("india");
        queue.enqueue("yankee");
        queue.enqueue("november");
        queue.enqueue("mike");
        queue.enqueue("alpha");
        queue.enqueue("kilo");
        return queue;
    }

    private static Queue<Integer> createQueue2() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(10);
        queue.enqueue(9);
        queue.enqueue(8);
        queue.enqueue(7);
        queue.enqueue(6);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(0);
        return queue;
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
