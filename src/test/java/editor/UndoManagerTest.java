package editor;

import istic.aco.editor.Command.*;
import istic.aco.editor.EngineImpl;
import istic.aco.editor.EngineOriginator;
import istic.aco.editor.Invoker.InvokerImpl;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Recorder.RecorderImpl;
import istic.aco.editor.Recorder.UndoManager;
import istic.aco.editor.Recorder.UndoManagerImpl;
import istic.aco.editor.Selection;
import istic.aco.editor.SelectionImpl;
import istic.aco.editor.exceptions.CannotRedoException;
import istic.aco.editor.exceptions.CannotUndoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UndoManagerTest {
    InvokerImpl invoker;
    Command selectionChangeCommand;
    Command insertCommand;
    Command pasteCommand;
    Command copyCommand;
    Command cutCommand;
    StringBuilder stringBuilder;
    Selection selection;
    EngineOriginator engine;
    UndoManager undoManager;

    Recorder recorder;

    @BeforeEach
    @Test
    void initializer() {
        stringBuilder = new StringBuilder("Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        selection = new SelectionImpl(21, 104, stringBuilder);

        engine = new EngineImpl(stringBuilder, selection);
        undoManager = new UndoManagerImpl(engine);
        recorder = new RecorderImpl();
        invoker = new InvokerImpl();
        selectionChangeCommand = new SelectionChangeCommand(engine, invoker, recorder, undoManager);
        insertCommand = new InsertCommand(engine, invoker, recorder, undoManager);
        pasteCommand = new PasteCommand(engine, recorder, undoManager);
        copyCommand = new CopyCommand(engine, recorder, undoManager);
        cutCommand = new CutCommand(engine, recorder, undoManager);
    }

    @Test
    @DisplayName("Calls to undo() should put the engine in it's last state")
    void oneCallToUndoShouldPutTheEngineInItSLastState() {
        invoker.setCommand("selection", selectionChangeCommand);

        //Change the selection ounce
        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        invoker.selectionChange();
        assertEquals(60, selection.getBeginIndex());
        assertEquals(230, selection.getEndIndex());

        //Change the selection twice
        invoker.setBeginIndex(100);
        invoker.setEndIndex(200);
        invoker.selectionChange();
        assertEquals(100, selection.getBeginIndex());
        assertEquals(200, selection.getEndIndex());

        //Undo the last command (selectionChangeCommand in this case)
        undoManager.undo();
        undoManager.undo();
        //The selection state might return to its previous state
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
    }

    @Test
    @DisplayName("If any command hadn't been triggered then undo and redo should raise an exception")
    void ifAnyCommandHadnTBeenTriggeredThenUndoAndRedoShouldRaiseAnException() {
        assertThrows(CannotUndoException.class, () -> undoManager.undo());
        assertThrows(CannotRedoException.class, () -> undoManager.redo());
    }

    @Test
    @DisplayName("redo or undo should raise en exception if there no more state to novigate to")
    void redoOrUndoShouldRaiseEnExceptionIfThereNoMoreStateToNovigateTo() {
        invoker.setCommand("selection", selectionChangeCommand);

        //Change the selection ounce
        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        invoker.selectionChange();
        assertEquals(60, selection.getBeginIndex());
        assertEquals(230, selection.getEndIndex());

        //Change the selection twice
        invoker.setBeginIndex(100);
        invoker.setEndIndex(200);
        invoker.selectionChange();
        assertEquals(100, selection.getBeginIndex());
        assertEquals(200, selection.getEndIndex());

        //Undo the last command (selectionChangeCommand in this case)
        undoManager.undo();
        undoManager.undo();
        //The selection state might return to its initial state
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());

        //Calling undo a third time (exception should raise)
        assertThrows(CannotUndoException.class, () -> undoManager.undo());

        undoManager.redo();
        undoManager.redo();
        //The selection state might return to its previous state
        assertEquals(60, selection.getBeginIndex());
        assertEquals(230, selection.getEndIndex());

        //No more redo possible
        assertThrows(CannotRedoException.class, () -> undoManager.redo());
    }

    @Test
    @DisplayName("Trying to undo and redo clipboard state")
    void tryingToUndoAndRedoClipboardState() {
        invoker.setCommand("copy", copyCommand);
        invoker.setCommand("selection", selectionChangeCommand);

        invoker.copyText();////////////////////////////////////////////////////
        //Change clipboard content
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
        // The beginning state of beginIndex And endIndex have been saved
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());

        //Change the selection ounce
        invoker.setEndIndex(197);
        invoker.setBeginIndex(105);
        invoker.selectionChange();///////////////////////////////////////////////
        assertEquals(105, selection.getBeginIndex());
        assertEquals(197, selection.getEndIndex());

        //Copy the text into the new selection range
        invoker.copyText();////////////////////////////////////////////////////

        //Its change clipboard content too
        assertEquals("dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled", engine.getClipboardContents());

        //First undo:
        //Go back to the latest saved state
        undoManager.undo();
        assertEquals(105, selection.getBeginIndex());
        assertEquals(197, selection.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());

        //Second undo
        //The beginIndex and the endIndex stays the same
        //But the cliboard content return to the state in which it was after the first copyCommand occurs
        undoManager.undo();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());

        //Third undo
        //The beginIndex and the endIndex returns to their initial state
        //The cliboard content stays in the state in which it was after the first copyCommand occurs
        undoManager.undo();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
        //Return to initial state
        assertEquals("", engine.getClipboardContents());

        //Another undo
        //Anyother undo should raise an error
        //There is no more state to go back to
        assertThrows(CannotUndoException.class, () -> undoManager.undo());

        //The second REDO
        //The beginIndex and the endIndex should go back to 21 and 104
        //But the cliboard content return to the state in which it was after the last copyCommand occurs
        undoManager.redo();
        undoManager.redo();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
    }

    @Test
    @DisplayName("Trying to do and undo clipboard, buffer and selection state at ounce")
    void tryingToDoAndUndoClipboardBufferAndSelectionStateAtOunce() {
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("cut", cutCommand);

        invoker.cutText();////////////////////////////////////////////////////

        //Change clipboard content
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
        //Change buffer content
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", engine.getBufferContents());
        //Initial begin index and end index
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());

        //Change the selection ounce
        invoker.setBeginIndex(7);
        invoker.setEndIndex(114);
        invoker.selectionChange();///////////////////////////////////////////////
        //Change beginIndex and endIndex
        assertEquals(7, selection.getBeginIndex());
        assertEquals(114, selection.getEndIndex());

        //cut the text between the new selection range
        invoker.cutText();////////////////////////////////////////////////////

        //Its change clipboard content too
        assertEquals("dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled", engine.getClipboardContents());
        //Its change the buffer content too
        assertEquals("Simply  it to make a type specimen book.", engine.getBufferContents());

        //First undo:
        //Go back to the state  after the latest selectionChange
        undoManager.undo();
        assertEquals(7, selection.getBeginIndex());
        assertEquals(114, selection.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine.getBufferContents());

        //Second undo
        //The beginIndex and the endIndex stays the same
        //But the cliboard content return to the state in which it was after the first copyCommand occurs
        undoManager.undo();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine.getBufferContents());

        //Third undo
        //The beginIndex and the endIndex returns to their initial state
        //The cliboard content stays in the state in which it was after the first copyCommand occurs
        undoManager.undo();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
        assertEquals("", engine.getClipboardContents());
        assertEquals("Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine.getBufferContents());

        //Another undo
        //Anyother undo should raise an error
        //There is no more state to go back to
        assertThrows(CannotUndoException.class, () -> undoManager.undo());

        //The Second REDO
        //The beginIndex and the endIndex should go back to 7 and 114
        //But the cliboard and the buffer content return to the state in which they were after the second Command occurs
        undoManager.redo();
        undoManager.redo();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine.getBufferContents());
    }
}
