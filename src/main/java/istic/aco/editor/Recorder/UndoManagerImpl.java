package main.java.istic.aco.editor.Recorder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

//import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.EngineOriginator;
import main.java.istic.aco.editor.Memento.EngineMemento;
import main.java.istic.aco.editor.Memento.Memento;
/**
 * UndoManager interface implementation, UndoManagerImpl
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public class UndoManagerImpl implements UndoManager {
    private Deque<EngineMemento> stateEnginesPast = new ArrayDeque<EngineMemento>();
    private Deque<EngineMemento> stateEnginesFutur = new ArrayDeque<EngineMemento>();
    private EngineOriginator engine;
	
	/**
	 * @param engine
	 */
	public UndoManagerImpl(EngineOriginator engine) {
		super();
		if(test(engine)) {
			this.engine = engine;
		}
		
	}

	@Override
	public void save( Optional<Memento> m) {
		if(m == null) {
			throw new IllegalArgumentException("Vous devez passé des paramètres vide");
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
	    	   throw new NullPointerException("Vous devez passer des paramètres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }

}
