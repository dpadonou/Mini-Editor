package main.java.istic.aco.editor.exceptions;
/**
 * CannotRedoException, Exception for redo action
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 *
 */
public class CannotRedoException extends RuntimeException {
    /**
     * Constructs a {@code CannotRedoException}.
     * @param message, the message to display if the exception is lifted
     */
    public CannotRedoException(String message) {
        super(message);
    }
}
