package visitor;

public class Lion implements Animal{
    public void roar() {
        System.out.println("roar!");
    }

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitLion(this);
    }
}
