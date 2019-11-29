public class UnionFind {

    private int[] parent;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parent.length || vertex < 0) {
            throw new ArrayIndexOutOfBoundsException("vertex given too high");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return Math.abs(parent(find(v1)));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);

        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {

        int v1Size = sizeOf(v1);
        int v2Size = sizeOf(v2);

        int v1Root = find(v1);
        int v2Root = find(v2);

        if (v1Root == v2Root) {
            return;
        }

        if (v1Size > v2Size) {
            parent[v2Root] = v1Root;
            parent[v1Root] = - v1Size - v2Size;
        } else {
            parent[v1Root] = v2Root;
            parent[v2Root] = - v1Size - v2Size;

        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (parent(vertex) < 0) {
            return vertex;
        }
        parent[vertex] = find(parent(vertex));

        return parent(vertex);
    }

}
