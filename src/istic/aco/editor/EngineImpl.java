package aco_v1;

public class EngineImpl implements Engine {
	private StringBuilder buffer;
	private String clipboard;
	private Selection selection;
	
	/**
	 * Constructeur par defaut
	 */
	public EngineImpl() {
	}
    /**
     * Constructeur initialisant les  paramètres
     * @param buffer
     * @param clipboard
     */
	public EngineImpl(StringBuilder buffer,Selection s) {
		this.buffer = buffer;
		this.selection=s;
	}

	public StringBuilder getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuilder buffer) {
		this.buffer = buffer;
	}

	/**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        // TODO
        return this.selection;
    }
    /**
     * recuperer le contenu de la selection
     * @return
     */
    public String getSelectionContents() {
    	return this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex()+1);
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {
        // TODO
        return this.buffer.toString();
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        // TODO
        return this.clipboard;
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        // TODO
    	String s= this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex()+1);
    	this.clipboard=s;
    	this.buffer.delete(this.selection.getBeginIndex(), this.selection.getBeginIndex());
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        // TODO
    	String s= this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex()+1);
    	this.clipboard=s;
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        // TODO
    	this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), clipboard);
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
    	this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), s);
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
    	this.buffer.delete(this.selection.getBeginIndex(), this.selection.getBeginIndex());
    }


}
