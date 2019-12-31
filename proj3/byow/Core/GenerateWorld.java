package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.lab12.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateWorld {

    private class Room {
        private final int width;
        private final int height;
        private final Position topLeft;
        private final Position topRight;
        private final Position bottomLeft;
        private final Position bottomRight;
        private final boolean isInsideWorld;

        private Room(Position pos, int w, int h) {
            width = w;
            height = h;
            bottomLeft = pos;
            bottomRight = new Position(pos.x + w, pos.y);
            topLeft = new Position(pos.x, pos.y + h);
            topRight = new Position(pos.x + w, pos.y + w);
            isInsideWorld = calculateIsInsideWorld();
        }

        private boolean isInsideWorld() {
            return isInsideWorld;
        }

        private boolean calculateIsInsideWorld() {
            if (isInsideWorld(bottomLeft) && isInsideWorld(bottomRight) && isInsideWorld(topLeft) &&
                    isInsideWorld(topRight)) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isInsideWorld(Position p) {
            return p.x >= 0 && p.x < WIDTH && p.y >= 0 && p.y < HEIGHT;
        }
    }

    private class Hallway {
        private final int length;
        private final boolean hasCorner;
        private List<Room> connectedRooms = new ArrayList<>();

        private Hallway(int l, boolean c) {
            length = l;
            hasCorner = c;
        }
    }

    private final int WIDTH;
    private final int HEIGHT;
    private final long SEED;
    private final Random RANDOM;
    private final int totalNumberOfRooms;
    private List<Room> rooms = new ArrayList<>();

    TETile[][] world;

    GenerateWorld(int w, int h, long s) {
        WIDTH = w;
        HEIGHT = h;
        SEED = s;
        RANDOM = new Random(SEED);
        world = createEmptyWorld(WIDTH, HEIGHT);
        totalNumberOfRooms = RANDOM.nextInt(25) + 11;


        //Room room = createRandomRoom();
        //addRoom(room);


        createRooms();
        addRooms();
    }

    public TETile[][] getWorld() {
        return world;
    }

    private void createRooms() {
        int numberOfRooms = 0;
        while (numberOfRooms < totalNumberOfRooms) {
            Room room = createRandomRoom();
            if (room.isInsideWorld() && doNotOverlap(rooms, room)) {
                rooms.add(room);
                numberOfRooms += 1;
            }
        }
    }

    private Room createRandomRoom() {
        int w = RANDOM.nextInt(6) + 4;
        int h = RANDOM.nextInt(6) + 4;
        int xPos = RANDOM.nextInt(WIDTH);
        int yPos = RANDOM.nextInt(HEIGHT);
        Position pos = new Position(xPos, yPos);
        return new Room(pos, w, h);
    }

    private static boolean doNotOverlap(List<Room> rooms, Room newRoom) {
        for (Room room : rooms) {
            if (doOverlap(room, newRoom)) {
                return false;
            }
        }
        return true;
    }

    private static boolean doOverlap(Room room1, Room room2) {
        Position l1 = room1.topLeft;
        Position r1 = room1.bottomRight;
        Position l2 = room2.topLeft;
        Position r2 = room2.bottomRight;

        // If one rectangle is on left side of other
        if (l1.x > r2.x || l2.x > r1.x) {
            return false;
        }

        // If one rectangle is above other
        if (l1.y < r2.y || l2.y < r1.y) {
            return false;
        }

        return true;
    }

    private void addRooms() {
        for (Room room : rooms) {
            addRoom(room);
        }
    }

    private void addRoom(Room room) {
        makeFloor(room);
        makeWalls(room);
    }

    private void makeFloor(Room room) {
        Position pos = room.bottomLeft;
        for (int i = 0; i < room.width-2; i++) {
            for (int j = 0; j < room.height-2; j++) {
                world[pos.x + 1 + i][pos.y + 1 + j] = Tileset.FLOOR;
            }
        }
    }

    private void makeWalls(Room room) {
        Position pos = room.bottomLeft;
        makeHorizontalWall(new Position(pos.x, pos.y), room.width);
        makeHorizontalWall(new Position(pos.x, pos.y + room.height - 1), room.width);
        makeVerticalWall(new Position(pos.x, pos.y + 1), room.height-2);
        makeVerticalWall(new Position(pos.x + room.width - 1, pos.y + 1), room.height-2);
    }

    private void makeVerticalWall(Position pos, int length) {
        for (int i = 0; i < length; i++) {
            world[pos.x][pos.y + i] = Tileset.WALL;
        }
    }

    private void makeHorizontalWall(Position pos, int length) {
        for (int i = 0; i < length; i++) {
            world[pos.x + i][pos.y] = Tileset.WALL;
        }
    }

    private static TETile[][] createEmptyWorld(int w, int h) {
        TETile[][] world = new TETile[w][h];
        for (int x = 0; x < w; x += 1) {
            for (int y = 0; y < h; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }
}