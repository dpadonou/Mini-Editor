package istic.aco.editor;

/**
 * Selection Interface implementation, SelectionImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn Padonou
 */

public class SelectionImpl implements Selection {
    static int BUFFER_BEGIN_INDEX = 0;
    private int beginIndex;
    private int endIndex;
    private StringBuilder buffer;

    public SelectionImpl(int beginIndex, int endIndex, StringBuilder buffer) {
        if (test(beginIndex, endIndex, buffer)) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.buffer = buffer;
        }
    }

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
                throw new IllegalArgumentException("L'index de d�but doit �tre sup�rieure ou �gale � 0.");
            } else if (beginIndex > this.endIndex) {
                throw new IllegalArgumentException("L'index de d�but doit �tre inf�rieure ou �gale � l'index de fin.");
            } else this.beginIndex = beginIndex;
        } else {
            throw new IndexOutOfBoundsException("L'index de d�but est hors du buffer.");
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
                throw new IllegalArgumentException("L'index de fin doit �tre sup�rieure ou �gale � 0.");
            } else if (endIndex < this.beginIndex) {
                throw new IllegalArgumentException("L'index de fin doit �tre sup�rieure ou �gale � l'index de d�but.");
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

    public boolean test(int begin, int end, StringBuilder bf) {
        if (end <= bf.length()) {
            if (end < 0) {
                throw new IllegalArgumentException("L'index de fin doit �tre sup�rieure ou �gale � 0.");
            } else if (end < begin) {
                throw new IllegalArgumentException("L'index de fin doit �tre sup�rieure ou �gale � l'index de d�but.");
            } else if (begin < 0) {
                throw new IllegalArgumentException("L'index de d�but doit �tre sup�rieure ou �gale � 0.");
            } else {
                return true;
            }
        } else {
            throw new IndexOutOfBoundsException("L'index de fin est hors du buffer.");
        }
    }
}
