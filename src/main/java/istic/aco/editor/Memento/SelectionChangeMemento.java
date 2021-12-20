package istic.aco.editor.Memento;
/**
 * Concrete Memento selectionChangeMemento
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 *
 */
public class SelectionChangeMemento implements Memento {
    private int beginIndex;
    private int endIndex;
    
	/**
	 * @param beginIndex
	 * @param endIndex
	 */
	public SelectionChangeMemento(int beginIndex, int endIndex) {
		super();
		if(test(beginIndex,endIndex)) {
			this.beginIndex = beginIndex;
			this.endIndex = endIndex;
		}
	}

	@Override
	public Object[] getParameter() {
		Object[] t = new Object[2];
		t[0] = this.beginIndex;
		t[1] = this.endIndex;
		return t;
	}
	
	/**
	 * Test if the parameter is good
	 *
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 * @throws IllegalArgumentException if one of the parameter is negative or beginIndex is tall than endIndex
	 */
	 public boolean test(int beginIndex, int endIndex) throws IllegalArgumentException {
	        if ( beginIndex<0 || endIndex<0 || beginIndex>=endIndex) {
	        	throw new IllegalArgumentException("Vous devez pass� des param�tres valides");
	        } else return true;
	 }

}
