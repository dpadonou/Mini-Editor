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
        this.buffer = new StringBuilder("");
        this.selection = new SelectionImpl();
    }

    public EngineImpl(StringBuilder buffer, Selection s) {
        if (test(buffer, s)) {
            this.buffer = buffer;
            this.selection = s;
        }
    }

    public StringBuilder getBuffer() {
        return this.buffer;
    }

    public void setBuffer(StringBuilder buffer) {
        if (buffer != null)
            this.buffer = buffer;
        else
            throw new NullPointerException("Le buffer ne peut �tre nul.");
    }

    public Selection getSelection() {
        return this.selection;
    }

    public String getSelectionContents() {
        return this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    public String getBufferContents() {
        return this.buffer.toString();
    }

    public String getClipboardContents() {
        return this.clipboard;
    }

    public void cutSelectedText() {
        String s = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
        this.clipboard = s;
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    public void copySelectedText() {
        String s = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
        this.clipboard = s;
    }

    public void pasteClipboard() {
        this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), this.clipboard);
    }

    public void insert(String s) {
        this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), s);
    }

    public void delete() {
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    public boolean test(StringBuilder buffer, Selection selection) {
        if (buffer == null) {
            throw new NullPointerException("Le buffer ne peut �tre nul.");
        } else if (selection == null) {
            throw new NullPointerException("La selection ne peut �tre nulle.");
        } else if (!(selection.getBuffer().equals(buffer))) {
            throw new IllegalArgumentException("Le buffer de la selection ne correspond pas.");
        } else return true;
    }

    public void setSelection(Selection selection) {
        if (selection != null) {
            if (selection.getBuffer().equals(this.buffer)) {
                this.selection = selection;
            } else {
                throw new IllegalArgumentException("Le buffer de la selection ne correspond pas.");
            }
        } else {
            throw new NullPointerException("La selection ne peut �tre nulle.");
        }
    }
}
