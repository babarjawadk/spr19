package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private static final long SEED = 173;
    private static final Random RANDOM = new Random(SEED);


    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        int col = p.x; int bottomRow = p.y; int topRow = topRow(p.y, s);
        for (int i = 0, j = s; i < s; i += 1, j += 2) {
            addLine(world, col - i, topRow - i, j, t);
            addLine(world, col - i, bottomRow + i, j, t);
        }
    }

    private static int topRow(int bottomRow, int size) {
        return bottomRow + 2 * size - 1;
    }

    private static void addLine(TETile[][] world, int x, int y, int s, TETile t) {
        for (int i = 0; i < s; i++) {
            world[x+i][y] = t;
        }
    }

    private static TETile[][] createWorld(int w, int h) {
        TETile[][] world = new TETile[w][h];
        for (int x = 0; x < w; x += 1) {
            for (int y = 0; y < h; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(8);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            case 3: return Tileset.AVATAR;
            case 4: return Tileset.GRASS;
            case 5: return Tileset.MOUNTAIN;
            case 6: return Tileset.SAND;
            case 7: return Tileset.TREE;
            default: return Tileset.FLOOR;
        }
    }

    private static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int n) {
        for (int i = 0, j = p.y; i < n; i += 1, j += 6) {
            addHexagon(world, new Position(p.x, j), s, randomTile());
        }
    }

    private static Position bottomRight(Position p) {
        return new Position(p.x + 5, p.y - 3);
    }

    private static Position topRight(Position p) {
        return new Position(p.x + 5, p.y + 3);
    }

    private static void drawTessalation(TETile[][] world, int singleHexSize) {
        Position col1BottomLeft = new Position(2, 6);
        Position col2BottomLeft = bottomRight(col1BottomLeft);
        Position col3BottomLeft = bottomRight(col2BottomLeft);
        Position col4BottomLeft = topRight(col3BottomLeft);
        Position col5BottomLeft = topRight(col4BottomLeft);

        drawRandomVerticalHexes(world, col1BottomLeft, singleHexSize, 3);
        drawRandomVerticalHexes(world, col2BottomLeft, singleHexSize, 4);
        drawRandomVerticalHexes(world, col3BottomLeft, singleHexSize, 5);
        drawRandomVerticalHexes(world, col4BottomLeft, singleHexSize, 4);
        drawRandomVerticalHexes(world, col5BottomLeft, singleHexSize, 3);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = createWorld(WIDTH, HEIGHT);
        drawTessalation(world, 3);
        ter.renderFrame(world);
    }

}
