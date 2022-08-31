package Memento;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();

        editor.type("first line");
        editor.type("second line");

        EditorMemento editorMemento = editor.save();

        editor.type("third line");

        System.out.println(editor.getContent());

        editor.restore(editorMemento);

        System.out.println(editor.getContent());
    }
}
