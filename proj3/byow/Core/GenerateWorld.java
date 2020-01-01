package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.lab12.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateWorld {

    final int WIDTH;
    final int HEIGHT;
    final long SEED;
    final Random RANDOM;
    final TETile[][] world;

    //private final int maxRooms;
    private List<Inside> insides;



    GenerateWorld(int w, int h, long s) {
        WIDTH = w;
        HEIGHT = h;
        SEED = s;
        RANDOM = new Random(SEED);
        world = createEmptyWorld(WIDTH, HEIGHT);
        setParameters();

        //maxRooms = RANDOM.nextInt(40) + 20;
        //insides = createRandomRooms(maxRooms, RANDOM, world, WIDTH, HEIGHT);
        //createRandomRooms();
        insides = new ArrayList<Inside>();

        for (int i = 0; i < 40; i++) {
            Room room = new Room(insides);
            room.draw();
            insides.add(room);
        }

        //Room room = new Room(new Position(45, 15), 5, 5);
        //room.draw();

        //int x = room.topLeft().x + 3;
        //int y = room.topLeft().y + 1;
        /*Position position = room.makeRandomHallway(RANDOM);
        Hallway hallway = new Hallway(position, 5, NORTH, world, WIDTH, HEIGHT, insides);
        hallway.draw();*/

        //HALLWAY:
        //close(); (draw wall, 3 squares)
        //findRandomBottomLeft; (return position)
        //ROOM:
        //findRandomStartHallway (return position)
    }

    private void setParameters() {
        Hallway.SEED = SEED;
        Hallway.RANDOM = RANDOM;
        Hallway.worldWidth = WIDTH;
        Hallway.worldHeight = HEIGHT;
        Hallway.world = world;

        Room.SEED = SEED;
        Room.RANDOM = RANDOM;
        Room.worldWidth = WIDTH;
        Room.worldHeight = HEIGHT;
        Room.world = world;
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
