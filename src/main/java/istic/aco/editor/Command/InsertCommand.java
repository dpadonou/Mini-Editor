package main.java.istic.aco.editor.Command;

import java.util.Optional;

import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.Invoker.Invoker;
import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.Memento.InsertMemento;
import main.java.istic.aco.editor.Recorder.Recorder;
/**
 * Concrete Command, insertCommand
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public class InsertCommand implements CommandOriginator {
	private Engine engine;
    private Invoker inv;
    private Recorder recorder;
    private String s;
     /**
	 * @param engine The Receiver where are the functions
	 * @param inv The invoker who call this concrete command
	 * @param recorder The recorder for record the command
	 * @param memento The memento for store this command parameters
	 */
	public InsertCommand(Engine engine, Invoker inv,Recorder recorder) {
		if(test(engine,inv,recorder)) {
			this.engine = engine;
			this.inv = inv;
			this.recorder= recorder;
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
	public Optional<Memento> save() {
		return Optional.of(new InsertMemento(this.s));
		
	}

	@Override
	public void restore(Memento m) { 
		if(m == null) {
			throw new IllegalArgumentException("vous devez passer un objet memento non nul");
		}else {
			Object[] t = m.getParameter();
			this.s = t[0].toString();
		}
       
		
	}
	/**
	 * Lift an error if the parameters are null and send true if not.
	 * @param engine
	 * @param recorder
	 * @param inv
	 * @return
	 * @throws NullPointerException if the method parameters are null
	 */
	 public boolean test(Engine engine,Invoker invoker,Recorder recorder) throws NullPointerException {
	       if(engine == null || recorder==null || invoker==null) {
	    	   throw new NullPointerException("Vous devez passer des paramètres non nul");
	       }else {
	    	   return true;
	       }
	    
	 }


}
