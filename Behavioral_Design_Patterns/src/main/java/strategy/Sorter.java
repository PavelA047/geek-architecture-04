package strategy;

public class Sorter {
    SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public int[] sort(int[] dataSet) {
        return strategy.sort(dataSet);
    }
}
