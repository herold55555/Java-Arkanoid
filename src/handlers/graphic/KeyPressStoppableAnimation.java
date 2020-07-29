package handlers.graphic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import settings.GameStandarts;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean running;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor the sensor
     * @param k      the k
     * @param anim   the anim
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String k, Animation anim) {
        keyboardSensor = sensor;
        key = k;
        animation = anim;
        isAlreadyPressed = true;
        running = true;
    }

    /**
     * frame.
     * @param d  DrawSurface
     * @param dt dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        animation.doOneFrame(d, dt);
        d.drawText(20, GameStandarts.WIDTH - 20, "press " + key + " to exit", 20);
        if (!keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if (keyboardSensor.isPressed(key) && !isAlreadyPressed) {
            running = false;
        }
    }

    /**
     * shouldStop.
     * @return shouldStop
     */
    public boolean shouldStop() {
        return !running;
    }
}