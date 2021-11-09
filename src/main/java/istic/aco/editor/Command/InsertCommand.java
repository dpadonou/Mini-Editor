package istic.aco.editor.Command;

import istic.aco.editor.Engine;
import istic.aco.editor.Invoker.Invoker;
import istic.aco.editor.Memento.InsertMemento;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Recorder.Record;

/**
 * Concrete Command, insertCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public class InsertCommand implements Command {
	private Engine engine;
	private Invoker inv;
	private Record recorder;
	private String s;

	/**
	 * @param engine   The Receiver where are the functions
	 * @param inv      The invoker who call this concrete command
	 * @param recorder The recorder for record the command
	 */
	public InsertCommand(Engine engine, Invoker inv, Record recorder) {
		if (test(engine, inv, recorder)) {
			this.engine = engine;
			this.inv = inv;
			this.recorder = recorder;
		}
	}
      
	/**
	 * call the insert method of the engine
	 * take the text to insert in the invoker who call this command
	 * save the recorder in the recorder
	 */
	@Override
	public void execute() {
		this.s= inv.getS();
		engine.insert(this.s);   
		recorder.save(this);
	}

	@Override
	public Memento save() {
		return new InsertMemento(this.s);
	}

	@Override
	public void restore(Memento m) { 
		if(m.equals(null)) {
			throw new IllegalArgumentException("vous devez passer un objet memento non nul");
		}else {
			Object[] t = m.getParameter();
			this.s = t[0].toString();
		}


	}

	/**
	 * Lift an error if the parameters are null and send true if not.
	 *
	 * @param engine
	 * @param recorder
	 * @param invoker
	 * @return
	 * @throws IllegalArgumentException if the method parameters are null
	 */
	 public boolean test(Engine engine,Invoker invoker,Record recorder) throws IllegalArgumentException {
	       if(engine.equals(null) || recorder.equals(null) || invoker.equals(null)) {
	    	   throw new IllegalArgumentException("Vous devez passer des param�tres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }


}
