package visitor;

public class Dolphin implements Animal{
    public void speak() {
        System.out.println("tu tu tu!");
    }

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitDolphin(this);
    }
}
