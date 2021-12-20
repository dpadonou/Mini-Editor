package main.java.istic.aco.editor.Memento;

/**
 * Concrete Memento EngineMemento, the engine state
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class EngineMemento implements Memento {
    private String clipboard;
    private String buffer;
    private int beginIndex = 0;
    private int endIndex = 0;

    /**
     * @param clipboard the clipboard of the engine
     * @param buffer the engine buffer
     * @param beginIndex the begin index of the engine selection's
     * @param endIndex the end index of the engine selection's
     */
    public EngineMemento(String clipboard, String buffer, int beginIndex, int endIndex) {
        super();
        if (test(clipboard, buffer, beginIndex, endIndex)) {
            this.clipboard = clipboard;
            this.buffer = buffer;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }
    }

    @Override
    public Object[] getParameter() {
        Object[] t = new Object[4];
        t[0] = clipboard;
        t[1] = buffer;
        t[2] = beginIndex;
        t[3] = endIndex;
        return t;
    }

/**
 * Test if the parameter is good
 * @param clipboard the clipboard of the engine
 * @param buffer the engine buffer
 * @param beginIndex the begin index of the engine selection's
 * @param endIndex the end index of the engine selection's
 * @return true if all parameters are good
 * @throws IllegalArgumentException if the parameters are not good
 */
    public boolean test(String clipboard, String buffer, int beginIndex, int endIndex) throws IllegalArgumentException {
        if (clipboard == null || buffer == null || beginIndex <= 0 || endIndex <= 0) {
            throw new IllegalArgumentException("Vous devez pass� des param�tres non nulles");
        } else {
            return true;
        }
    }
}
