package main.java.istic.aco.editor.Invoker;

import main.java.istic.aco.editor.Command.Command;
 /**
 * Invoker interface implementation, InvokerImpl
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public class InvokerImpl implements Invoker {
     private String s;
     private int beginIndex;
     private int endIndex;
     private Command cutTextCommand;
     private Command copyTextCommand;
     private Command pasteTextCommand;
     private Command insertTextCommand;
     private Command selectionChangeCommand;
     private Command replay;
     
     
	/**
	 * @return s the text to insert
	 */
     @Override
	public String getS() {
		return s;
	}

	/**
	 * @param s the s to insert
	 */
     @Override
	public void setS(String s) {
    	 if(s==null) {
    		 throw new NullPointerException("Vous devez passer une chaine non vide");
    	 }else {
    		 this.s = s;
    	 }
		
	}
	
     /**
	 * @return the beginIndex
	 */
     @Override
	public int getBeginIndex() {
		return beginIndex;
	}

	/**
	 * @param beginIndex the beginIndex to set
	 */
     @Override
	public void setBeginIndex(int beginIndex) {
    	 if(beginIndex<0) {
    		 throw new IllegalArgumentException("l'index ne doit pas etre negatif");
    	 }else {
    		 this.beginIndex = beginIndex;
    	 }
		
	}

	/**
	 * @return the endIndex
	 */
     @Override
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * @param endIndex the endIndex to set
	 */
     @Override
	public void setEndIndex(int endIndex) {
    	 if(endIndex<0 || endIndex<beginIndex) {
    		 throw new IllegalArgumentException("l'index ne doit pas etre negatif");
    	 }else {
    		 this.endIndex = endIndex;
    	 }
    	 
	}

	/**
 	 * @param c the cut text command
 	 * @throws NullPointerException if the parameter is null
 	 */
	public void setCutTextCommand(Command c) throws NullPointerException{
		if(c==null) {
			throw new NullPointerException("La commande passée doit etre non nul");
		}else {
			this.cutTextCommand = c;
		}
	}
	 /**
 	 * @param c the copy text command
 	 * @throws NullPointerException if the parameter is null
 	 */
	public void setcopyTextCommand(Command c)  throws NullPointerException{
		if(c==null) {
			throw new NullPointerException("La commande passée doit etre non nul");
		}else {
			this.copyTextCommand = c;
		}
		
	}
	 /**
 	 * @param c the paste text command
 	 * @throws NullPointerException if the parameter is null
 	 */
	public void setPasteTextCommand(Command c)  throws NullPointerException{
		if(c==null) {
			throw new NullPointerException("La commande passée doit etre non nul");
		}else {
			this.pasteTextCommand = c;
		}
		
	}
	 /**
 	 * @param c the insert text command
 	 * @throws NullPointerException if the parameter is null
 	 */
	public void setinsertTextCommand(Command c) throws NullPointerException {
		if(c==null) {
			throw new NullPointerException("La commande passée doit etre non nul");
		}else {
			this.insertTextCommand = c;
		}
		
	}
	 /**
 	 * @param c the selection change command
 	 * @throws NullPointerException if the parameter is null
 	 */
	public void setSelectionChangeCommand(Command c) throws NullPointerException{
		if(c==null) {
			throw new NullPointerException("La commande passée doit etre non nul");
		}else {
			this.selectionChangeCommand = c;
		}
		
	}
	
	 /**
 	 * @param c the selection change command
 	 * @throws NullPointerException if the parameter is null
 	 */
	public void setReplayCommand(Command c) throws NullPointerException {
		if(c==null) {
			throw new NullPointerException("La commande passée doit etre non nul");
		}else {
			this.replay = c;
		}
		
	}

    /**
     * the user action to change selection
     */
	@Override
	public void selectionChange() {
		this.selectionChangeCommand.execute();
		
	}
    
	 /**
     * the user action to cut text
     */
	@Override
	public void cutText() {
		this.cutTextCommand.execute();
		
		
	}
    
	 /**
     * the user action to copy text
     */
	@Override
	public void copytext() {
		this.copyTextCommand.execute();
		
	}

	 /**
     * the user action to paste the clipboard content's
     */
	@Override
	public void pasteClipboard() {
		this.pasteTextCommand.execute();
		
	}
	 /**
     * the user action to insert text
     */
	@Override
	public void insert() {
		this.insertTextCommand.execute();
	}
    
	/**
	 * Replay the last command
	 */
	@Override
	public void replay() {
		// TODO Auto-generated method stub
		this.replay.execute();
		
	}
        
}
