//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package istic.aco.editor;

public class SelectionImpl implements Selection {
    private int beginIndex;
    private int endIndex;
    private StringBuilder buffer;
    static int BUFFER_BEGIN_INDEX = 0;

    public SelectionImpl(int beginIndex, int endIndex, StringBuilder buffer) {
        if (beginIndex <= endIndex && endIndex <= buffer.length() - 1 && beginIndex >= 0) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.buffer = buffer;
        }

    }

    public void setBuffer(StringBuilder buffer) {
        this.buffer = buffer;
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
        this.beginIndex = beginIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
