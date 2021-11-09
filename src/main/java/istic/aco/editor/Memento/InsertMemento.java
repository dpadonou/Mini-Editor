package istic.aco.editor.Memento;

/**
 * Concrete Memento insertMemento
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public class InsertMemento implements Memento {
    private String s;


    /**
     * @param s the command parameter
     */
    public InsertMemento(String s) {
        if (test(s)) {
            this.s = s;
        }
    }

    /**
     * @return the parameter
     */
    @Override
    public Object[] getParameter() {
        Object[] t = new Object[1];
        t[0] = this.s;
        return t;
    }

    /**
     * Test if the parameter is good
     *
     * @param s
     * @return
     * @throws IllegalArgumentException if the parameter is empty
     */
    public boolean test(String s) throws IllegalArgumentException {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Vous devez passer une chaine de caractère non vide.");
        } else return true;
    }

}
