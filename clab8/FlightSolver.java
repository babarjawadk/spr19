

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 *
 * @source https://github.com/Wangwj98/CS61B-Spring2019/blob/master/labs/lab8/clab8/FlightSolver.java
 */
public class FlightSolver {

    /**
     * Sort both of them into ascending order.
     */
    private PriorityQueue<Flight> minStartTimePQ;
    private PriorityQueue<Flight> minEndTimePQ;

    public FlightSolver(ArrayList<Flight> flights) {
        minStartTimePQ = new PriorityQueue<>(flights.size(), Comparator.comparingInt(Flight::startTime));
        minEndTimePQ = new PriorityQueue<>(flights.size(), Comparator.comparingInt(Flight::endTime));
        for (Flight flight : flights) {
            minStartTimePQ.add(flight);
            minEndTimePQ.add(flight);
        }
    }

    public int solve() {
        int largest = 0;
        int count = 0;
        while (!minStartTimePQ.isEmpty() && !minEndTimePQ.isEmpty()) {
            Flight f1 = minStartTimePQ.peek();
            Flight f2 = minEndTimePQ.peek();
            if (f1.startTime() < f2.endTime()) {
                count += f1.passengers();
                if (count > largest) {
                    largest = count;
                }
                minStartTimePQ.remove();
            } else {
                count -= f2.passengers();
                minEndTimePQ.remove();
            }
        }
        return largest;
    }

}

