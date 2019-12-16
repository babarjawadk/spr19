package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {

    @Test
    public void basicTest1() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree kdTree = new KDTree(List.of(p1, p2, p3));
        Point nearestKDTree = kdTree.nearest(3.0, 4.0);

        NaivePointSet naivePointSet = new NaivePointSet(List.of(p1, p2, p3));
        Point nearestNaivePointSet = naivePointSet.nearest(3.0, 4.0);

        assertEquals(nearestKDTree, nearestNaivePointSet);
    }

    @Test
    public void basicTest2() {
        Point p1 = new Point(7, 3);
        Point p2 = new Point(9, 9);
        Point p3 = new Point(6, 8);
        Point p4 = new Point(0, 6);
        Point p5 = new Point(5, 9);

        KDTree kdTree = new KDTree(List.of(p1, p2, p3, p4, p5));
        Point nearestKDTree = kdTree.nearest(9, 3);

        NaivePointSet naivePointSet = new NaivePointSet(List.of(p1, p2, p3, p3, p4, p5));
        Point nearestNaivePointSet = naivePointSet.nearest(9, 3);

        assertEquals(nearestKDTree, nearestNaivePointSet);
    }

    @Test
    public void randomizedTest() {
        List<Point> randomPoints = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            randomPoints.add(new Point(generateRandomInt(100), generateRandomInt(100)));
        }

        PointSet kdTree = new KDTree(randomPoints);
        PointSet naivePointSet = new NaivePointSet(randomPoints);


        for (int i = 0; i < 1000; i++) {
            int randomX = generateRandomInt(100);
            int randomY = generateRandomInt(100);

            Point nearestKDTree = kdTree.nearest(randomX, randomY);
            Point nearestNaivePointSet = naivePointSet.nearest(randomX, randomY);

            Double dist1 = Point.distance(nearestKDTree, new Point(randomX, randomY));
            Double dist2 = Point.distance(nearestNaivePointSet, new Point(randomX, randomY));

            assertTrue(dist1.equals(dist2) || nearestKDTree.equals(nearestNaivePointSet));
            //assertEquals(dist1, dist2);
            //assertEquals(nearestKDTree, nearestNaivePointSet);
        }
    }

    @Test
    public void timingTest() {
        List<Point> randomPoints = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            randomPoints.add(new Point(generateRandomInt(100), generateRandomInt(100)));
        }

        List<Point> randomQueries = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            randomQueries.add(new Point(generateRandomInt(100), generateRandomInt(100)));
        }

        //NaivePointSet
        PointSet naivePointSet = new NaivePointSet(randomPoints);

        Stopwatch sw = new Stopwatch();
        for (Point p : randomQueries) {
            naivePointSet.nearest(p.getX(), p.getY());
        }
        Double naiveTime = sw.elapsedTime();


        //KDTree
        PointSet kdTree = new KDTree(randomPoints);

        sw = new Stopwatch();
        for (Point p : randomQueries) {
            kdTree.nearest(p.getX(), p.getY());
        }
        Double kdTime = sw.elapsedTime();

        System.out.println("kdTime and naiveTime: " + kdTime + " and " + naiveTime + "  seconds");
        //assertTrue(kdTime/naiveTime < 0.1);

    }

    private static int generateRandomInt(int upperRange){
        Random random = new Random();
        return random.nextInt(upperRange);
    }
}
