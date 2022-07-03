package pl.minecash.minecash.events;

public class Random {
    public static int getRandom() {
        int max = 3;
        int min = 1;
        int range = max - min + 1;

        int rand = (int)(Math.random() * range) + min;
        return rand;
    }
}
