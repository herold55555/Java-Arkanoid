package handlers.gameTrackers;

/**
 * counter.
 */
public class Counter {
    private int count;

    /**
     * constractor.
     * @param num num
     */
    public Counter(int num) {
        count = num;
    }

    /**
     * constractor.
     */
    public Counter() {
        this(0);
    }

    /**
     * increase.
     * @param number number
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * decrease.
     * @param number number
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * get value.
     * @return value
     */
    public int getValue() {
        return count;
    }
}
