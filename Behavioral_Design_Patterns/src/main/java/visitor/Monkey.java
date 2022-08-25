package visitor;

public class Monkey implements Animal{
    public void shout() {
        System.out.println("oh oo aa aa!");
    }

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitMonkey(this);
    }
}
