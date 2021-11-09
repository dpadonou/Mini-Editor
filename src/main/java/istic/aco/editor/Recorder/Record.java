package istic.aco.editor.Recorder;

import istic.aco.editor.Command.Command;

/**
 * Record interface
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public interface Record {
    /**
     * Save one command
     *
     * @param c the command who wants to save
     * @throws IllegalArgumentException if the command is null
     */
    void save(Command c) throws IllegalArgumentException;

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
