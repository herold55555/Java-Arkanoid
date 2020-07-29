package handlers.levelParser;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * backgrounffill.
 */
public class BackgroundFill {

    private Map<String, Image> images;
    private Map<String, Color> colors;

    /**
     * constractor.
     *
     * @param images images
     * @param colors colors
     */
    public BackgroundFill(Map<String, Image> images, Map<String, Color> colors) {
        this.images = images;
        this.colors = colors;
    }

    /**
     * defult.
     */
    public BackgroundFill() {
        this(new TreeMap<>(), new TreeMap<>());
    }

    /**
     * get images.
     *
     * @return imges
     */
    public Map<String, Image> getImages() {
        return images;
    }

    /**
     * get colors.
     *
     * @return colors
     */
    public Map<String, Color> getColors() {
        return colors;
    }

    /**
     * get next object.
     *
     * @param symbol sy
     * @return image
     */
    public Image getImage(String symbol) {
        if (images.containsKey(symbol)) {
            return images.get(symbol);
        }
        if (colors.containsKey(symbol)) {
            return null;
        }
        return images.get("d");
    }

    /**
     * color.
     *
     * @param symbol sy
     * @return color
     */
    public Color getColor(String symbol) {
        if (colors.containsKey(symbol)) {
            return colors.get(symbol);
        }
        if (images.containsKey(symbol)) {
            return null;
        }
        return colors.get("d");
    }

    /**
     * add color.
     *
     * @param c color
     */
    public void addColor(Color c) {
        colors.put("d", c);
    }

    /**
     * add color.
     *
     * @param c   color
     * @param sym sym
     */
    public void addColor(Color c, String sym) {
        colors.put(sym, c);
    }
}