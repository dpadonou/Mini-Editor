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
	 * @param beginIndex
	 * @param endIndex
	 * @param buffer
	 */
	public SelectionImpl(int beginIndex, int endIndex, StringBuilder buffer) {
		if(test(beginIndex,endIndex,buffer)) {
			this.beginIndex = beginIndex;
			this.endIndex = endIndex;
			this.buffer = buffer;
		}
		
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
		if (beginIndex <= this.buffer.length()) {
            if (beginIndex < 0){
                throw new IllegalArgumentException("L'index de début doit être supérieure ou égale à 0.");
            }else if(beginIndex > this.endIndex) {
                throw new IllegalArgumentException("L'index de début doit être inférieure ou égale à l'index de fin.");
            } else if(i != null){
            	throw new NullPointerException("L'index de début ne doit pas être nul");	
            } else this.beginIndex = beginIndex;
        }else {
            throw new IndexOutOfBoundsException("L'index de début est hors du buffer.");
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
		 if (endIndex <= this.buffer.length()) {
	            if (endIndex <= 0){
	                throw new IllegalArgumentException("L'index de fin doit être supérieure à 0.");
	            }else if(endIndex < this.beginIndex) {
	                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à l'index de début.");
	            } else if(i!= null) {
					throw new NullPointerException("L'index de fin ne doit pas être nul");

	            } else this.endIndex = endIndex;
	        }else {
	            throw new IndexOutOfBoundsException("L'index de fin est hors du buffer.");
	        }

	}
	   /**
	    * 
	    * @param begin
	    * @param end
	    * @param bf
	    * @return true if all parameters are good
	    */
	  public boolean test(int begin, int end, StringBuilder bf){
	        if (end <= bf.length()) {
	            if (end <= 0){
	                throw new IllegalArgumentException("L'index de fin doit être supérieure à 0.");
	            }else if(end < begin) {
	                throw new IllegalArgumentException("L'index de fin doit être supérieure ou égale à l'index de début.");
	            } else if (begin < 0){
	                throw new IllegalArgumentException("L'index de début doit être supérieure ou égale à 0.");
	            }else {
	                return true;
	            }
	        }else {
	            throw new IndexOutOfBoundsException("L'index de fin est hors du buffer.");
	        }
	    }

	
	

}
