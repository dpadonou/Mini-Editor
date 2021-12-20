package main.java.istic.aco.editor.Recorder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

import main.java.istic.aco.editor.EngineOriginator;
import main.java.istic.aco.editor.Memento.EngineMemento;
import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.exceptions.CannotRedoException;
import main.java.istic.aco.editor.exceptions.CannotUndoException;

/**
 * UndoManager interface implementation, UndoManagerImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public class UndoManagerImpl implements UndoManager {
    private final Deque<EngineMemento> stateEnginesPast = new ArrayDeque<EngineMemento>();
    private final Deque<EngineMemento> stateEnginesFutur = new ArrayDeque<EngineMemento>();
    private EngineOriginator engine;

    /**
     * @param engine, a engine
     */
    public UndoManagerImpl(EngineOriginator engine) {
        super();
        if (test(engine)) {
            this.engine = engine;
        }
    }

    @Override
    public void save(Optional<Memento> m) {
        if (m == null) {
            throw new IllegalArgumentException("Vous devez pass� des param�tres vide");
        } else {
            stateEnginesPast.addFirst((EngineMemento) m.get());
        }
    }

    @Override
    public void undo() {
        if (stateEnginesPast.size() > 0) {
            engine.restore(stateEnginesPast.getFirst());
            stateEnginesFutur.addFirst(stateEnginesPast.getFirst());
            stateEnginesPast.removeFirst();
        } else {
            throw new CannotUndoException("Aucune commande à restorer.");
        }
    }

    @Override
    public void redo() {
        if (stateEnginesFutur.size() > 0) {
            engine.restore(stateEnginesFutur.getFirst());
            stateEnginesPast.addFirst(stateEnginesFutur.getFirst());
            stateEnginesFutur.removeFirst();
        } else {
            throw new CannotRedoException("Aucune commande à reexécuter.");
        }
    }

    /**
     * Test the constructor parameter
     *
     * @param engine, a engine
     * @return true if the parameter is good
     * @throws NullPointerException if the parameter is null
     */
    public boolean test(EngineOriginator engine) throws NullPointerException {
        if (engine == null) {
            throw new NullPointerException("Vous devez passer des param�tres non nul");
        } else {
            return true;
        }
    }

}
