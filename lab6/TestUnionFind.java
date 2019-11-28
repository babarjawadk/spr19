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

}
