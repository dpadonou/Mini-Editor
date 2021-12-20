package main.java.istic.aco.editor;

import java.util.Optional;

import main.java.istic.aco.editor.Memento.EngineMemento;
import main.java.istic.aco.editor.Memento.Memento;

/**
 * Engine Interface Implementation, EngineImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class EngineImpl implements EngineOriginator {
    private StringBuilder buffer;
    private String clipboard = "";
    private Selection selection;
  
    /**
     * Constructor to instanciate the engine whitout parameter
     */
    public EngineImpl() {
        this.buffer = new StringBuilder();
        this.selection = new SelectionImpl();
    }
   /**
    * 
    * @param buffer of the engine
    * @param s, the selection of the engine
    */
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
        if (buffer != null) this.buffer = buffer;
        else throw new NullPointerException("Le buffer ne peut être nul.");
    }

    @Override
    public Selection getSelection() {
        return this.selection;
    }

    @Override
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
        String s = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
        this.clipboard = s;
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    @Override
    public void copySelectedText() {
        String s = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
        this.clipboard = s;
    }

    @Override
    public void pasteClipboard() {
        this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), this.clipboard);
    }

    @Override
    public void insert(String s) {
        if (s.isEmpty()) {
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
     * @param buffer the buffer of the engine
     * @param selection the selection of the engine
     * @return true if the parameters are good and error if not.
     * @throws IllegalArgumentException if the buffer don't match.
     * @throws NullPointerException     if the buffer or the selection are null
     */
    public boolean test(StringBuilder buffer, Selection selection) throws IllegalArgumentException, NullPointerException {
        if (buffer == null) {
            throw new NullPointerException("Le buffer ne peut �tre nul.");
        } else if (selection == null) {
            throw new NullPointerException("La selection ne peut �tre nulle.");
        } else if (!(selection.getBuffer().equals(buffer))) {
            throw new IllegalArgumentException("Le buffer de la selection ne correspond pas.");
        } else return true;
    }

    @Override
    public Optional<Memento> save() {
        return Optional.of(new EngineMemento(clipboard, this.getBufferContents(), this.getSelection().getBeginIndex(), this.getSelection().getEndIndex()));
    }

    @Override
    public void restore(Memento m) throws IllegalArgumentException {
        if (m == null) {
            throw new IllegalArgumentException();
        } else {
            buffer.replace(0, getBuffer().length() - 1, (String) m.getParameter()[1]);
            clipboard = m.getParameter()[0].toString();
            if (this.getSelection().getEndIndex() < (int) m.getParameter()[2]) {
                this.getSelection().setEndIndex((int) m.getParameter()[3]);
                this.getSelection().setBeginIndex((int) m.getParameter()[2]);
            } else {
                this.getSelection().setBeginIndex((int) m.getParameter()[2]);
                this.getSelection().setEndIndex((int) m.getParameter()[3]);
            }
        }
    }
}
