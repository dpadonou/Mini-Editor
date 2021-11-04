
package main.java.istic.aco.editor;

/**
 * Engine Interface Implementation, EngineImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */

public class EngineImpl implements Engine {
    private StringBuilder buffer;
    private String clipboard;
    private Selection selection;

    public EngineImpl() {
        this.buffer = new StringBuilder();
        this.selection = new SelectionImpl();
    }

    public EngineImpl(StringBuilder buffer, Selection s) {
        if (test(buffer, s)) {
            this.buffer = buffer;
            this.selection = s;
        }
    }

    @Override
    public StringBuilder getBuffer() {
        return this.buffer;
    }

    @Override
    public void setBuffer(StringBuilder buffer) {
        if (buffer != null)
            this.buffer = buffer;
        else
            throw new NullPointerException("Le buffer ne peut être nul.");
    }

    @Override
    public Selection getSelection() {
        return this.selection;
    }

    /**
     * Provides the selection content's
     *
     * @return the buffer content's for the selection
     */
    public String getSelectionContents() {
        return this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    @Override
    public String getBufferContents() {
        return this.buffer.toString();
    }

    @Override
    public String getClipboardContents() {
        return this.clipboard;
    }

    @Override
    public void cutSelectedText() {
        this.clipboard = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    @Override
    public void copySelectedText() {
        this.clipboard = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    @Override
    public void pasteClipboard() {
        this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), this.clipboard);
    }

    @Override
    public void insert(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Vous devez passer une varaible non vide");
        } else {
            this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), s);
        }

    }

    @Override
    public void delete() {
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    /**
     * Test if the constructor parameters are good.
     *
     * @param buffer
     * @param selection
     * @return true if the parameters are good and error if not.
     * @throws IllegalArgumentException if the buffer don't match.
     * @throws NullPointerException     if the buffer or the selection are null
     */
    public boolean test(StringBuilder buffer, Selection selection) throws IllegalArgumentException, NullPointerException {
        if (buffer == null) {
            throw new NullPointerException("Le buffer ne peut être nul.");
        } else if (selection == null) {
            throw new NullPointerException("La selection ne peut être nulle.");
        } else if (!(selection.getBuffer() == (buffer))) {
            throw new IllegalArgumentException("Le buffer de la selection ne correspond pas.");
        } else return true;
    }

    @Override
    public void setSelection(Selection selection) {
        if (selection != null) {
            if (selection.getBuffer() == (this.buffer)) {
                this.selection = selection;
            } else {
                throw new IllegalArgumentException("Le buffer de la selection ne correspond pas.");
            }
        } else {
            throw new NullPointerException("La selection ne peut être nulle.");
        }
    }
}
