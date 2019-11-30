package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Monte Carlo simulation. Performs T independent experiments on an N-by-N grid.
 * @author jawad
 */
public class PercolationStats {
    /** Array containing thresholds for all T experiments. */
    private final double[] thresholds;
    /** Total number of experiments. */
    private final double numberOfExperiments;

    /**
     * Constructor conducting all experiments.
     * @param N size of a single grid
     * @param T number of experiments
     * @param pf for autograder
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        thresholds = new double[T];
        numberOfExperiments = T;

        for (int t = 0; t < T; t++) {

            Percolation p = pf.make(N);
            for (int n = 0; (n < N * N) && !p.percolates(); n++) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                }
                p.open(row, col);
            }
            double openedSites = p.numberOfOpenSites();
            double totalSites = N * N;
            thresholds[t] = openedSites / totalSites;
        }
    }

    /**
     * Returns the mean of all thresholds.
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * Returns the standard deviation of all thresholds.
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Returns the low endpoint of 95% confidence interval.
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberOfExperiments);
    }

    /**
     * Returns the high endpoint of 95% confidence interval.
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberOfExperiments);
    }
}
