package main.java.istic.aco.editor.exceptions;
/**
 * CannotUndoException, Exception for undo action
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 *
 */
public class CannotUndoException extends RuntimeException {
    /**
     * Constructs a {@code CannotRedoException}.
     * @param message, the message to display if the exception is lifted
     */
    public CannotUndoException(String message) {
        super(message);
    }
}
