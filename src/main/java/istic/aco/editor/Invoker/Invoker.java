package main.java.istic.aco.editor.Invoker;

import main.java.istic.aco.editor.Command.Command;

/**
 * Invoker interface
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public interface Invoker  {
	/**
	 * Get for the text to insert
	 * @return  the text to insert
	 */
	public String getS();
	/**
	 * Set the text to insert
	 * @param s the text to insert
	 * @throws NullPointerException if the parameter is null
	 */
	public void setS(String s) throws NullPointerException;

	 /**
	  * Put the commands in the map
	  * @param command
	  * @throws IllegalArgumentException if the command is null or s is empty
	  */
	public void setCommand(String s,Command command) throws IllegalArgumentException;
	/**
	 * Get the beginIndex for the new Selection
	 * @return beginIndex for the new Selection
	 */
	
	public int getBeginIndex();
	/**
	 * Set the beginIndex for the new Selection
	 * @param beginIndex for the new Selection
	 * @throws IllegalArgumentException if the parameter is negative
	 */
	public void setBeginIndex(int beginIndex) throws IllegalArgumentException;
	/**
	 * Get the endIndex for the new Selection
	 * @return endIndex for the new Selection
	 */
	public int getEndIndex();
	/**
	 * Set the endIndex for the new Selection
	 * @param endIndex for the new Selection
	 * @throws IllegalArgumentException if the parameter is negative or endIndex<=beginIndex
	 */
	public void setEndIndex(int endIndex) throws IllegalArgumentException;
	/**
	 * User action for change the selection
	 */
    public void selectionChange();
    /**
     * User action for cut the selection content's
     */
    public void cutText();
    /**
     * User action for copy the selection content's
     */
    public void copyText();
    /**
     * User action for paste the clipboard content's instead of the selection content's
     */
    public void pasteClipboard();
    /**
     * User action for insert text into the selection
     */
    public void insert();
    /**
     * user action for replay last command
     */
    public void replay();
    /**
     * user action for make undo
     */
    public void undo();
    /**
     * user action for make redo
     */  
    public void redo();
    
}
