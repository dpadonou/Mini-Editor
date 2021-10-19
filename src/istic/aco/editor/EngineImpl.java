package aco_v1;

public class EngineImpl implements Engine {
	private StringBuilder buffer;
	private String clipboard;
	private Selection selection;
	
	/**
	 * default constructor
	 */
	public EngineImpl() {
	}
    /**
     * Constructor for initialize Engine's parameters
     * @param buffer  the Engine buffer
     * @param s the Engine selection
     */
	public EngineImpl(StringBuilder buffer,Selection s) {
		this.buffer = buffer;
		this.selection=s;
	}
    /**
     * Provides access to the buffer
     * @return the buffer object
     */
	public StringBuilder getBuffer() {
		return buffer;
	}
	/**
     * set the buffer
     */
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
     * Provides the content of the selection 
     * @return the selection content
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
     * Inserts a string in the buffer,  which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
    	if(!(s.isEmpty())){
    		this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), s);
    	}else {
    		  throw new NullPointerException("Le texte ne doit pas etre nul");
    	}
    	
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
    	this.buffer.delete(this.selection.getBeginIndex(), this.selection.getBeginIndex());
    }


}
