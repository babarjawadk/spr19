package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private SolverOutcome outcome;
    private double totalWeight;
    private List<Vertex> solution;
    private double time;
    private double maxTime;
    private int states = 1;

    private AStarGraph<Vertex> graph;
    private Vertex first;
    private Vertex last;

    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
    private ArrayHeapMinPQ<Vertex> pq= new ArrayHeapMinPQ<>();


    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();

        graph = input;
        maxTime = timeout;
        first = start;
        last = end;

        distTo.put(start, 0.0);
        pq.add(start, h(start, end));

        while ((!pq.isEmpty() && !pq.getSmallest().equals(end)) || sw.elapsedTime() > timeout) {
            for (WeightedEdge<Vertex> we : input.neighbors(pq.removeSmallest())) {
                relax(we);
            }
            states++;
        }
        time = sw.elapsedTime();
        outcome = calculateOutcome();
        totalWeight = calculateWeight();
        solution = calculatePath();
    }


    private void relax(WeightedEdge<Vertex> weightedEdge) {
        Vertex p = weightedEdge.from();
        Vertex q = weightedEdge.to();
        double weight = weightedEdge.weight();

        if (distTo.get(q) == null || distTo.get(p) + weight < distTo.get(q)) {
            update(p, q, weight);
            states++;
        }
    }

    private void update(Vertex p, Vertex q, double weight) {
        edgeTo.put(q, p);
        distTo.put(q, distTo.get(p) + weight);
        pq.addOrUpdate(q, distTo.get(q) + h(p, q));
    }

    private double h(Vertex p, Vertex q) {
        return graph.estimatedDistanceToGoal(p, q);
    }

    private SolverOutcome calculateOutcome() {
        if (time > maxTime) {
            return SolverOutcome.TIMEOUT;
        } else if (pq.isEmpty()) {
            return SolverOutcome.UNSOLVABLE;
        } else {
            return SolverOutcome.SOLVED;
        }
    }

    private double calculateWeight() {
        if (outcome.equals(SolverOutcome.TIMEOUT) || outcome.equals(SolverOutcome.UNSOLVABLE)) {
            return 0.0;
        } else {
            return distTo.get(last); // return helperWeight(last);
        }
    }

    private List<Vertex> calculatePath() {
        if (outcome.equals(SolverOutcome.TIMEOUT) || outcome.equals(SolverOutcome.UNSOLVABLE)) {
            return new ArrayList<Vertex>();
        } else {
            return helperPath(last, new ArrayList<Vertex>());
        }
    }

    /*private double helperWeight(Vertex v) {
        if (v.equals(first)) {
            return distTo.get(v);
        }
        return distTo.get(v) + helperWeight(edgeTo.get(v));
    }*/

    private List<Vertex> helperPath(Vertex v, List<Vertex> list) {
        if (v.equals(first)) {
            list.add(0, v);
            return list;
        }
        list.add(0, v);
        helperPath(edgeTo.get(v), list);
        return list;
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return totalWeight;
    }

    @Override
    public int numStatesExplored() {
        return states;
    }

    @Override
    public double explorationTime() {
        return time;
    }
}
