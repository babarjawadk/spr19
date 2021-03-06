package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimingArrayHeapMinPQTest {


    private static void addTimingTest() {
        for (int i = 10; i < 100000000; i *= 10) {
            ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
            NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
            Stopwatch sw;

            List<Double> randomDoubles = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                randomDoubles.add(generateRandomDouble());
            }

            sw = new Stopwatch();
            for (int j = 0; j < i; j++) {
                minPQ.add(j, randomDoubles.get(j));
            }
            System.out.print("add() for minPQ and naivePQ (" + i + "): " + sw.elapsedTime() +  " and ");

            sw = new Stopwatch();
            for (int j = 0; j < i; j++) {
                naivePQ.add(j, randomDoubles.get(j));
            }
            System.out.println(sw.elapsedTime() +  " seconds.");
        }
    }

    private static void removeSmallestTimingTest() {
        for (int i = 10; i < 100000000; i *= 10) {
            ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
            NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
            Stopwatch sw;

            List<Double> randomDoubles = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                randomDoubles.add(generateRandomDouble());
            }


            for (int j = 0; j < i; j++) {
                minPQ.add(j, randomDoubles.get(j));
            }
            sw = new Stopwatch();
            for (int j = 0; j < i; j++) {
                minPQ.removeSmallest();
            }

            System.out.print("removeSmallest() for minPQ and naivePQ (" + i + "): " + sw.elapsedTime() +  " and ");

            if (i < 1000000) {
                for (int j = 0; j < i; j++) {
                    naivePQ.add(j, randomDoubles.get(j));
                }
                sw = new Stopwatch();
                for (int j = 0; j < i; j++) {
                    naivePQ.removeSmallest();
                }

                System.out.println(sw.elapsedTime() +  " seconds.");
            } else {
                System.out.println("<too big> seconds.");
            }

        }
    }

    private static void changePriorityTimingTest() {
        for (int i = 10; i < 100000000; i *= 10) {
            ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
            NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
            ArrayList<Integer> items = new ArrayList<>();
            Stopwatch sw;

            List<Double> randomDoubles = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                randomDoubles.add(generateRandomDouble());
            }

            for (int j = 0; j < i; j++) {
                minPQ.add(j, randomDoubles.get(j));
                items.add(j);
            }
            sw = new Stopwatch();
            for (int j = 0; j < i; j++) {
                int retrieveRandomInt = getRandomElement(items);
                minPQ.changePriority(retrieveRandomInt, randomDoubles.get(i-j-1));
            }

            System.out.print("changePriority() for minPQ and naivePQ (" + i + "): " + sw.elapsedTime() +  " and ");

            items.clear();
            if (i < 1000000) {
                for (int j = 0; j < i; j++) {
                    naivePQ.add(j, randomDoubles.get(j));
                    items.add(j);
                }
                sw = new Stopwatch();
                for (int j = 0; j < i; j++) {
                    int retrieveRandomInt = getRandomElement(items);
                    naivePQ.changePriority(retrieveRandomInt, randomDoubles.get(i-j-1));
                }

                System.out.println(sw.elapsedTime() +  " seconds.");
            } else {
                System.out.println("<too big> seconds.");
            }

        }
    }

    public static void main(String[] args) {
        addTimingTest();
        System.out.println();
        removeSmallestTimingTest();
        System.out.println();
        changePriorityTimingTest();
    }

    private static int generateRandomInt(int upperRange){
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    private static double generateRandomDouble(){
        Random random = new Random();
        return random.nextDouble();
    }

    private static int getRandomElement(List<Integer> list) {
        return list.get(generateRandomInt(list.size()));
    }

}
