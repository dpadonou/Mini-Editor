package istic.aco.editor.exceptions;

public class CannotUndoException extends RuntimeException {
    /**
     * Constructs a {@code CannotRedoException}.
     */
    public CannotUndoException(String message) {
        super(message);
    }
}
