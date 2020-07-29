package handlers.graphic;

import biuoop.DrawSurface;

import java.util.LinkedList;
import java.util.List;

/**
 * sprites.
 */
public class SpriteCollection {
    private List<Sprite> objects;

    /**
     * Constractor.
     */
    public SpriteCollection() {
        this.objects = new LinkedList<>();
    }

    /**
     * add sprite to list.
     *
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        this.objects.add(s);
    }

    /**
     * remove sprite.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        objects.remove(s);
    }

    /**
     * notify all sprites time passed.
     *
     * @param dt dt
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).timePassed(dt);
        }
        /*try {
            for (Sprite obj : this.objects) {
                obj.timePassed();
            }
        } catch (Exception e) {
            int x = 5;
        }*/
    }

    /**
     * draw all sprites.
     *
     * @param d draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite obj : this.objects) {
            obj.drawOn(d);
        }
    }
}
