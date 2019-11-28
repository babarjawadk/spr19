package es.datastructur.synthesizer;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testBasics() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);

        assertEquals(4, arb.capacity());
        assertTrue(arb.isEmpty());

        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        assertFalse(arb.isFull());
        arb.enqueue(-3.1);
        assertTrue(arb.isFull());
        arb.dequeue();
        assertEquals(15.1, arb.peek());

    }

    @Test
    public void testIterator() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);

        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }

        Iterator<Integer> seer1 = arb.iterator();
        Iterator<Integer> seer2 = arb.iterator();

        int expected1 = 0;
        while (seer1.hasNext()) {
            assertEquals(expected1, (int) seer1.next());
            expected1++;
        }


        int expected2 = 0;
        for (int i : seer2) {
            assertEquals(expected2, i);
            expected2++;
        }


    }
}
