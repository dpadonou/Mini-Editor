//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package istic.aco.editor;

public class EngineImpl implements Engine {
    private StringBuilder buffer;
    private String clipboard;
    private Selection selection;

    public EngineImpl() {
    }

    public EngineImpl(StringBuilder buffer, Selection s) {
        this.buffer = buffer;
        this.selection = s;
    }

    public StringBuilder getBuffer() {
        return this.buffer;
    }

    public void setBuffer(StringBuilder buffer) {
        this.buffer = buffer;
    }

    public Selection getSelection() {
        return this.selection;
    }

    public String getSelectionContents() {
        return this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex() + 1);
    }

    public String getBufferContents() {
        return this.buffer.toString();
    }

    public String getClipboardContents() {
        return this.clipboard;
    }

    public void cutSelectedText() {
        String s = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex() + 1);
        this.clipboard = s;
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getBeginIndex());
    }

    public void copySelectedText() {
        String s = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex() + 1);
        this.clipboard = s;
    }

    public void pasteClipboard() {
        this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), this.clipboard);
    }

    public void insert(String s) {
        this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), s);
    }

    public void delete() {
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getBeginIndex());
    }
}
