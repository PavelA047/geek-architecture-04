package strategy;

public class Main {
    public static void main(String[] args) {
        int[] dataSet = {1, 2, 3, 4, 5, 6, 7};

        Sorter sorter = new Sorter(new QuickSortStrategy());
        sorter.sort(dataSet);

        sorter = new Sorter(new BubbleSortStrategy());
        sorter.sort(dataSet);
    }
}
