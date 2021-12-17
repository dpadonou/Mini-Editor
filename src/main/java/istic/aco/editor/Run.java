package main.java.istic.aco.editor;

public class Run {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("Un buffer de string dans les tests.");
        /*
        Selection selection = new SelectionImpl(3, 19, stringBuilder);
        Engine engine2 = new EngineImpl(stringBuilder, selection);

        String newBuffer = "Un dans les tests.";
        engine2.cutSelectedText();
        System.out.println(engine2.getBufferContents());*/

        System.out.println(stringBuilder);
        StringBuilder cuted = stringBuilder.delete(3, 19);
        System.out.println(cuted);
        System.out.println(stringBuilder);
    }
}
