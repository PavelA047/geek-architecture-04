package iteratorRandom;

import java.util.Iterator;
import java.util.Random;

public class RandomIterator implements Iterable<Integer> {

    private final int[] integers;
    private int counter = 0;

    public RandomIterator(int number) {
        integers = new int[number];
        Random random = new Random();
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt(200);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                try {
                    return integers[counter] != 0;
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }

            @Override
            public Integer next() {
                return integers[counter++];
            }
        };
    }
}
