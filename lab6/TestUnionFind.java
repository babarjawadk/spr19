import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the union find class
 *  @author Jawad
 */
public class TestUnionFind {
    @Test
    public void testBasics() {
        UnionFind ds = new UnionFind(10);


        int a = ds.parent(7);
        int b = ds.parent(4);

        int c = ds.sizeOf(7);
        int d = ds.sizeOf(9);

        ds.union(3, 4);
        boolean e = ds.connected(3, 4);
        boolean f = ds.connected(4, 5);

        ds.union(0, 9);
        ds.union(3, 9);
        ds.union(1, 9);

        boolean i = ds.connected(3, 9);

        ds.find(1);
        ds.find(3);

        boolean g = ds.connected(2, 3);
        boolean h = ds.connected(3, 9);
    }

    // test from internet
    @Test
    public void testMore() {
        int n = 5;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            assertEquals(1, unionFind.sizeOf(i));
            assertEquals(-1, unionFind.parent(i));
        }
        assertFalse(unionFind.connected(0, 2));
        assertFalse(unionFind.connected(3, 4));
        assertFalse(unionFind.connected(4, 1));

        unionFind.union(0, 1);
        unionFind.union(1, 0);
        unionFind.union(1, 2);
        unionFind.union(3, 4);
        unionFind.union(4, 2);

        assertTrue(unionFind.connected(0, 1));
        assertTrue(unionFind.connected(2, 0));
        assertTrue(unionFind.connected(3, 1));
        assertTrue(unionFind.connected(4, 2));
        assertTrue(unionFind.connected(4, 0));
        assertTrue(unionFind.connected(4, 3));
        assertTrue(unionFind.connected(2, 4));

        for (int i = 0; i < n; i++) {
            assertEquals(5, unionFind.sizeOf(i));
        }
    }

    // test from internet
    @Test
    public void testBig() {
        int n = 1000000;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n * 2; i++) {
            int v1 = (int) (Math.random() * n);
            int v2 = (int) (Math.random() * n);
            unionFind.union(v1, v2);
        }


    }

}