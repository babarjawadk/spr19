package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

import static byow.Core.Engine.WIDTH;
import static byow.Core.Engine.HEIGHT;

public class MapVisualTest {
    private static final long SEED = 1001;

    public static void generateRandomRooms1() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        GenerateWorld1 gw = new GenerateWorld1(WIDTH, HEIGHT, SEED);
        TETile[][] world = gw.getWorld();
        ter.renderFrame(world);
    }

    public static void generateRandomRooms2() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        GenerateWorld2 gw = new GenerateWorld2(WIDTH, HEIGHT, SEED);
        TETile[][] world = gw.getWorld();
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        //generateRandomRooms1();
        //generateRandomRooms2();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        GenerateWorld gw = new GenerateWorld(WIDTH, HEIGHT, SEED);
        ter.renderFrame(gw.world);


    }
}
