package handlers.levelParser;

import geometry.GameObjects.Block;
import geometry.invisible.Point;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * block info.
 */
public class BlockInfo implements BlockCreator {

    private int width;
    private int height;
    private int hits;
    private Map<String, Color> colorMap;
    private Map<String, Image> imageMap;


    /**
     * constractor.
     */
    public BlockInfo() {
        this(0, 0, 0);
    }

    /**
     * block info.
     *
     * @param width  width
     * @param height height
     * @param hits   hits
     */
    public BlockInfo(int width, int height, int hits) {
        this.width = width;
        this.height = height;
        this.hits = hits;
        colorMap = new TreeMap<>();
        imageMap = new TreeMap<>();
    }

    /**
     * read data.
     *
     * @param defaultVal defulat
     * @param line       line
     * @throws Exception exception
     */
    private void readHeight(Map<String, String> defaultVal, String line) throws Exception {
        if (defaultVal.containsKey("height")) {
            try {
                height = Integer.parseInt(defaultVal.get("height"));
                return;
            } catch (Exception e) {
                throw new Exception("parse error");
            }
        }
        if (!line.contains("height:")) {
            throw new Exception("missing height");
        }
        String[] parts = line.split("height:");
        try {
            height = Integer.parseInt(parts[1].split(" ")[0]);
        } catch (Exception e) {
            throw new Exception("parse error");
        }
    }

    /**
     * read data.
     *
     * @param defaultVal defulat
     * @param line       line
     * @throws Exception exception
     */
    private void readWidth(Map<String, String> defaultVal, String line) throws Exception {
        if (defaultVal.containsKey("width")) {
            try {
                width = Integer.parseInt(defaultVal.get("width"));
                return;
            } catch (Exception e) {
                throw new Exception("parse error");
            }
        }
        if (!line.contains("width:")) {
            throw new Exception("missing width");
        }
        String[] parts = line.split("width:");
        try {
            width = Integer.parseInt(parts[1].split(" ")[0]);
        } catch (Exception e) {
            throw new Exception("parse error");
        }
    }

    /**
     * read data.
     *
     * @param defaultVal defulat
     * @param line       line
     * @throws Exception exception
     */
    private void readHits(Map<String, String> defaultVal, String line) throws Exception {
        if (defaultVal.containsKey("hit_points")) {
            try {
                hits = Integer.parseInt(defaultVal.get("hit_points"));
                return;
            } catch (Exception e) {
                throw new Exception("parse error");
            }
        }
        if (!line.contains("hit_points:")) {
            throw new Exception("missing hit_points");
        }
        String[] parts = line.split("hit_points:");
        try {
            hits = Integer.parseInt(parts[1].split(" ")[0]);
        } catch (Exception e) {
            throw new Exception("parse error");
        }
    }

    /**
     * read data.
     *
     * @param defaultVal defulat
     * @param line       line
     * @throws Exception exception
     */
    private void readColors(Map<String, String> defaultVal, String line) throws Exception {
        ColorsParser colorParser = new ColorsParser();
        for (String key : defaultVal.keySet()) {
            if (key.contains("fill") || key.contains("stroke")) {
                try {
                    colorParser.addFill(key + ":" + defaultVal.get(key));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        if (line.contains("fill")) {
            String[] parts = line.split("fill");
            for (int i = 1; i < parts.length; i++) {
                colorParser.addFill("fill" + parts[i].split(" ")[0]);
            }
        }
        if (line.contains("stroke")) {
            String[] parts = line.split("stroke");
            colorParser.addFill("stroke" + parts[1].split(" ")[0]);
        }
        if (!colorParser.checkVailedAndCorrect()) {
            throw new Exception("no color were found");
        }
        colorMap = colorParser.getColors();
        imageMap = colorParser.getImages();
    }

    /**
     * crate block.
     *
     * @param x x
     * @param y y
     * @return block
     */
    public Block create(int x, int y) {
        Point upperLeft = new Point(x, y);
        BackgroundFill backgroundFill = new BackgroundFill(imageMap, colorMap);
        return new Block(upperLeft, height, width, hits, backgroundFill);
    }

    /**
     * crate block info.
     *
     * @param defaultVal def
     * @param line       line
     * @return blockinfo
     * @throws Exception excption
     */
    public static BlockInfo createBlock(Map<String, String> defaultVal, String line)
            throws Exception {
        BlockInfo blockInfo = new BlockInfo();
        try {
            blockInfo.readHeight(defaultVal, line);
            blockInfo.readWidth(defaultVal, line);
            blockInfo.readHits(defaultVal, line);
            blockInfo.readColors(defaultVal, line);
        } catch (Exception e) {
            throw new Exception("could not create block");
        }
        return blockInfo;
    }
}