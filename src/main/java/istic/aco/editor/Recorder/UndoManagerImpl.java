package istic.aco.editor.Recorder;

import istic.aco.editor.EngineOriginator;
import istic.aco.editor.Memento.EngineMemento;
import istic.aco.editor.Memento.Memento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * UndoManager interface implementation, UndoManagerImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class UndoManagerImpl implements UndoManager {
	private final Deque<EngineMemento> stateEnginesPast = new ArrayDeque<EngineMemento>();
	private final Deque<EngineMemento> stateEnginesFutur = new ArrayDeque<EngineMemento>();
	private EngineOriginator engine;

	/**
	 * @param engine
	 */
	public UndoManagerImpl(EngineOriginator engine) {
		super();
		if (test(engine)) {
			this.engine = engine;
		}
	}

	@Override
	public void save( Optional<Memento> m) {
		if(m == null) {
			throw new IllegalArgumentException("Vous devez pass� des param�tres vide");
		}else {
			stateEnginesPast.addFirst((EngineMemento) m.get());
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
	
	/**
	 * Test the constructor parameter
	 * @param engine
	 * @return true if the parameter is good
	 * @throws NullPointerException if the parameter is null
	 */
	 public boolean test(EngineOriginator engine) throws NullPointerException {
	       if(engine==null ) {
	    	   throw new NullPointerException("Vous devez passer des param�tres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }

}
