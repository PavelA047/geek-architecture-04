package state;

public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(new DefaultText());
        textEditor.type("bBb");

        textEditor.setState(new UpperCase());
        textEditor.type("bbb");

        textEditor.setState(new LowerCase());
        textEditor.type("bbb");
    }
}
