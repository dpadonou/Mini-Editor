package main.java.istic.aco.editor.Memento;
/**
 * Memento Interface
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public interface Memento {
	/**
	 * Provides the parameters who saved
	 * @return an object table who contains all parameters
	 */
    public Object[] getParameter();
}
