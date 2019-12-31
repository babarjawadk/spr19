package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class MapVisualTest {
    private static final int WIDTH = 90;
    private static final int HEIGHT = 30;
    private static final long SEED = 179;

    public static void generateRandomRooms() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        GenerateWorldOld gw = new GenerateWorldOld(WIDTH, HEIGHT, SEED);
        TETile[][] world = gw.getWorld();
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        //generateRandomRooms();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        GenerateWorld gw = new GenerateWorld(WIDTH, HEIGHT, SEED);
        TETile[][] world = gw.getWorld();
        ter.renderFrame(world);
    }
}
