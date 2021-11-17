package istic.aco.editor.Command;

import istic.aco.editor.Engine;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Recorder.Recorder;

import java.util.Optional;

/**
 * Concrete Command, pasteCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public class PasteCommand implements Command {
	
	private Engine engine;
	private Recorder recorder;
    
	/**
	 * @param engine The Receiver where are the functions
	 * @param recorder The recorder for record the command
	 */
	public PasteCommand(Engine engine,Recorder recorder) {
		if(test(engine,recorder)) {
			this.engine = engine;
			this.recorder = recorder;
		}
		
	}

    /**
     * call the past method of the engine
     * save this command in the recorder
     */
	@Override
	public void execute() {
		engine.pasteClipboard();
		recorder.save(this);
	}
	
	/**
	 * Lift an error if the parameters are null and send true if not.
	 * @param engine
	 * @param recorder
	 * @return
	 * @throws NullPointerException if the method parameters are null
	 */
	 public boolean test(Engine engine,Recorder recorder) throws NullPointerException {
	       if(engine==null || recorder==null) {
	    	   throw new NullPointerException("Vous devez passer des paramètres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }

	@Override
	public Optional<Memento> save() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void restore(Memento m) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
