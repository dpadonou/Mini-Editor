package main.java.istic.aco.editor.Recorder;

import main.java.istic.aco.editor.Command.Command;
/**
 * Record interface 
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public interface Record {
	/**
	 * Save one command
	 * @param c the command who wants to save
	 * @throws IllegalArgumentException if the command is null
	 */
    public void save(Command c) throws IllegalArgumentException;
    /**
     * Start the recorder
     */
    public void start();
    /**
     * Turn off the recorder
     */
    public void stop();
     /**
      * Replay the last command who had been save in the recorder
      */
    public void replay();
    
}
