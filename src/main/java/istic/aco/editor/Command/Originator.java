package istic.aco.editor.Command;

import istic.aco.editor.Memento.Memento;

import java.util.Optional;

public interface Originator {
    /**
     * Create new Memento and save the command state
     *
     * @return the Memento who have the parameters
     */
    Optional<Memento> save();

    /**
     * restore the old parameters
     *
     * @param m the memento who contains parameters to restore
     * @throws IllegalArgumentException if the memento is null
     */
    void restore(Memento m) throws IllegalArgumentException;
}
