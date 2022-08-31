package strategy;

public class QuickSortStrategy implements SortStrategy{
    @Override
    public int[] sort(int[] dataSet) {
        System.out.println("using quick");
        return dataSet;
    }
}
