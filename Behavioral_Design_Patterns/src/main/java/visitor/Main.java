package visitor;

public class Main {
    public static void main(String[] args) {
        Animal monkey = new Monkey();
        Animal lion = new Lion();
        Animal dolphin = new Dolphin();

        Speak speak = new Speak();
        Jump jump = new Jump();

        monkey.accept(jump);
        lion.accept(jump);
        dolphin.accept(jump);
    }
}
