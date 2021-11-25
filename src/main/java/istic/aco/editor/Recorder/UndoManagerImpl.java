package main.java.istic.aco.editor.Recorder;

import java.util.ArrayDeque;
import java.util.Deque;

//import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.EngineOriginator;
import main.java.istic.aco.editor.Memento.EngineMemento;
import main.java.istic.aco.editor.Memento.Memento;

public class UndoManagerImpl implements UndoManager {
    private Deque<EngineMemento> stateEnginesPast = new ArrayDeque<EngineMemento>();
    private Deque<EngineMemento> stateEnginesFutur = new ArrayDeque<EngineMemento>();
    private EngineOriginator engine;
    
	
	/**
	 * @param engine
	 */
	public UndoManagerImpl(EngineOriginator engine) {
		super();
		this.engine = engine;
	}

	@Override
	public void save(Memento m) {
		if(m == null) {
			throw new IllegalArgumentException("Vous devez passé des paramètres vide");
		}else {
			stateEnginesPast.addFirst((EngineMemento) m);
		}
		
	}

	@Override
	public void undo() {
		engine.restore(stateEnginesPast.getFirst());
		stateEnginesFutur.addFirst(stateEnginesPast.getFirst());
		stateEnginesPast.removeFirst();
	}

	@Override
	public void redo() {
		engine.restore(stateEnginesFutur.getFirst());
		stateEnginesPast.addFirst(stateEnginesFutur.getFirst());
		stateEnginesFutur.removeFirst();
	}

}
