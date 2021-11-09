package istic.aco.editor.Command;

import istic.aco.editor.Invoker.Invoker;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Memento.SelectionChangeMemento;
import istic.aco.editor.Recorder.Record;
import istic.aco.editor.Selection;

/**
 * Concrete Command, selectionChangeCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public class SelectionChangeCommand implements Command {

	private Selection selection;
	private Invoker inv;
	private Record recorder;
	private int beginIndex;
	private int endIndex;

	/**
	 * @param selection The receiver where are the functions
	 * @param inv       The invoker who call this concrete command
	 * @param recorder  for record this command
	 */
	public SelectionChangeCommand(Selection selection, Invoker inv, Record recorder) {
		if (test(selection, inv, recorder)) {
			this.selection = selection;
			this.inv = inv;
			this.recorder = recorder;
		}

	}

	/**
	 * Get the end and the begin index through the invoker
	 * set at the selection new begin and end index
	 * Save this command in the recorder
	 */
	@Override
	public void execute() {
		this.beginIndex = inv.getBeginIndex();
		this.endIndex = inv.getEndIndex();
		selection.setBeginIndex(this.beginIndex);
		selection.setEndIndex(this.endIndex);
		recorder.save(this);
	}
	@Override
	public Memento save() {
		return new SelectionChangeMemento(this.beginIndex, this.endIndex);
		
	}

	/**
	 * Set in this object properties the memento values
	 */
	@Override
	public void restore(Memento m) {
		 if(m.equals(null)) {
			 throw new IllegalArgumentException("Vous devez mpasser en paramètre un memento non null");
		 }else {
			 Object[] t = m.getParameter();
			 this.beginIndex = Integer.parseInt(t[0].toString());
			 this.endIndex = Integer.parseInt(t[1].toString());
		 }
	}

	/**
	 * Lift an error if the parameters are null and send true if not.
	 *
	 * @param recorder
	 * @param invoker
	 * @param selection
	 * @return boolean
	 * @throws IllegalArgumentException if the method parameters are null
	 */
	public boolean test(Selection selection, Invoker invoker, Record recorder) throws IllegalArgumentException {
		if (selection.equals(null) || recorder.equals(null) || invoker.equals(null)) {
			throw new IllegalArgumentException("Vous devez passer des paramètres non nul");
		} else {
			return true;
		}

	}

}
