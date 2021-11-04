
package main.java.istic.aco.editor;

/**
 * Selection Interface implementation, SelectionImpl
 * @author Arnauld Djedjemel
 * @author Dieu-Donn Padonou
 *
 */

public class SelectionImpl implements Selection {
    private int beginIndex;
    private int endIndex;
    private StringBuilder buffer;
    static int BUFFER_BEGIN_INDEX = 0;

    public SelectionImpl(int beginIndex, int endIndex, StringBuilder buffer) {
        if(test(beginIndex, endIndex, buffer)){
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.buffer = buffer;
        }
    }

    public SelectionImpl() {

    }

    public StringBuilder getBuffer() {
        return buffer;
    }

    public int getBeginIndex() {
        return this.beginIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public int getBufferBeginIndex() {
        return BUFFER_BEGIN_INDEX;
    }

    public int getBufferEndIndex() {
        return this.buffer.length() - 1;
    }

    public void setBeginIndex(int beginIndex) {
        if (beginIndex <= this.buffer.length()) {
            if (beginIndex < 0){
                throw new IllegalArgumentException("L'index de début doit être supérieure ou égale à 0.");
            }else if(beginIndex > this.endIndex) {
                throw new IllegalArgumentException("L'index de début doit être inférieure ou égale à l'index de fin.");
            } else this.beginIndex = beginIndex;
        }else {
            throw new IndexOutOfBoundsException("L'index de début est hors du buffer.");
        }
    }

    public void setEndIndex(int endIndex) {
        if (endIndex <= this.buffer.length()) {
            if (endIndex < 0){
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à 0.");
            }else if(endIndex < this.beginIndex) {
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à l'index de début.");
            } else this.endIndex = endIndex;
        }else {
            throw new IndexOutOfBoundsException("L'index de fin est hors du buffer.");
        }
    }

    public boolean test(int begin, int end, StringBuilder bf){
        if (end <= bf.length()) {
            if (end < 0){
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à 0.");
            }else if(end < begin) {
                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à l'index de début.");
            } else if (begin < 0){
                throw new IllegalArgumentException("L'index de début doit être supérieure ou égale à 0.");
            }else {
                return true;
            }
        }else {
            throw new IndexOutOfBoundsException("L'index de fin est hors du buffer.");
        }
    }
}
