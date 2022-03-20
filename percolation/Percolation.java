/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private final int size;
    private WeightedQuickUnionUF wquuf;

    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n <= 0) throw new IllegalArgumentException();
        grid = new boolean[n][n];
        size = n;
        wquuf = new WeightedQuickUnionUF(n*n+2); // 0, n^2+1 being virtual sites
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row-1][col-1] = true;

        openSites++;
        if (isValid(row-1, col) && isOpen(row-1, col)) {
            wquuf.union(index(row-1, col), index(row, col));
        }
        if (isValid(row+1, col) && isOpen(row+1, col)) {
            wquuf.union(index(row+1, col), index(row, col));
        }
        if (isValid(row, col-1) && isOpen(row, col-1)) {
            wquuf.union(index(row, col-1), index(row, col));
        }
        if (isValid(row, col+1) && isOpen(row, col+1)) {
            wquuf.union(index(row, col+1), index(row, col));
        }
        if (row == 1) { // virtual
            wquuf.union(0, index(row, col));
        }
        if (row == size) { // virtual
            wquuf.union(size*size+1, index(row, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row-1][col-1];
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return wquuf.find(0) == wquuf.find(index(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquuf.find(0) == wquuf.find(size*size+1);
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException();
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && row <= size && col >= 1 && col <= size;
    }

    private int index(int row, int col) {
        return (row-1)*size + (col-1) + 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        // System.out.println(args.length);
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            perc.open(row, col);
        }
        StdOut.print(perc.percolates());
        StdOut.println();
    }
}