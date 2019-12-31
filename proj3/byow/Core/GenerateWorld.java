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
        private final Position center;
        private final boolean isInsideWorld;

        public Room(Position pos, int w, int h) {
            width = w;
            height = h;
            bottomLeft = pos;
            bottomRight = new Position(pos.x + w, pos.y);
            topLeft = new Position(pos.x, pos.y + h);
            topRight = new Position(pos.x + w, pos.y + w);
            center = new Position((pos.x + pos.x + w) / 2, (pos.y + pos.y + h) / 2);
            isInsideWorld = calculateIsInsideWorld();
        }

        public boolean isInsideWorld() {
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
            return p.x > 0 && p.x < WIDTH-1 && p.y > 0 && p.y < HEIGHT-1;
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
        createRooms();
        addRooms();
    }

    public TETile[][] getWorld() {
        return world;
    }

    private void createRooms() {
        int numberOfRooms = 0;
        while (numberOfRooms < totalNumberOfRooms) {
            int w = RANDOM.nextInt(7) + 2;
            int h = RANDOM.nextInt(7) + 2;
            int xPos = RANDOM.nextInt(WIDTH);
            int yPos = RANDOM.nextInt(HEIGHT);
            Position pos = new Position(xPos, yPos);
            Room room = new Room(pos, w, h);
            if (room.isInsideWorld() && doNotOverlap(rooms, room)) {
                rooms.add(room);
                numberOfRooms += 1;
            }
        }
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
        if (l1.x > r2.x+1 || l2.x > r1.x+1) {
            return false;
        }

        // If one rectangle is above other
        if (l1.y+1 < r2.y || l2.y+1 < r1.y) {
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
        for (int i = 0; i < room.width; i++) {
            for (int j = 0; j < room.height; j++) {
                world[pos.x + i][pos.y + j] = Tileset.FLOOR;
            }
        }
    }

    private void makeWalls(Room room) {
        Position pos = room.bottomLeft;
        makeHorizontalWall(new Position(pos.x - 1, pos.y - 1), room.width + 2);
        makeHorizontalWall(new Position(pos.x - 1, pos.y + room.height), room.width + 2);
        makeVerticalWall(new Position(pos.x - 1, pos.y - 1), room.height + 2);
        makeVerticalWall(new Position(pos.x + room.width, pos.y - 1), room.height + 2);
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
