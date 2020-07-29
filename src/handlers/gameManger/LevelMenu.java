package handlers.gameManger;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import graphics.screens.ShowGame;
import handlers.graphic.Animation;
import handlers.graphic.AnimationRunner;
import handlers.graphic.Sprite;
import settings.GameStandarts;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Level menu.
 */
public class LevelMenu implements Animation, Task<Void> {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private List<MenuOptions<ShowGame>> menuOptions;
    private String title;
    private Sprite background;
    private ShowGame selectedOption;
    private boolean running;

    /**
     * Instantiates a new Level menu.
     *
     * @param titl   the titl
     * @param runner the runner
     * @param back   the back
     */
    public LevelMenu(String titl, AnimationRunner runner, Sprite back) {
        animationRunner = runner;
        keyboardSensor = runner.getKeyboardSensor();
        title = titl;
        menuOptions = new LinkedList<>();
        running = true;
        selectedOption = null;
        background = back;
    }

    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    public void addSelection(String key, String message, ShowGame returnVal) {
        menuOptions.add(new MenuOptions<>(key, message, returnVal));
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public ShowGame getStatus() {
        reset();
        return selectedOption;
    }

    /**
     * frame.
     * @param d  DrawSurface
     * @param dt dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        background.drawOn(d);
        int index = 0;
        d.setColor(Color.BLACK);
        d.drawText(GameStandarts.HEIGHT / 2 - 40, 100, title, 35);
        for (MenuOptions<ShowGame> menuOption : menuOptions) {
            d.drawText(GameStandarts.HEIGHT / 2 - 150, 150 + index * 30, "Press On", 25);
            d.drawText(GameStandarts.HEIGHT / 2 - 40, 150 + index * 30, "\"" + menuOption.getKey() + "\"", 25);
            d.drawText(GameStandarts.HEIGHT / 2, 150 + index * 30, "to", 25);
            d.drawText(GameStandarts.HEIGHT / 2 + 30, 150 + index * 30, menuOption.getMessage(), 25);
            index++;
        }
        checkKeyPressed();
    }

    /**
     * shouldStop.
     *
     * @return shouldStop
     */
    public boolean shouldStop() {
        return !running;
    }

    /**
     * run.
     * @return Void
     */
    public Void run() {
        animationRunner.run(this);
        this.getStatus().run();
        return null;
    }

    /**
     * check key.
     */
    private void checkKeyPressed() {
        for (MenuOptions<ShowGame> menuOption : menuOptions) {
            if (!keyboardSensor.isPressed(menuOption.getKey())) {
                menuOption.setNotPressed();
            }
            if (keyboardSensor.isPressed(menuOption.getKey()) && !menuOption.getIsAlreadyPressed()) {
                selectedOption = menuOption.getReturnValue();
                running = false;
                break;
            }
        }
    }

    /**
     * reset.
     */
    private void reset() {
        running = true;
        for (MenuOptions<ShowGame> menuOption : menuOptions) {
            menuOption.setAlreadyPressed();
        }
    }
}