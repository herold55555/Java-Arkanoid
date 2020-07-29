package settings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * my logger.
 */
public class Logger {
    private static boolean log = false;
    private BufferedWriter bw;
    private FileWriter fw;

    /**
     * Constractor.
     */
    public Logger() {
        try {
            if (log) {
                fw = new FileWriter("C:\\Users\\user\\Desktop\\log.txt");
                bw = new BufferedWriter(fw);
            }
        } catch (IOException e) {
            exeptionIO();
        }
    }

    /**
     * write to log.
     *
     * @param line txt to write.
     */
    public void write(String line) {
        try {
            if (log) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            exeptionIO();
        }
    }

    /**
     * close loge.
     */
    public void close() {
        try {
            if (log) {
                bw.close();
                fw.close();
            }
        } catch (IOException e) {
            exeptionIO();
        }
    }

    /**
     * IO exeption.
     */
    private void exeptionIO() {

    }
}
