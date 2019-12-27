package bearmaps.proj2ab;

import java.util.List;

public class KDTree implements PointSet {

    private class Node {
        Point point;
        Node left;
        Node right;

        Node(Point p, Node l, Node r) {
            point = p;
            left = l;
            right = r;
        }

        Point getPoint() {
            return point;
        }
    }

    Node root;

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = insert(root, p, true);
        }
    }

    private boolean compare(Point a, Point b, Boolean xOrY) {
        if (xOrY) {
            if (a.getX() >= b.getX()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (a.getY() >= b.getY()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private Node insert(Node node, Point point, Boolean xOrY) {
        if (node == null) {
            return new Node(point, null, null);
        }
        if (compare(point, node.point, xOrY)) {
            node.right = insert(node.right, point, !xOrY);
        } else {
            node.left = insert(node.left, point, !xOrY);
        }
        return node;
    }

    private Node nearest(Node n, Point goal, Node best, Boolean xOrY) {
        if (n == null) {
            return best;
        }
        if (Point.distance(best.getPoint(), goal) > Point.distance(n.getPoint(), goal)) {
            best = n;
        }
        Node goodSide;
        Node badSide;
        if (compare(n.getPoint(), goal, xOrY)) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearest(goodSide, goal, best, !xOrY);
        Double diff;
        if (xOrY) { diff = n.getPoint().getX() - goal.getX(); } else { diff = n.getPoint().getY() - goal.getY(); }
        if (Point.distance(best.getPoint(), goal) > Math.pow(diff, 2)) {
            best = nearest(badSide, goal, best, !xOrY);
        }
        return best;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node minNode = nearest(root, goal, root, true);
        return minNode.getPoint();
    }

    public static void main(String[] args) {
        Point A = new Point(2, 3);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        List<Point> list = List.of(A, B, C, D, E, F);
        KDTree test = new KDTree(list);

    }

}
