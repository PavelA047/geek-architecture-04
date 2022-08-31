package strategy;

public class BubbleSortStrategy implements SortStrategy{
    @Override
    public int[] sort(int[] dataSet) {
        System.out.println("using bubble");
        return dataSet;
    }
}
