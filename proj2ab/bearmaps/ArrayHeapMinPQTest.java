package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class ArrayHeapMinPQTest {

    @Test
    public void constructorAndSizeTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();

        assertEquals(heap.size(), 0);
    }

    @Test
    public void addAndContainsTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();

        assertFalse(heap.contains("a"));
        assertFalse(heap.contains(null));

        heap.add("a", 1.0);

        assertTrue(heap.contains("a"));
        assertFalse(heap.contains("b"));

        assertEquals(heap.size(), 1);

    }

    @Test
    public void getSmallestTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();

        heap.add("e", 5.0);
        assertEquals(heap.getSmallest(), "e");
        heap.add("c", 3.0);
        assertEquals(heap.getSmallest(), "c");
        heap.add("a", 1.0);
        assertEquals(heap.getSmallest(), "a");
        heap.add("f", 6.0);
        assertEquals(heap.getSmallest(), "a");
        heap.add("d", 4.0);
        assertEquals(heap.getSmallest(), "a");
        heap.add("b", 2.0);
        assertEquals(heap.getSmallest(), "a");
        heap.add("g", 0.0);
        assertEquals(heap.getSmallest(), "g");
        heap.add("h", 12);
        assertEquals(heap.getSmallest(), "g");
        heap.add("i", -1);
        assertEquals(heap.getSmallest(), "i");
        heap.add("j", 7);
        assertEquals(heap.getSmallest(), "i");
        assertEquals(heap.getSmallest(), "i");

        assertEquals(heap.size(), 10);
    }

    @Test
    public void removeSmallestTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();

        assertEquals(heap.size(), 0);

        heap.add("e", 5);
        heap.add("c", 3);
        heap.add("a", 1);
        heap.add("f", 6);
        heap.add("d", 4);
        heap.add("b", 2);

        heap.add("j", 44);
        heap.add("k", 55);
        heap.add("i", 33);
        heap.add("g", 11);
        heap.add("h", 22);

        assertEquals(heap.size(), 11);

        assertEquals(heap.removeSmallest(), "a");
        assertEquals(heap.removeSmallest(), "b");
        assertEquals(heap.removeSmallest(), "c");
        assertEquals(heap.removeSmallest(), "d");
        assertEquals(heap.removeSmallest(), "e");
        assertEquals(heap.removeSmallest(), "f");
        assertEquals(heap.removeSmallest(), "g");
        assertEquals(heap.getSmallest(), "h");
        assertEquals(heap.removeSmallest(), "h");
        assertEquals(heap.removeSmallest(), "i");
        assertEquals(heap.removeSmallest(), "j");
        assertEquals(heap.removeSmallest(), "k");

        assertEquals(heap.size(), 0);
    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();

        heap.add("e", 5);
        heap.add("c", 3);
        heap.add("a", 1);
        heap.add("f", 6);
        heap.add("d", 4);
        heap.add("b", 2);

        assertEquals(heap.getSmallest(), "a");

        heap.changePriority("f", 0);
        assertEquals(heap.getSmallest(), "f");

        heap.changePriority("b", 3);
        heap.changePriority("b", 3);
        heap.changePriority("a", -10);
        heap.changePriority("c", 1);
        heap.changePriority("e", -3);
        heap.changePriority("d", 2);

        assertEquals(heap.removeSmallest(), "a");
        assertEquals(heap.removeSmallest(), "e");
        assertEquals(heap.removeSmallest(), "f");
        assertEquals(heap.removeSmallest(), "c");
        assertEquals(heap.removeSmallest(), "d");
        assertEquals(heap.removeSmallest(), "b");
        assertEquals(heap.size(), 0);
    }

    @Test
    public void randomizedTest() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        ArrayList<Integer> items = new ArrayList<>();


        for (int i = 0; i < 1000; i++) {
            int r = generateRandomInt(100);

            assertEquals(minPQ.size(), naivePQ.size());

            if (r < 30 && minPQ.size() != 0) { // removeSmallest
                assertEquals(minPQ.getSmallest(), naivePQ.getSmallest());

                items.remove(minPQ.getSmallest());

                assertEquals(minPQ.removeSmallest(), naivePQ.removeSmallest());
            } else if (r < 60 && minPQ.size() != 0) { // changePriority
                assertEquals(minPQ.getSmallest(), naivePQ.getSmallest());

                int retrieveRandomInt = getRandomElement(items);
                Double randomPriority = generateRandomDouble();

                minPQ.changePriority(retrieveRandomInt, randomPriority);
                naivePQ.changePriority(retrieveRandomInt, randomPriority);

                assertEquals(minPQ.contains(retrieveRandomInt), naivePQ.contains(retrieveRandomInt));
                assertEquals(minPQ.getSmallest(), naivePQ.getSmallest());
            } else { // add
                int randomInt = generateRandomInt(1000);
                while (minPQ.contains(randomInt)) {
                    randomInt = generateRandomInt(1000);
                }
                Double randomPriority = generateRandomDouble();

                minPQ.add(randomInt, randomPriority);
                naivePQ.add(randomInt, randomPriority);
                items.add(randomInt);

                assertEquals(minPQ.contains(randomInt), naivePQ.contains(randomInt));
                assertEquals(minPQ.getSmallest(), naivePQ.getSmallest());
            }
        }
    }

    private static int generateRandomInt(int upperRange){
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    private static double generateRandomDouble(){
        Random random = new Random();
        return random.nextDouble();
    }

    public int getRandomElement(List<Integer> list) {
        return list.get(generateRandomInt(list.size()));
    }

}
