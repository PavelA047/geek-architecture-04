package temolateMethod;

public class Main {
    public static void main(String[] args) {
        Builder aBuilder = new AndroidBuilder();
        Builder iBuilder = new IosBuilder();

        aBuilder.build();
        iBuilder.build();
    }
}
