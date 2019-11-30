package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation class with constructor and couple of methods.
 * @author jawad
 */
public class Percolation {
    /** Size of one side of the grid. */
    private final int size;
    /** Number of opened sites on the grid. */
    private int opened;
    /** Position of the virtual top site in the weighted quick find. */
    private final int top;
    /** Position of the virtual bottom site in the weighted quick find. */
    private final int bottom;
    /** Grid to record opened sites. */
    private int[][] grid;
    /** Weighted quick union find to record percolation status of the grid. */
    private WeightedQuickUnionUF quPercol;
    /** Weighted quick union find to record full status for each site. */
    private WeightedQuickUnionUF quIsFull;

    /**
     * Transforms a (row, col) and returns its corresponding 1 dimensional position in
     * a weighted quick union find.
     * @param r row
     * @param c column
     */
    private int xyTo1D(int r, int c) {
        return r * size + c;
    }

    /**
     * Opens a neighbour if not already open.
     * @param row row index of neighbour
     * @param col column index of neighbour
     * @param rowCol one dimensional position in quick union find of the site
     */
    private void unionNeighbour(int row, int col, int rowCol) {
        if (isOpen(row, col)) {
            quPercol.union(xyTo1D(row, col), rowCol);
            quIsFull.union(xyTo1D(row, col), rowCol);
        }
    }

    /**
     * Creates N-by-N grid, with all sites initially blocked (constructor).
     * #param N grid size
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("input to constructor must be positive integer");
        }
        size = N;
        top = size * size;
        bottom = size * size + 1;
        opened = 0;
        grid = new int[N][N];
        quPercol = new WeightedQuickUnionUF(size * size + 2);
        quIsFull = new WeightedQuickUnionUF(size * size + 1);
    }

    /**
     * Open the site (row, col) if it is not open already.
     * @param row row indew on the grid
     * @param col column index on the grid
     */
    public void open(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("index out of bound");
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

        unionNeighbour(row, prevCol, rowCol);
        unionNeighbour(row, nextCol, rowCol);
        unionNeighbour(prevRow, col, rowCol);
        unionNeighbour(nextRow, col, rowCol);

        if (row == 0) {
            quPercol.union(top, rowCol);
            quIsFull.union(top, rowCol);
        }
        if ((row == (size - 1)) && !percolates()) {
            quPercol.union(bottom, rowCol);
        }

    }

    /**
     * Is the site (row, col) open? Returns a boolean.
     * @param row row index on the grid
     * @param col column index on the grid
     */
    public boolean isOpen(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("index out of bound");
        }
        return grid[row][col] == 1;
    }

    /**
     * Is the site (row, col) full? Returns a boolean.
     * @param row row index on the grid
     * @param col column index on the grid
     */
    public boolean isFull(int row, int col) {
        return quIsFull.connected(xyTo1D(row, col), top);
    }

    /**
     * Returns the number of open sites.
     */
    public int numberOfOpenSites() {
        return opened;
    }

    /**
     * Does the system percolate? Returns a boolean.
     */
    public boolean percolates() {
        return quPercol.connected(top, bottom);
    }

    /**
     * Sse for unit testing (not required, but keep this here for the autograder).
     * @param args no input
     */
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

        Percolation testB = new Percolation(1);
        testB.open(0, 0);
        boolean b = testB.percolates();
    }
}
