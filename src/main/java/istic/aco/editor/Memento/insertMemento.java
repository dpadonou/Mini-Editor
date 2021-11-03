package main.java.istic.aco.editor.Memento;

/**
 * Concrete Memento insertMemento
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 *
 */
public class insertMemento implements Memento {
     private String s;
     

	/**
	 * @param s the command parameter
	 */
	public insertMemento(String s){
		if(test(s)) {
			this.s = s;
		}
		
	}

	/**
	 * @return the parameter
	 */
	@Override
	public Object[] getParameter() {
		Object[] t = new Object[1];
		t[0] = this.s;
		return t ;
	}
	
	/**
	 * Test if the parameter is good
	 * @param s
	 * @return
	 * @throws IllegalArgumentException if the parameter is empty
	 */
	 public boolean test(String s) throws IllegalArgumentException {
	        if (s.isEmpty()) {
	        	throw new IllegalArgumentException("Vous devez pass� une chaine de caract�re non vide");
	        } else return true;
	    
	 }
     
}
