package main.java.istic.aco.editor.Command;

import main.java.istic.aco.editor.Memento.Memento;
/**
 * Command Interface
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public interface Command {
	/**
	 * Execute the user action
	 */
    public void execute();
    /**
     * Create new Memento and save the command state
     * @return the Memento who have the parameters
     */
    public Memento save();
    /**
     * restore the old parameters
     * @param m the memento who contains parameters to restore
     * @throws NullPointerException if the memento is null
     */
    public void restore(Memento m) throws NullPointerException;
}
