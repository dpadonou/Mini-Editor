package main.java.istic.aco.editor.Recorder;

import java.util.Optional;

import main.java.istic.aco.editor.Memento.Memento;
/**
 * UndoManager, Recorder for engine
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public interface UndoManager {
	/**
	 * Save engine state
	 * @param m The memento who contains engine state
	 * @throws IllegalArgumentException if the memento is null
	 */
   public void save( Optional<Memento> m) throws IllegalArgumentException;
   /**
    * Cancel the last engine state
    */
   public void undo();
   /**
    * Reset the last engine state
    */
   public void redo();
}
