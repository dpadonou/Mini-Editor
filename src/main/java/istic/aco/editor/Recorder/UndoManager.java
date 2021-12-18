package istic.aco.editor.Recorder;

import istic.aco.editor.Memento.Memento;

import java.util.Optional;

/**
 * UndoManager, Recorder for engine
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public interface UndoManager {
    /**
     * Save engine state
     * @param m The memento who contains engine state
     * @throws IllegalArgumentException if the memento is null
     */
    void save(Optional<Memento> m) throws IllegalArgumentException;

    /**
     * Cancel the last engine state
     */
    void undo();

    /**
     * Reset the last engine state
     */
    void redo();
}
