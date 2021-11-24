package main.java.istic.aco.editor.Recorder;

import main.java.istic.aco.editor.Memento.Memento;

public interface UndoManager {
	
   public void save(Memento m) throws IllegalArgumentException;
   
   public void undo();
   
   public void redo();
}
