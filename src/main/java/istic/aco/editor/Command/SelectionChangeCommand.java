package istic.aco.editor.Command;

import istic.aco.editor.Invoker.Invoker;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Memento.SelectionChangeMemento;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Selection;

import java.util.Optional;

/**
 * Concrete Command, selectionChangeCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class SelectionChangeCommand implements Command {

	private Selection selection;
	private Invoker inv;
	private Recorder recorder;
	private int beginIndex;
	private int endIndex;

	/**
	 * @param selection The receiver where are the functions
	 * @param inv The invoker who call this concrete command
	 * @param recorder for record this command
	 */
	public SelectionChangeCommand(Selection selection, Invoker inv,Recorder recorder) {
		if(test(selection,inv,recorder)) {
			this.selection = selection;
			this.inv = inv;
			this.recorder= recorder;
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
	public Optional<Memento> save() {
		return Optional.of(new SelectionChangeMemento(this.beginIndex,this.endIndex));
		
	}

	/**
	 * Set in this object properties the memento values
	 */
	@Override
	public void restore(Memento m) {
		 if(m==null) {
			 throw new IllegalArgumentException("Vous devez mpasser en param�tre un memento non null");
		 }else {
			 Object[] t = m.getParameter();
			 this.beginIndex = Integer.parseInt(t[0].toString());
			 this.endIndex = Integer.parseInt(t[1].toString());
		 }
	}

	/**
	 * Lift an error if the parameters are null and send true if not.
	 *
	 * @param selection
	 * @param recorder
	 * @param invoker
	 * @return
	 * @throws NullPointerException if the method parameters are null
	 */
	public boolean test(Selection selection, Invoker invoker, Recorder recorder) throws NullPointerException {
		if (selection == null || recorder == null || invoker == null) {
			throw new IllegalArgumentException("Vous devez passer des param�tres non nuls.");
		} else {
			return true;
		}
	}

}
