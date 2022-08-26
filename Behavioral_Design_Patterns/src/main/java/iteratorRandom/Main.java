package iteratorRandom;

public class Main {
    public static void main(String[] args) {
        RandomIterator randomIterator = new RandomIterator(5);
        for (int i : randomIterator) {
            System.out.println(i);
        }
    }
}
