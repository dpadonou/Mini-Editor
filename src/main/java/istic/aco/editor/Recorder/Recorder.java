package main.java.istic.aco.editor.Recorder;

import main.java.istic.aco.editor.Command.CommandOriginator;
/**
 * Recorder interface 
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 *
 */
public interface Recorder {
    /**
     * Save one command
     *
     * @param c the command who wants to save
     * @throws NullPointerException if the command is null
     */
    void save(CommandOriginator c) throws NullPointerException;

    /**
     * Start the recorder
     */
    void start();

    /**
     * Turn off the recorder
     */
    void stop();

    /**
     * Replay the last command who had been save in the recorder
     */
    void replay();
    
}
