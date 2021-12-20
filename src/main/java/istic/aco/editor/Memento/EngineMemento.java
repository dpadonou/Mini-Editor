package istic.aco.editor.Memento;

/**
 * Concrete Memento EngineMemento
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class EngineMemento implements Memento {
    private String clipboard;
    private String buffer;
    private int beginIndex = 0;
    private int endIndex = 0;

    /**
     * @param clipboard
     * @param buffer
     * @param beginIndex
     * @param endIndex
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
     *
     * @param clipboard the engine clipboard
     * @param buffer    the engine buffer
     * @param selection the engine selection
     * @return true if all paramets is good and error if not
     * @throws IllegalArgumentException if at least one parameter is bad
     */
    public boolean test(String clipboard, String buffer, int beginIndex, int endIndex) throws IllegalArgumentException {
        if (clipboard == null || buffer == null || beginIndex <= 0 || endIndex <= 0) {
            throw new IllegalArgumentException("Vous devez pass� des param�tres non nulles");
        } else {
            return true;
        }
    }
}
