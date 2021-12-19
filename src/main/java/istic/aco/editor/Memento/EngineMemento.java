package istic.aco.editor.Memento;

import istic.aco.editor.Selection;

/**
 * Concrete Memento EngineMemento
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class EngineMemento implements Memento {
    private String clipboard;
    private StringBuilder buffer;
    private Selection selection;

    /**
     * @param clipboard the engine clipboard
     * @param buffer    the engine buffer
     * @param selection the engine selection
     */
    public EngineMemento(String clipboard, StringBuilder buffer, Selection selection) {
        super();
        if (test(clipboard, buffer, selection)) {
            this.clipboard = clipboard;
            this.buffer = buffer;
            this.selection = selection;
        }
    }

    @Override
    public Object[] getParameter() {
        Object[] t = new Object[3];
        t[0] = clipboard;
        t[1] = buffer;
        t[2] = selection;
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
    public boolean test(String clipboard, StringBuilder buffer, Selection selection) throws IllegalArgumentException {
        if (clipboard == null || buffer == null || selection == null) {
            throw new IllegalArgumentException("Vous devez pass� des param�tres non nulles");
        } else {
            return true;
        }
    }
}
