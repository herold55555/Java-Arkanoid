package handlers.levelParser;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * color.
 */
public class ColorsParser {

    private Map<String, Image> images;
    private Map<String, Color> colors;

    /**
     * cons.
     */
    public ColorsParser() {
        images = new TreeMap<>();
        colors = new TreeMap<>();
    }

    /**
     * get.
     *
     * @return images
     */
    public Map<String, Image> getImages() {
        return images;
    }

    /**
     * get.
     *
     * @return images
     */
    public Map<String, Color> getColors() {
        return colors;
    }

    /**
     * add fill.
     *
     * @param fill fill
     * @throws Exception exception
     */
    public void addFill(String fill) throws Exception {
        boolean added = false;
        try {
            added = getImageFromString(fill);
        } catch (Exception e) {
            e.getStackTrace();
        }
        try {
            added = getRGBColorFromString(fill) || added;
        } catch (Exception e) {
            e.getStackTrace();
        }
        try {
            added = getColorFromString(fill) || added;
        } catch (Exception e) {
            e.getStackTrace();
        }
        if (!added) {
            throw new Exception("could not add color");
        }
    }

    /**
     * check image.
     *
     * @param line line
     * @return image
     * @throws Exception exception
     */
    private boolean getImageFromString(String line) throws Exception {
        if (line.contains("image")) {
            String[] parts = line.split("image\\(");
            String img = parts[1].split("\\)")[0];
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(img);
                Image image = ImageIO.read(is);
                String key = analizeKeyByLine(line);
                images.put(key, image);
            } catch (Exception e) {
                e.getStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * check color.
     *
     * @param line line
     * @return color
     * @throws Exception exception
     */
    private boolean getRGBColorFromString(String line) throws Exception {
        if (line.contains("color")) {
            String[] parts = line.split("color\\(");
            String color = parts[1].split("\\)")[0];
            if (color.contains("RGB")) {
                parts = line.split("RGB\\(");
                color = parts[1].split("\\)")[0];
                String[] rgb = color.split(",");
                String key = analizeKeyByLine(line);
                try {
                    Color c = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
                    colors.put(key, c);
                } catch (Exception e) {
                    throw new Exception("color parse error");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * check color.
     *
     * @param line line
     * @return color
     * @throws Exception exception
     */
    private boolean getColorFromString(String line) throws Exception {
        if (line.contains("color")) {
            String[] parts = line.split("color\\(");
            String colorS = parts[1].split("\\)")[0];
            if (colorS.contains("RGB")) {
                throw new Exception("color parse error");
            }
            Color color = null;
            switch (colorS) {
                case "black":
                    color = Color.black;
                    break;
                case "blue":
                    color = Color.blue;
                    break;
                case "cyan":
                    color = Color.cyan;
                    break;
                case "gray":
                    color = Color.gray;
                    break;
                case "lightGray":
                    color = Color.lightGray;
                    break;
                case "green":
                    color = Color.green;
                    break;
                case "orange":
                    color = Color.orange;
                    break;
                case "pink":
                    color = Color.pink;
                    break;
                case "red":
                    color = Color.red;
                    break;
                case "white":
                    color = Color.white;
                    break;
                case "yellow":
                    color = Color.yellow;
                    break;
                default:
                    break;
            }
            if (color != null) {
                String key = analizeKeyByLine(line);
                colors.put(key, color);
                return true;
            }
            throw new Exception("color parse error");
        }
        return false;
    }

    /**
     * analizeKey.
     *
     * @param line line
     * @return string
     * @throws Exception exception
     */
    private String analizeKeyByLine(String line) throws Exception {
        if (line.startsWith("fill:")) {
            return "d";
        }
        if (line.startsWith("stroke:")) {
            return "s";
        }
        if (line.startsWith("fill-")) {
            String[] parts = line.split("fill-");
            String colorNum = parts[1].split(":")[0];
            return colorNum;
        }
        if (!line.contains("fill")) {
            return "d";
        }
        throw new Exception("cannot solve type");
    }

    /**
     * check vailed.
     *
     * @return vailed
     */
    public boolean checkVailedAndCorrect() {
        if (images.size() == 0 && colors.size() == 0) {
            return false;
        }
        if (images.containsKey("d") || colors.containsKey("d") || colors.containsKey("s")) {
            return true;
        }
        if (images.size() > 0) {
            images.put("d", images.get(images.keySet().toArray()[0]));
        }
        if (colors.size() > 0) {
            colors.put("d", colors.get(colors.keySet().toArray()[0]));
        }
        return true;
    }
}
