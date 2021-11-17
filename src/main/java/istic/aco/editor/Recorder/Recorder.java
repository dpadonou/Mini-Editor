package istic.aco.editor.Recorder;

import istic.aco.editor.Command.Command;

/**
 * Recorder interface
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public interface Recorder {
    /**
     * Save one command
     *
     * @param c the command who wants to save
     * @throws NullPointerException if the command is null
     */
    void save(Command c) throws NullPointerException;

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
