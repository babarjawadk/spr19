package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.lab12.Position;

import java.util.List;
import java.util.Random;

import static byow.Core.GenerateWorld.createEmptyWorld;

public class Hallway implements Inside {

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    static int SEED = 1000;
    static Random RANDOM = new Random(SEED);
    static int worldWidth = 90;
    static int worldHeight = 30;
    static TETile[][] world = new TETile[worldWidth][worldHeight];

    private final int direction;
    private final int length;
    /*private final int worldWidth;
    private final int worldHeight;*/
    //private final boolean isBuildable;
    //private final TETile[][] world;
    private final Position topLeft;
    private final Position topRight;
    private final Position bottomLeft;
    private final Position bottomRight;




    public Hallway(Position position, int hallwayLength, int dir, TETile[][] worldGrid,
                   int wWidth, int wHeight, List<Inside> insides, int seed) {
        SEED = seed;
        RANDOM = new Random(SEED);
        world = worldGrid;
        worldWidth = wWidth;
        worldHeight = wHeight;
        length = hallwayLength;
        direction = dir;
        bottomLeft = calculateBottomLeft(position);
        bottomRight = calculateBottomRight(position);
        topLeft = calculateTopLeft(position);
        topRight = calculateTopRight(position);
        //isBuildable = isBuildable(insides);
    }

    private Position calculateTopLeft(Position position) {
        int x = position.x; int y = position.y; int n = length - 1;
        switch (direction) {
            case NORTH: return new Position(x, y+n);
            case EAST: return position;
            case SOUTH: return position;
            case WEST: return new Position(x-n, y);
            default: return null;
        }
    }

    private Position calculateTopRight(Position position) {
        int x = position.x; int y = position.y; int n = length - 1;
        switch (direction) {
            case NORTH: return new Position(x, y+n);
            case EAST: return new Position(x+n, y);
            case SOUTH: return position;
            case WEST: return position;
            default: return null;
        }
    }

    private Position calculateBottomLeft(Position position) {
        int x = position.x; int y = position.y; int n = length - 1;
        switch (direction) {
            case NORTH: return position;
            case EAST: return position;
            case SOUTH: return new Position(x, y-n);
            case WEST: return new Position(x-n, y);
            default: return null;
        }
    }

    private Position calculateBottomRight(Position position) {
        int x = position.x; int y = position.y; int n = length - 1;
        switch (direction) {
            case NORTH: return position;
            case EAST: return new Position(x+n, y);
            case SOUTH: return new Position(x, y-n);
            case WEST: return position;
            default: return null;
        }
    }

    public Position emptySide() {
        switch (direction) {
            case NORTH: return topLeft;
            case EAST: return topRight;
            case SOUTH: return bottomRight;
            case WEST: return bottomLeft;
            default: return null;
        }
    }

    @Override
    public void drawWalls() {
        if (direction == NORTH || direction == SOUTH) {
            for (int i = 0; i < length; i++) {
                int xLeft = bottomLeft.x - 1;
                int xRight = topLeft.x + 1;
                int y = bottomLeft.y + i;
                world[xLeft][y] = Tileset.WALL;
                world[xRight][y] = Tileset.WALL;
            }
        } else {
            for (int i = 0; i < length; i++) {
                int x = bottomLeft.x + i;
                int yUp = bottomLeft.y + 1;
                int yDown = bottomRight.y - 1;
                world[x][yUp] = Tileset.WALL;
                world[x][yDown] = Tileset.WALL;
            }
        }
    }

    public int direction() {
        return direction;
    }

    @Override
    public int worldWidth() {
        return worldWidth;
    }

    @Override
    public int worldHeight() {
        return worldHeight;
    }

    @Override
    public TETile[][] world() {
        return world;
    }

    @Override
    public int width() {
        if (direction == NORTH || direction == SOUTH) {
            return 1;
        }
        return length;
    }

    @Override
    public int height() {
        if (direction == NORTH || direction == SOUTH) {
            return length;
        }
        return 1;
    }

    @Override
    public Position topLeft() {
        return topLeft;
    }

    @Override
    public Position topRight() {
        return topRight;
    }

    @Override
    public Position bottomLeft() {
        return bottomLeft;
    }

    @Override
    public Position bottomRight() {
        return bottomRight;
    }

    /*@Override
    public boolean isBuildable() {
        return isBuildable;
    }*/
}
