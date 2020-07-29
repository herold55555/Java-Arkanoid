package handlers.levelParser;

import geometry.GameObjects.Block;
import geometry.invisible.Velocity;
import graphics.background.Background;
import graphics.levelData.LevelInformation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Level generator.
 */
public class LevelGenerator {

    private Map<String, String> levelInfo;
    private List<String> blockInfo;
    private BlocksFromSymbolsFactory gameBlocks;
    private boolean readingBlocks;

    /**
     * Instantiates a new Level generator.
     */
    public LevelGenerator() {
        levelInfo = new TreeMap<>();
        blockInfo = new LinkedList<>();
        readingBlocks = false;
    }

    /**
     * Analize line.
     *
     * @param line the line
     */
    public void analizeLine(String line) {
        if (line.equals("START_BLOCKS")) {
            readingBlocks = true;
            return;
        }
        if (line.equals("END_BLOCKS")) {
            readingBlocks = false;
            return;
        }
        if (readingBlocks) {
            addBlockInfo(line);
            return;
        }
        if (line.contains(":")) {
            String[] parts = line.trim().split(":");
            levelInfo.put(parts[0].trim(), parts[1].trim());
        }
    }

    /**
     * Add block info.
     *
     * @param line the line
     */
    public void addBlockInfo(String line) {
        blockInfo.add(line.trim());
    }

    /**
     * Create level level information.
     *
     * @return the level information
     * @throws Exception the exception
     */
    public LevelInformation createLevel() throws Exception {
        List<Block> blocks;
        try {
            definedBlocks();
            blocks = createBlocks();
        } catch (Exception e) {
            throw new Exception("trouble in parsing blocks");
        }
        try {
            return initialLevel(blocks);
        } catch (Exception e) {
            throw new Exception("trouble in parsing level");
        }
    }

    /**
     * defined.
     *
     * @throws Exception exception
     */
    private void definedBlocks() throws Exception {
        String blockFile = "block_definitions";
        if (!levelInfo.containsKey(blockFile)) {
            throw new Exception("missing block file");
        }
        gameBlocks = BlocksDefinitionReader.fromReader(levelInfo.get(blockFile));
    }

    /**
     * create.
     *
     * @return list
     * @throws Exception create
     */
    private List<Block> createBlocks() throws Exception {
        String xStartPoint = "blocks_start_x", yStartPoint = "blocks_start_y";
        if (!levelInfo.containsKey(xStartPoint) || !levelInfo.containsKey(yStartPoint)) {
            throw new Exception("missing block start point");
        }
        String rowHeight = "row_height";
        if (!levelInfo.containsKey(rowHeight)) {
            throw new Exception("missing row Height");
        }
        List<Block> blocks = new LinkedList<>();
        int xStart;
        int yPoint;
        int rowSpaceHeight;
        try {
            xStart = Integer.parseInt(levelInfo.get(xStartPoint));
            yPoint = Integer.parseInt(levelInfo.get(yStartPoint));
            rowSpaceHeight = Integer.parseInt(levelInfo.get(rowHeight));
        } catch (Exception e) {
            throw new Exception("error parsing block start point");
        }
        for (String line : blockInfo) {
            int xPoint = xStart;
            for (Character c : line.toCharArray()) {
                if (gameBlocks.isBlockSymbol(c + "")) {
                    Block tmp = gameBlocks.getBlock(c + "", xPoint, yPoint);
                    xPoint += tmp.getHeight();
                    blocks.add(tmp);
                } else if (gameBlocks.isSpaceSymbol(c + "")) {
                    xPoint += gameBlocks.getSpaceWidth(c + "");
                } else {
                    throw new Exception("could not solve char: " + c);
                }
            }
            yPoint += rowSpaceHeight;
        }
        return blocks;
    }

    /**
     * initial level.
     *
     * @param blocks clocks
     * @return level
     * @throws Exception exception
     */
    private LevelInformation initialLevel(List<Block> blocks) throws Exception {
        String levelName = parseToString("level_name");
        int paddleSpeed = parseToInteger("paddle_speed");
        int paddleWidth = parseToInteger("paddle_width");
        int numBlocks = parseToInteger("num_blocks");
        List<Velocity> velocities = parseVelocities();
        Background background = parseBackground();
        Level newLevel = new Level();
        newLevel.setBackground(background);
        newLevel.setBlockInGame(blocks);
        newLevel.setLevelName(levelName);
        newLevel.setNumberOfBlocks(numBlocks);
        newLevel.setPaddleSpeed(paddleSpeed);
        newLevel.setPaddleWidth(paddleWidth);
        newLevel.setVelocities(velocities);
        if (!newLevel.checkVailed()) {
            throw new Exception("could not analize level");
        }
        return newLevel;
    }

    /**
     * parse string.
     *
     * @param key key
     * @return string
     * @throws Exception exception
     */
    private String parseToString(String key) throws Exception {
        if (!levelInfo.containsKey(key)) {
            throw new Exception("missing " + key);
        }
        return levelInfo.get(key);
    }

    /**
     * parse int.
     *
     * @param key key
     * @return int
     * @throws Exception exception
     */
    private int parseToInteger(String key) throws Exception {
        if (!levelInfo.containsKey(key)) {
            throw new Exception("missing " + key);
        }
        return Integer.parseInt(levelInfo.get(key));
    }

    /**
     * parse velocities.
     *
     * @return list
     * @throws Exception exception
     */
    private List<Velocity> parseVelocities() throws Exception {
        String velocity = "ball_velocities";
        if (!levelInfo.containsKey(velocity)) {
            throw new Exception("missing " + velocity);
        }
        List<Velocity> velocities = new LinkedList<>();
        for (String ballV : levelInfo.get(velocity).split(" ")) {
            try {
                double angle = Double.parseDouble(ballV.split(",")[0]);
                double speed = Double.parseDouble(ballV.split(",")[1]);
                velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            } catch (Exception e) {
                throw new Exception("could not parse velocity");
            }
        }
        return velocities;
    }

    /**
     * background.
     *
     * @return background
     * @throws Exception exception
     */
    private Background parseBackground() throws Exception {
        String back = "background";
        if (!levelInfo.containsKey(back)) {
            throw new Exception("missing " + back);
        }
        ColorsParser colorParser = new ColorsParser();
        colorParser.addFill(levelInfo.get(back));
        colorParser.checkVailedAndCorrect();
        return new Background(new BackgroundFill(colorParser.getImages(), colorParser.getColors()));
    }
}
