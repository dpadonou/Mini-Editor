package main.java.istic.aco.editor.Command;

import java.util.Optional;

import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.Recorder.UndoManager;

/**
 * Concrete Command, UndoCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class UndoCommand implements CommandOriginator {
    private UndoManager undoManager;

    /**
     * @param undoManager, the manager for manage the engine state
     */
    public UndoCommand(UndoManager undoManager) {
        super();
        if (test(undoManager)) {
            this.undoManager = undoManager;
        }
    }

    @Override
    public void execute() {
        undoManager.undo();
    }

    @Override
    public Optional<Memento> save() {
        return Optional.empty();
    }

    @Override
    public void restore(Memento m) throws IllegalArgumentException {
    }

    /**
     * Test the constructor parameter
     * @param undoManager, the manager for manage the engine state
     * @return true if the parameter is good
     * @throws NullPointerException if the parameter is null
     */
    public boolean test(UndoManager undoManager) throws NullPointerException {
        if (undoManager == null) {
            throw new NullPointerException("Vous devez passer des param�tres non nul");
        } else {
            return true;
        }
    }
}
