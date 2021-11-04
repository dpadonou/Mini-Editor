package main.java.istic.aco.editor;
/**
 * Selection Interface
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public interface Selection {

	/**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return the begin Index
     */
    int getBeginIndex();
    
    /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the end index
     */
    int getEndIndex();
    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    int getBufferBeginIndex();
    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */
    int getBufferEndIndex();
    
    /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     * @throws IllegalArgumentException if the beginIndex is negative
     */
    void setBeginIndex(int beginIndex) throws IndexOutOfBoundsException,IllegalArgumentException;
   
    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the endIndex is out of bounds
     * @throws IllegalArgumentException if the endIndex is negative
     */
    void setEndIndex(int endIndex) throws IndexOutOfBoundsException,IllegalArgumentException;

    /**
     * Provides the buffer who contains this selection
     * @return the buffer instance
     */
    StringBuilder getBuffer();
}
