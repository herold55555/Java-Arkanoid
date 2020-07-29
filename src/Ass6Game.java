import play.CreateGame;
import settings.GameStandarts;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Ass6Game.
 */
public class Ass6Game {

    /**
     * main.
     *
     * @param args args
     */
    public static void main(String[] args) {
        try {
            BufferedReader reader;
            if (args.length == 0) {
                try {
                    InputStream is = ClassLoader.getSystemClassLoader().
                            getResourceAsStream(GameStandarts.DEFULT_LEVEL_SET);
                    reader = new BufferedReader(new InputStreamReader(is));
                } catch (Exception e) {
                    throw new Exception("Not Found Level Sets");
                }
            } else {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
                reader = new BufferedReader(new InputStreamReader(is));
            }
            CreateGame game = new CreateGame(reader);
            game.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
