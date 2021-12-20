package main.java.istic.aco.editor.Memento;

/**
 * Concrete Memento insertMemento
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
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
     * @param s the string to insert
     * @return true if the parameter is good
     * @throws NullPointerException if the parameter is null
     */
    public boolean test(String s) throws NullPointerException {
        if (s == null) {
            throw new NullPointerException("Vous devez pass� une chaine de caract�re ");
        } else return true;
    }

}
