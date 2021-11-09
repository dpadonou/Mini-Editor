package main.java.istic.aco.editor.Recorder;

/**
 * Recorder interface implementation, RecorderImpl
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
import java.util.ArrayList;

import main.java.istic.aco.editor.Command.Command;
import main.java.istic.aco.editor.Memento.Memento;

public class RecorderImpl implements Recorder {
    private ArrayList<Command> com = new ArrayList<Command>();
    private Memento memento;
    
    /**
     * Save one command in the receiver
     * @param c the command who wants to save in the recorder
     */
	@Override
	public void save(Command c) {
		if(c==null) {
			throw new NullPointerException("La commande passée doit etre non nul");
		}else {
			com.add(c);
			this.memento=c.save();
		}
		
	}
    
	/**
	 *Start the recorder
	 */
	@Override
	public void start() {
		
	}
    
	/**
	 * Stop the recorder
	 */
	@Override
	public void stop() {
	}
    /**
     * First Restore the state of the command 
     * Replay the last command save in the recorder
     */
	@Override
	public void replay() {
		this.com.get(this.com.size()-1).restore(this.memento);
		this.com.get(this.com.size()-1).execute();
		
	}
     
}
