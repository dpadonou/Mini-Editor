package main.java.istic.aco.editor.Memento;
/**
 * Memento Interface
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 *
 */
public interface Memento {
	/**
	 * Provides the parameters who saved
	 *
	 * @return an object table who contains all parameters
	 */
	Object[] getParameter();
}
