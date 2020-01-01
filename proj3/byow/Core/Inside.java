package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.lab12.Position;

import java.util.List;

public interface Inside {

    /*public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;*/

    int worldWidth();

    int worldHeight();

    TETile[][] world();

    int width();

    int height();

    Position topLeft();

    Position topRight();

    Position bottomLeft();

    Position bottomRight();

    //boolean isBuildable();

    void drawWalls();

    default void drawFloor() {
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                int x = bottomLeft().x + i;
                int y = bottomLeft().y + j;
                world()[x][y] = Tileset.FLOOR;
            }
        }
    }

    default void draw() {
        drawFloor();
        drawWalls();
    }

    default boolean isBuildable(List<Inside> insides) {
        if (!isInsideWorld()) {
            return false;
        }
        for (Inside inside : insides) {
            if (overlap(inside)) {
                return false;
            }
        }
        return true;
    }

    private boolean isInsideWorld() {
        if (isInsideWorld(bottomLeft()) && isInsideWorld(bottomRight()) &&
                isInsideWorld(topLeft()) && isInsideWorld(topRight())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInsideWorld(Position p) {
        return p.x > 0 && p.x < worldWidth()-1 && p.y > 0 && p.y < worldHeight()-1;
    }

    private boolean overlap(Inside inside) {
        Position l1 = topLeft();
        Position r1 = bottomRight();
        Position l2 = inside.topLeft();
        Position r2 = inside.bottomRight();

        // If one rectangle is on left side of other (walls cannot overlap)
        if (l1.x - r2.x > 2 || l2.x - r1.x > 2) {
            return false;
        }

        // If one rectangle is above other (walls cannot overlap)
        if (r1.y - l2.y > 2 || r2.y - l1.y > 2) {
            return false;
        }
        return true;
    }
}
