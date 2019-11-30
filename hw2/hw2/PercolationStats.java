package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    // perform T independent experiments on an N-by-N grid

    private final double[] thresholds;
    private final double numberOfExperiments;

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

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberOfExperiments);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberOfExperiments);
    }


    /*public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats testStats = new PercolationStats(50, 100, pf);
        double a = testStats.mean();
    }*/
}
