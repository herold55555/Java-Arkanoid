package handlers.gameManger;

import handlers.graphic.Animation;

/**
 * The interface Menu.
 *
 * @param <T> the type parameter
 */
public interface Menu<T> extends Animation {
    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param subMenu the return val
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);


    /**
     * Gets status.
     *
     * @return the status
     */
    T getStatus();
}