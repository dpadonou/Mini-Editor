package main.java.istic.aco.editor.Command;

import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.Recorder.Record;
/**
 * Concrete Command, Replay
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public class Replay implements Command{
    private Record recorder;
    
	/**
	 * @param recorder The recorder who contains the command for replaying
	 */
	public Replay(Record recorder) {
		super();
		if(test(recorder)) {
			this.recorder = recorder;
		}
		
	}
     /**
      * Call the recorder replay method
      */
	@Override
	public void execute() {
		this.recorder.replay();
		
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
	 * @param recorder
	 * @return
	 * @throws IllegalArgumentException if the method parameters are null
	 */
	 public boolean test(Record recorder) throws IllegalArgumentException {
	       if(recorder.equals(null)) {
	    	   throw new IllegalArgumentException("Vous devez passer des paramètres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }

}
