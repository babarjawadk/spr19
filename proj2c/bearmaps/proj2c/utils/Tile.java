package bearmaps.proj2c.utils;

import static bearmaps.proj2c.utils.Constants.*;

/**
 * A utility class to hold information about a particular tile.
 * Created by Jawad
 */
public class Tile {
    public final int depth;
    public final int x;
    public final int y;
    public final String filename;
    public final double ullon;
    public final double ullat;
    public final double lrlon;
    public final double lrlat;

    public Tile(int d, int xLab, int yLab) {
        depth = d;
        x = xLab;
        y = yLab;

        if (depth < 0 || depth > 7 || x >= Math.pow(2, depth) || x < 0 || y >= Math.pow(2, depth) || y < 0) {
            filename = null;
            ullon = 0.0;
            ullat = 0.0;
            lrlon = 0.0;
            lrlat = 0.0;
        } else {
            filename = "d" + depth + "_x" + x + "_y" + y + ".png";
            double width = tileWidth(depth);
            double height = tileHeight(depth);
            ullon = ROOT_ULLON + x * width;
            ullat = ROOT_ULLAT - y * height;
            lrlon = ullon + width;
            lrlat = ullat - height;
        }
    }

    public boolean notFound() {
        return (filename == null);
    }

    private static double tileWidth(int depth) {
        return Math.abs(ROOT_ULLON - ROOT_LRLON) / Math.pow(2, depth);
    }

    private static double tileHeight(int depth) {
        return Math.abs(ROOT_ULLAT - ROOT_LRLAT) / Math.pow(2, depth);
    }

    public static double lonDPP(double width, double height) {
        return Math.abs(width/height);
    }

    public static double lonDPP(int depth) {
        return lonDPP(tileWidth(depth), TILE_SIZE);
    }

    public static int findDepth(double lonDPPAsked) {
        int depth = 0;
        double lonDPPFile = lonDPP(depth);
        while (depth < 7 && lonDPPAsked < lonDPPFile) {
            depth += 1;
            lonDPPFile = lonDPP(depth);
        }
        return depth;
    }

    public static Tile findTile(double lon, double lat, int depth) {
        int x = (int) Math.floor(-(ROOT_ULLON - lon) / tileWidth(depth));
        int y = (int) Math.floor((ROOT_ULLAT - lat) / tileHeight(depth));
        return new Tile(depth, x, y);
    }

    public static String[][] makeGrid(Tile ul, Tile lr) {
        int depth = ul.depth;
        if (ul.notFound()) {
            int x = Math.max(0, ul.x);
            int y = Math.max(0, ul.y);
            ul = new Tile(depth, x, y);
        }
        if (lr.notFound()) {
            int max = (int) Math.pow(2, depth) - 1;
            int x = Math.min(lr.x, max);
            int y = Math.min(lr.y, max);
            lr = new Tile(depth, x, y);
        }

        int iMax = lr.y-ul.y+1;
        int jMax = lr.x-ul.x+1;
        String[][] results = new String[iMax][jMax];

        for (int i = 0; i < iMax; i++) {
            for (int j = 0; j < jMax; j++) {
                Tile tile = new Tile(depth, ul.x+j, ul.y+i);
                results[i][j] = tile.filename;
            }
        }
        return results;
    }
}
