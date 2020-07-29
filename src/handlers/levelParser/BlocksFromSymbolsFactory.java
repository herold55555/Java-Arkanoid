package handlers.levelParser;

import geometry.GameObjects.Block;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * gamemap.
 */
public class BlocksFromSymbolsFactory {

    private String filePath;
    private Map<String, BlockInfo> blocks;
    private Map<String, String> defaultValuse;
    private Map<String, Integer> spaces;

    /**
     * map.
     *
     * @param file file
     */
    public BlocksFromSymbolsFactory(String file) {
        this.filePath = file;
        blocks = new TreeMap<>();
        defaultValuse = new TreeMap<>();
        spaces = new TreeMap<>();
    }

    /**
     * start anlize.
     *
     * @throws Exception exception
     */
    public void startAnalize() throws Exception {
        BufferedReader br;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            br = new BufferedReader(new InputStreamReader(is));
        } catch (Exception e) {
            System.out.println("file not found");
            throw new Exception("file not found");
        }
        String currLine;
        while ((currLine = br.readLine()) != null) {
            currLine = currLine.trim();
            if (currLine.startsWith("default")) {
                addDefualtVal(currLine);
            }
            if (currLine.startsWith("bdef")) {
                addBlock(currLine);
            }
            if (currLine.startsWith("sdef")) {
                addSpace(currLine);
            }
        }
    }

    /**
     * add default.
     * @param line line
     * @throws Exception exception
     */
    private void addDefualtVal(String line) throws Exception {
        line = line.replaceAll("default", "").trim();
        line = line.replace(" ", ":");
        String[] part = line.split(":");
        for (int i = 0; i < part.length; i += 2) {
            defaultValuse.put(part[i], part[i + 1]);
        }
    }

    /**
     * add space.
     * @param line line
     * @throws Exception exception
     */
    private void addSpace(String line) throws Exception {
        line = line.replaceAll("sdef", "").trim();
        line = line.replace(" ", ":");
        String[] part = line.split(":");
        for (int i = 0; i < part.length; i += 4) {
            if (!part[i].startsWith("symbol")) {
                throw new Exception("missing data for spaces");
            }
            spaces.put(part[i + 1], Integer.parseInt(part[i + 3]));
        }
    }

    /**
     * add block.
     * @param line line
     * @throws Exception exception
     */
    private void addBlock(String line) throws Exception {
        if (!line.contains("symbol:")) {
            throw new Exception("missing symbol data for block");
        }
        String[] part = line.split("symbol:");
        String key = part[1].split(" ")[0];
        BlockInfo blockInfo = BlockInfo.createBlock(defaultValuse, line);
        blocks.put(key, blockInfo);

    }

    /**
     * get block.
     *
     * @param symbol sy
     * @param x      x
     * @param y      y
     * @return block block
     * @throws Exception exception
     */
    public Block getBlock(String symbol, int x, int y) throws Exception {
        return blocks.get(symbol).create(x, y);
    }

    /**
     * get space.
     *
     * @param symbol sy
     * @return int space width
     * @throws Exception exception
     */
    public int getSpaceWidth(String symbol) throws Exception {
        if (!spaces.containsKey(symbol)) {
            throw new Exception("missing space");
        }
        return this.spaces.get(symbol);
    }

    /**
     * Is space symbol boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean isSpaceSymbol(String key) {
        return spaces.containsKey(key);
    }

    /**
     * Is block symbol boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean isBlockSymbol(String key) {
        return blocks.containsKey(key);
    }
}