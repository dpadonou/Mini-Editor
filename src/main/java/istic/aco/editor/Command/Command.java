package main.java.istic.aco.editor.Command;

//import java.util.Optional;

//import main.java.istic.aco.editor.Memento.Memento;

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
    //public Optional<Memento> save();
    /**
     * restore the old parameters
     * @param m the memento who contains parameters to restore
     * @throws IllegalArgumentException if the memento is null
     */
   // public void restore(Memento m) throws IllegalArgumentException;

}
