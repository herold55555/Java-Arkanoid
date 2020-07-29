package handlers.gameManger;

/**
 * The type Menu options.
 *
 * @param <T> the type parameter
 */
public class MenuOptions<T> {

    private String key;
    private String message;
    private T returnValue;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Menu options.
     *
     * @param k         the k
     * @param m         the m
     * @param returnVal the return val
     */
    public MenuOptions(String k, String m, T returnVal) {
        key = k;
        message = m;
        returnValue = returnVal;
        isAlreadyPressed = true;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets return value.
     *
     * @return the return value
     */
    public T getReturnValue() {
        return returnValue;
    }

    /**
     * Gets is already pressed.
     *
     * @return the is already pressed
     */
    public boolean getIsAlreadyPressed() {
        return isAlreadyPressed;
    }

    /**
     * Sets not pressed.
     */
    public void setNotPressed() {
        isAlreadyPressed = false;
    }

    /**
     * Sets already pressed.
     */
    public void setAlreadyPressed() {
        isAlreadyPressed = true;
    }
}
