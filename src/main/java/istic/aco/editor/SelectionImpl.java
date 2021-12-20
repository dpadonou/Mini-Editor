package main.java.istic.aco.editor;

/**
 * Selection Interface implementation, SelectionImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné  Padonou
 */
public class SelectionImpl implements Selection {
    static int BUFFER_BEGIN_INDEX = 0;
    private int beginIndex;
    private int endIndex;
    private StringBuilder buffer;
  /**
   * Constructor the selection
   * @param beginIndex the begin index of the selection
   * @param endIndex the end index of the selection
   * @param buffer the engine buffer
   */
    public SelectionImpl(int beginIndex, int endIndex, StringBuilder buffer) {
        if (test(beginIndex, endIndex, buffer)) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.buffer = buffer;
        }
    }
    /**
     * Constructor to instanciate the selection whitout parameter
     */

    public SelectionImpl() {

    }

    @Override
    public StringBuilder getBuffer() {
        return buffer;
    }

    @Override
    public int getBeginIndex() {
        return this.beginIndex;
    }

    @Override
    public void setBeginIndex(int beginIndex) {
        if (beginIndex <= this.buffer.length()) {
            if (beginIndex < 0) {
                throw new IllegalArgumentException("L'index de début doit être supérieure ou égale à 0.");
            } else if (beginIndex > this.endIndex) {
                throw new IllegalArgumentException("L'index de début doit être inférieure ou égale à l'index de fin.");
            } else this.beginIndex = beginIndex;
        } else {
            throw new IndexOutOfBoundsException("L'index de début est hors du buffer.");
        }
    }

    @Override
    public int getEndIndex() {
        return this.endIndex;
    }

    @Override
    public void setEndIndex(int endIndex) {
        if (endIndex <= this.buffer.length()) {
            if (endIndex < 0) {
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à 0.");
            } else if (endIndex < this.beginIndex) {
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à l'index de début.");
            } else this.endIndex = endIndex;
        } else {
            throw new IndexOutOfBoundsException("L'index de fin est hors du buffer.");
        }
    }

    @Override
    public int getBufferBeginIndex() {
        return BUFFER_BEGIN_INDEX;
    }

    @Override
    public int getBufferEndIndex() {
        return this.buffer.length() - 1;
    }
  /**
   * Lift an error if the parameters are null and send true if not.
   * @param begin the beginIndex
   * @param end the endIndex
   * @param bf the buffer
   * @return true if all parameters are good
   * @throws IllegalArgumentException if the parameters are not good
   * @throws IndexOutOfBoundsException if the parameter 'end' if tall than the buffer length
   */
    public boolean test(int begin, int end, StringBuilder bf) throws IllegalArgumentException,IndexOutOfBoundsException{
        if (end <= bf.length()) {
            if (end < 0) {
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à 0.");
            } else if (end < begin) {
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à l'index de début.");
            } else if (begin < 0) {
                throw new IllegalArgumentException("L'index de début doit être supérieure ou égale à 0.");
            } else {
                return true;
            }
        } else {
            throw new IndexOutOfBoundsException("L'index de fin est hors du buffer.");
        }
    }
}
