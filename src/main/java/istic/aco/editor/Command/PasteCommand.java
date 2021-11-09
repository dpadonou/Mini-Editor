package main.java.istic.aco.editor.Command;

import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.Recorder.Recorder;
/**
 * Concrete Command, pasteCommand
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
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

	@Override
	public Memento save() {
		return null;
		
	}

	@Override
	public void restore(Memento m) {
		
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

}
