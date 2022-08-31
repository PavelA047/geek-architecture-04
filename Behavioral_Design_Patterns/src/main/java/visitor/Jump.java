package visitor;

public class Jump implements AnimalOperation{

    @Override
    public void visitMonkey(Monkey monkey) {
        System.out.println("20 m");
    }

    @Override
    public void visitLion(Lion lion) {
        System.out.println("7 m");
    }

    @Override
    public void visitDolphin(Dolphin dolphin) {
        System.out.println("disappeared");
    }
}
