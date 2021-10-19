package aco_v1;

public class SelectionImpl implements Selection {
    private int beginIndex;
    private int endIndex;
    private StringBuilder buffer;
     static int BUFFER_BEGIN_INDEX = 0;
    
	/**
	 * @param beginIndex
	 * @param endIndex
	 * @param buffer
	 */
	public SelectionImpl(int beginIndex, int endIndex, StringBuilder buffer) {
		if(beginIndex<=endIndex && endIndex<=buffer.length()-1 && beginIndex>=0) {
			this.beginIndex = beginIndex;
			this.endIndex = endIndex;
			this.buffer = buffer;
		}
	}

	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(StringBuilder buffer) {
		this.buffer = buffer;
	}


	@Override
	public int getBeginIndex() {
		// TODO Auto-generated method stub
		return this.beginIndex;
	}

	@Override
	public int getEndIndex() {
		// TODO Auto-generated method stub
		return this.endIndex;
	}

	@Override
	public int getBufferBeginIndex() {
		// TODO Auto-generated method stub
		return BUFFER_BEGIN_INDEX;
	}

	@Override
	public int getBufferEndIndex() {
		// TODO Auto-generated method stub
		return this.buffer.length()-1;
	}

	@Override
	public void setBeginIndex(int beginIndex) {
		// TODO Auto-generated method stub
		this.beginIndex=beginIndex;
		
	}

	@Override
	public void setEndIndex(int endIndex) {
		// TODO Auto-generated method stub
		this.endIndex= endIndex;
	}

}
