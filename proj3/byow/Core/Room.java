package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.lab12.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Room implements Inside {

    private static final int lowerBound = 2;
    private static final int upperBound = 6;


    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    static long SEED = 1000;
    static Random RANDOM = new Random(SEED);
    static int worldWidth = 90;
    static int worldHeight = 30;
    static TETile[][] world = new TETile[worldWidth][worldHeight];

    private int width;
    private int height;
    //private final int worldWidth;
    //private final int worldHeight;
    //private final boolean isBuildable;
    //private final TETile[][] world;
    private Position topLeft;
    private Position topRight;
    private Position bottomLeft;
    private Position bottomRight;

    //private final int SEED;
    //private final Random RANDOM;

    List<Integer> directions = new ArrayList<>(Arrays.asList(NORTH, EAST, SOUTH, WEST));

    public Room(Position p, int w, int h) {
        //SEED = seed;
        //RANDOM = new Random(SEED);
        //world = worldGrid;
        //worldWidth = wWidth;
        //worldHeight = wHeight;
        initializeParameters(p, w, h);
        /*
        width = w;
        height = h;
        bottomLeft = p;
        bottomRight = calculateBottomRight();
        topLeft = calculateTopLeft();
        topRight = calculateTopRight();
        */

        //isBuildable = isBuildable(insides);
    }

    public Room(List<Inside> insides) {
        initializeParameters(randomPosition(), randomLength(), randomLength());
        while (!isBuildable(insides)) {
            initializeParameters(randomPosition(), randomLength(), randomLength());
        }
    }

    private void initializeParameters(Position p, int w, int h) {
        width = w;
        height = h;
        bottomLeft = p;
        bottomRight = calculateBottomRight();
        topLeft = calculateTopLeft();
        topRight = calculateTopRight();
    }

    private static Position randomPosition() {
        int x = RANDOM.nextInt(worldWidth);
        int y = RANDOM.nextInt(worldHeight);
        return new Position(x, y);
    }

    private static int randomLength() {
        return RANDOM.nextInt(upperBound - lowerBound) + lowerBound;
    }

    private Position calculateBottomRight() {
        int x = bottomLeft.x + width - 1;
        int y = bottomLeft.y;
        return new Position(x, y);
    }

    private Position calculateTopLeft() {
        int x = bottomLeft.x;
        int y = bottomLeft.y + height - 1;
        return new Position(x, y);
    }

    private Position calculateTopRight() {
        int x = bottomLeft.x + width - 1;
        int y = bottomLeft.y + height - 1;
        return new Position(x, y);
    }



    @Override
    public void drawWalls() {
        drawHorizontalWalls();
        drawVerticalWalls();
    }

    private void drawVerticalWalls() {
        for (int i = 0; i < height + 2; i++) {
            int xLeft = bottomLeft.x - 1;
            int yLeft = bottomLeft.y - 1 + i;
            int xRight = bottomRight.x + 1;
            int yRight = bottomRight.y - 1 + i;
            world[xLeft][yLeft] = Tileset.WALL;
            world[xRight][yRight] = Tileset.WALL;
        }
    }

    private void drawHorizontalWalls() {
        for (int i = 0; i < width; i++) {
            int xDown = bottomLeft.x + i;
            int yDown = bottomLeft.y - 1;
            int xUp = topLeft.x + i;
            int yUp = topLeft.y + 1;
            world[xDown][yDown] = Tileset.WALL;
            world[xUp][yUp] = Tileset.WALL;
        }
    }

    /*@Override
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
    }*/

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
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

    /*
    public Hallway makeRandomHallway(List<Inside> insides) {
        int direction = directions.remove(RANDOM.nextInt(directions.size()-1));
        int length = RANDOM.nextInt(6) + 2;
        int xRandom = RANDOM.nextInt(width - 1);
        int yRandom = RANDOM.nextInt(height - 1);
        Position position;
        switch (direction) {
            case NORTH: position = new Position(topLeft.x + xRandom, topLeft.y + 1);
            case EAST: position = new Position(bottomRight.x + 1, bottomRight.y + yRandom);
            case SOUTH: position = new Position(bottomLeft.x + xRandom, bottomLeft.y - 1);
            case WEST: position = new Position(bottomLeft.x - 1, bottomLeft.y + yRandom);
            default: position = null;
        }
        return new Hallway(position, length, direction, world, )
    }
    */


    /*public static List<Inside> createRandomRooms(int maxRooms, Random random,
                                                 TETile[][] world, int worldWidth, int worldHeight) {
        List<Inside> rooms = new ArrayList<>();
        int numberOfRooms = 0;
        while (numberOfRooms < maxRooms) {
            Room room = createRandomRoom(random, world, worldWidth, worldHeight, rooms);
            if (room.isBuildable()) {
                room.draw();
                rooms.add(room);
                numberOfRooms += 1;
            }
        }
        return rooms;
    }

    public static Room createRandomRoom(Random random, TETile[][] world, int worldWidth,
                                        int worldHeight, List<Inside> rooms) {
        int roomWidth = random.nextInt(5) + 2;
        int roomHeight = random.nextInt(5) + 2;
        int xBottomLeftPosition = random.nextInt(worldWidth);
        int yBottomLeftPosition = random.nextInt(worldHeight);
        Position bottomLeft = new Position(xBottomLeftPosition, yBottomLeftPosition);
        return new Room(bottomLeft, roomWidth, roomHeight, world, worldWidth, worldHeight, rooms);
    }*/
}
