package istic.aco.editor.Command;

import istic.aco.editor.EngineOriginator;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Recorder.UndoManager;

import java.util.Optional;

/**
 * Concrete Command, copyCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class CopyCommand implements CommandOriginator {
	private EngineOriginator engine;
	private Recorder recorder;
	private UndoManager undoManager;
	/**
	 * @param engine The Receiver where are the functions
	 * @param recorder The recorder for record the command
	 */
	public CopyCommand(EngineOriginator engine,Recorder recorder,UndoManager undoManager) {
		super();
		if(test(engine,recorder,undoManager)) {
			this.engine = engine;
			this.recorder = recorder;
			this.undoManager = undoManager;
		}
		
	}
	/**
	 * Call the copyText method of the engine
	 * save this command in the recorder
	 */
	@Override
	public void execute() {
		engine.copySelectedText();
		undoManager.save(engine.save());
		recorder.save(this);
		
	}
	/**
	 * Lift an error if the parameters are null and send true if not.
	 * @param engine
	 * @param recorder
	 * @return
	 * @throws NullPointerException if the method parameters are null
	 */
	 public boolean test(EngineOriginator engine,Recorder recorder,UndoManager undoManager) throws NullPointerException {
	       if(engine==null || recorder==null ||undoManager ==null ) {
	    	   throw new NullPointerException("Vous devez passer des param�tres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }
	@Override
	public Optional<Memento> save() {
		return Optional.empty();
	}
	@Override
	public void restore(Memento m) throws IllegalArgumentException {
		
	}

}
