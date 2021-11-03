package main.java.istic.aco.editor.Command;

import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.Recorder.Record;
/**
 * Concrete Command, cutCommand
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public class cutCommand implements Command {
	private Engine engine;
	private Record recorder;
    	
	/**
	 * @param engine The Receiver where are the functions
	 * @param recorder The recorder for record the command
	 */
	public cutCommand(Engine engine,Record recorder) {
       if(test(engine,recorder)) {
    	   this.engine = engine;
   		   this.recorder = recorder;
       }
		
	}

    /**
     * call the cutText method of the engine
     * save this command in the recorder
     */
	@Override
	public void execute() {
		this.engine.cutSelectedText();
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
	 * @throws IllegalArgumentException if the method parameters are null
	 */
	 public boolean test(Engine engine,Record recorder) throws IllegalArgumentException {
	       if(engine.equals(null) || recorder.equals(null)) {
	    	   throw new IllegalArgumentException("Vous devez passer des paramètres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }

}
