package istic.aco.editor;

public class SelectionImpl implements Selection {
	private int beginIndex;
    private int endIndex;
    private StringBuilder buffer;
     static int BUFFER_BEGIN_INDEX = 0;
     
    /**
	 * 
	 */
	public SelectionImpl() {
		super();
	}
    
	/**
	 * set the buffer who will contains the selection
	 * @param buffer the buffer to set
	 */
	public void setBuffer(StringBuilder buffer) {
		this.buffer = buffer;
	}
 
	/**
	 * Provides access to the selection begin index
	 *  @return the selection beginIndex
	 */
	@Override
	public int getBeginIndex() {
		// TODO Auto-generated method stub
		return this.beginIndex;
	}
	/**
	 * Provides access to the selection end index
	 * @return the selection endIndex
	 */
	@Override
	public int getEndIndex() {
		// TODO Auto-generated method stub
		return this.endIndex;
	}
	
	/**
	 * Provides the beginIndex of the buffer who contains the selection
	 * @return the selection endIndex
	 */
	@Override
	public int getBufferBeginIndex() {
		// TODO Auto-generated method stub
		return BUFFER_BEGIN_INDEX;
	}
	
	/**
	 * Provides the endIndex of the buffer who contains the selection
	 * @return the selection endIndex
	 */
	@Override
	public int getBufferEndIndex() {
		// TODO Auto-generated method stub
		return this.buffer.length()-1;
	}

	/**
	 * Set the beginIndex of the selection
	 * @param beginIndex the begin index of the selection
	 */
	@Override
	public void setBeginIndex(int beginIndex) {
		// TODO Auto-generated method stub
		Integer i = Integer.valueOf(beginIndex);
		if(beginIndex<=endIndex && beginIndex>=BUFFER_BEGIN_INDEX && beginIndex<this.getBufferEndIndex()) {
			if(i != null) {
				this.beginIndex=beginIndex;
			}else {
				throw new NullPointerException("L'index de début ne doit pas être nul");
			}
			
		}else{
			throw new IndexOutOfBoundsException();
		}
		
		
	}
	
	/**
	 * Set the endIndex of the selection
	 * @param endIndex the end index of the selection
	 */
	@Override
	public void setEndIndex(int endIndex) {
		// TODO Auto-generated method stub
		Integer i = Integer.valueOf(endIndex);
		if(endIndex>= beginIndex && endIndex>=BUFFER_BEGIN_INDEX && endIndex<=this.getBufferEndIndex()) {
			if(i!= null) {
				this.endIndex= endIndex;
			}else {
				throw new NullPointerException("L'index de fin ne doit pas être nul");
			}	
		}else{
			throw new IndexOutOfBoundsException();
		}
		
	}

}
