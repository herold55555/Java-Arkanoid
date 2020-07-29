package handlers.gameManger;

/**
 * task.
 * @param <T> param
 */
public interface Task<T> {
    /**
     * run.
     * @return T
     */
    T run();
}