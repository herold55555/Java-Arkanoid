package graphics.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import handlers.gameManger.MenuOptions;
import handlers.gameManger.Menu;
import handlers.gameManger.Task;
import handlers.graphic.AnimationRunner;
import handlers.graphic.Sprite;
import settings.GameStandarts;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private List<MenuOptions<T>> menuOptions;
    private String title;
    private Sprite background;
    private T selectedOption;
    private boolean running;

    /**
     * Instantiates a new Menu animation.
     *
     * @param titl   the titl
     * @param runner the runner
     * @param back   the back
     */
    public MenuAnimation(String titl, AnimationRunner runner, Sprite back) {
        animationRunner = runner;
        keyboardSensor = runner.getKeyboardSensor();
        title = titl;
        menuOptions = new LinkedList<>();
        running = true;
        selectedOption = null;
        background = back;
    }

    /**
     * add.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    public void addSelection(String key, String message, T returnVal) {
        menuOptions.add(new MenuOptions<>(key, message, returnVal));
    }

    /**
     * Add selection.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the return val
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

    }

    /**
     * get status.
     *
     * @return status
     */
    public T getStatus() {
        reset();
        return selectedOption;
    }

    /**
     * doOneFrame.
     *
     * @param dt dt
     * @param d  DrawSurface
     */
    public void doOneFrame(DrawSurface d, double dt) {
        background.drawOn(d);
        int index = 0;
        d.setColor(Color.BLACK);
        d.drawText(GameStandarts.HEIGHT / 2 - 40, 100, title, 35);
        for (MenuOptions<T> menuOption : menuOptions) {
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
     * Run t.
     *
     * @return the t
     */
    public T run() {
        animationRunner.run(this);
        ((Task) this.getStatus()).run();
        return null;
    }

    /**
     * check key pressed.
     */
    private void checkKeyPressed() {
        for (MenuOptions<T> menuOption : menuOptions) {
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
        for (MenuOptions<T> menuOption : menuOptions) {
            menuOption.setAlreadyPressed();
        }
    }
}
