package main.java.istic.aco.editor.Memento;
/**
 * Concrete Memento selectionChangeMemento
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public class selectionChangeMemento implements Memento {
    private int beginIndex;
    private int endIndex;
    
	/**
	 * @param beginIndex
	 * @param endIndex
	 */
	public selectionChangeMemento(int beginIndex, int endIndex) {
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
	 * @param s
	 * @return
	 * @throws IllegalArgumentException if one of the parameter is negative
	 */
	 public boolean test(int beginIndex, int endIndex) throws IllegalArgumentException {
	        if ( beginIndex<0 || endIndex<0) {
	        	throw new IllegalArgumentException("Vous devez passé une chaine de caractère non vide");
	        } else return true;
	    
	 }

}
