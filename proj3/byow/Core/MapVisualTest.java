package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class MapVisualTest {
    private static final int WIDTH = 90;
    private static final int HEIGHT = 30;
    private static final long SEED = 178;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        GenerateWorld gw = new GenerateWorld(WIDTH, HEIGHT, SEED);
        TETile[][] world = gw.getWorld();
        ter.renderFrame(world);
    }
}
