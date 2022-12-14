
package main.java.istic.aco.editor;

//import java.util.Optional;
//import istic.aco.editor.Memento.Memento;

/**
 * Engine Interface
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 *
 */
public interface Engine {
	/**
     * Provides access to the selection control object
     * @return the selection object
     */
    Selection getSelection();
    /**
     * Provides the whole contents of the buffer, as a string
     * @return a copy of the buffer's contents
     */
    String getBufferContents();
    /**
     * Provides the clipboard contents
     * @return a copy of the clipboard's contents
     */
    String getClipboardContents();
    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    void cutSelectedText();
    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    void copySelectedText();
    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    void pasteClipboard();
    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     * @param var1, the text to insert
     * @throws IllegalArgumentException if the string is empty
     */
    void insert(String var1) throws IllegalArgumentException;
    
    /**
     * Removes the contents of the selection in the buffer
     */
    void delete();

    /**
     * Provides the engine buffer's.
     * @return the engine buffer instance
     */
    StringBuilder getBuffer();
   /**
    * Set the engine buffer's.
    * @param buffer, the engine buffer's
    * @throws NullPointerException if the buffer is null
    */
    void setBuffer(StringBuilder buffer) throws NullPointerException;
   /**
    * Set the engine selection's.
    * @param selection the engine selection's
    * @throws IllegalArgumentException if the buffer selection's don't match
    * @throws NullPointerException if the selection is null
    */
    void setSelection(Selection selection) throws IllegalArgumentException,NullPointerException;
    
    /**
     * Create new Memento and save the command state
     * @return the Memento who have the parameters
     */
    //public Optional<Memento> saveState();
   
    /**
     * restore the old parameters
     * @param m the memento who contains parameters to restore
     * @throws IllegalArgumentException if the memento is null
     */
    //public void restore(Memento m) throws IllegalArgumentException;
}
