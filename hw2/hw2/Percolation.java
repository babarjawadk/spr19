package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import static javax.management.ImmutableDescriptor.union;


public class Percolation {
    private final int size;
    private int opened;
    private final int top;
    private final int bottom;
    private int[][] grid;
    private WeightedQuickUnionUF qu;

    private int xyTo1D(int r, int c) {
        return r * size + c;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("input to constructor must be positive integer");
        }
        size = N;
        top = size * size;
        bottom = size * size + 1;
        opened = 0;
        grid = new int[N][N];
        qu = new WeightedQuickUnionUF(xyTo1D(size, size) + 2);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            throw new IllegalArgumentException("index out of bound");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = 1;
        opened++;

        int rowCol = xyTo1D(row, col);

        int prevCol = Math.max(0, col - 1);
        int nextCol = Math.min(size - 1, col + 1);
        int prevRow = Math.max(0, row - 1);
        int nextRow = Math.min(size - 1, row + 1);

        if (isOpen(row, prevCol)) { qu.union(xyTo1D(row, prevCol), rowCol); }
        if (isOpen(row, nextCol)) { qu.union(xyTo1D(row, nextCol), rowCol); }
        if (isOpen(prevRow, col)) { qu.union(xyTo1D(prevRow, col), rowCol); }
        if (isOpen(nextRow, col)) { qu.union(xyTo1D(nextRow, col), rowCol); }

        if (row == 0) {
            qu.union(top, rowCol);
        } else if ((row == (size - 1)) && !percolates()) {
            qu.union(bottom, rowCol);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            throw new IllegalArgumentException("index out of bound");
        }
        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        //return opened == xyTo1D(size, size);
        return qu.connected(xyTo1D(row, col), top);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        return qu.connected(top, bottom);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

        Percolation testPerc = new Percolation(4);
        boolean testBool = false;

        testBool = testPerc.percolates();

        testPerc.open(0, 1);
        testBool = testPerc.percolates();
        testPerc.open(1, 1);
        testBool = testPerc.percolates();
        testPerc.open(2, 1);
        testBool = testPerc.percolates();
        testPerc.open(3, 1);
        testBool = testPerc.percolates();
    }
}
