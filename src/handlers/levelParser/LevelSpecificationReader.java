package handlers.levelParser;

import graphics.levelData.LevelInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(Reader reader) {
        List<LevelInformation> levels = new LinkedList<>();
        BufferedReader br = new BufferedReader(reader);
        try {
            String currLine;
            boolean reading = false;
            LevelGenerator levelGenerator = new LevelGenerator();
            while ((currLine = br.readLine()) != null) {
                currLine = currLine.trim();
                if (currLine.equals("START_LEVEL")) {
                    reading = true;
                    levelGenerator = new LevelGenerator();
                    continue;
                }
                if (currLine.equals("END_LEVEL")) {
                    levels.add(levelGenerator.createLevel());
                    reading = false;
                    continue;
                }
                if (reading) {
                    levelGenerator.analizeLine(currLine);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return levels;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            LevelSpecificationReader l = new LevelSpecificationReader();
            File file = new File("try.txt");
            Reader reader = new FileReader(file);
            List<LevelInformation> levels = l.fromReader(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}