package istic.aco.editor.Invoker;

import istic.aco.editor.Command.Command;

/**
 * Invoker interface
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 *
 */
public interface Invoker  {
	/**
	 * Get for the text to insert
	 *
	 * @return the text to insert
	 */
	String getS();

	/**
	 * Set the text to insert
	 *
	 * @param s the text to insert
	 * @throws NullPointerException if the parameter is null
	 */
	void setS(String s) throws NullPointerException;

	/**
	 * Put the commands in the map
	 *
	 * @param command
	 * @throws IllegalArgumentException if the command is null or s is empty
	 */
	void setCommand(String s, Command command) throws IllegalArgumentException;

	/**
	 * Get the beginIndex for the new Selection
	 *
	 * @return beginIndex for the new Selection
	 */

	int getBeginIndex();

	/**
	 * Set the beginIndex for the new Selection
	 *
	 * @param beginIndex for the new Selection
	 * @throws IllegalArgumentException if the parameter is negative
	 */
	void setBeginIndex(int beginIndex) throws IllegalArgumentException;

	/**
	 * Get the endIndex for the new Selection
	 *
	 * @return endIndex for the new Selection
	 */
	int getEndIndex();

	/**
	 * Set the endIndex for the new Selection
	 *
	 * @param endIndex for the new Selection
	 * @throws IllegalArgumentException if the parameter is negative or endIndex<=beginIndex
	 */
	void setEndIndex(int endIndex) throws IllegalArgumentException;

	/**
	 * User action for change the selection
	 */
	void selectionChange();

	/**
	 * User action for cut the selection content's
	 */
	void cutText();

	/**
	 * User action for copy the selection content's
	 */
	void copyText();

	/**
	 * User action for paste the clipboard content's instead of the selection content's
	 */
	void pasteClipboard();

	/**
	 * User action for insert text into the selection
	 */
	void insert();

	/**
	 * user action for replay last command
	 */
	void replay();

	/**
	 * user action for make undo
	 */
	void undo();

	/**
	 * user action for make redo
     */
    void redo();
    
}
