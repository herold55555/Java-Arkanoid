package play;

import handlers.graphic.AnimationRunner;
import handlers.gameManger.StartMenu;

import java.io.Reader;

/**
 * create game.
 */
public class CreateGame {

    private AnimationRunner animationRunner;
    private Reader reader;

    /**
     * create.
     *
     * @param r r
     */
    public CreateGame(Reader r) {
        animationRunner = new AnimationRunner();
        reader = r;
    }

    /**
     * start.
     */
    public void start() {
        StartMenu startMenu = new StartMenu(animationRunner, reader);
        startMenu.openMenu();
    }
}
