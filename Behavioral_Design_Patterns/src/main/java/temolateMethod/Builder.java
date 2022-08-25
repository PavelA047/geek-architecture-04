package temolateMethod;

public abstract class Builder {
    public final void build() {
        test();
        lint();
        assemble();
        deploy();
    }

    abstract public void test();
    abstract public void lint();
    abstract public void assemble();
    abstract public void deploy();
}
