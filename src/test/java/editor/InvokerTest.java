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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvokerTest {
    InvokerImpl invoker;
    SelectionChangeCommand selectionChangeCommand;
    InsertCommand insertCommand;
    PasteCommand pasteCommand;
    CopyCommand copyCommand;
    CutCommand cutCommand;
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
        insertCommand = new InsertCommand(engine, invoker, recorder, undoManager);
        pasteCommand = new PasteCommand(engine, recorder, undoManager);
        copyCommand = new CopyCommand(engine, recorder, undoManager);
        cutCommand = new CutCommand(engine, recorder, undoManager);

    }

    @Test
    @DisplayName("copy should change the engine clipboard and let the buffer unchanged")
    void copyShouldChangeTheEngineClipboardAndLetTheBufferUnchanged() {
        invoker.setCommand("copy", copyCommand);
        invoker.copyText();
        //Change clipboard content
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
        //Buffer remains the same
        assertEquals(stringBuilder.toString(), engine.getBufferContents());
    }

    @Test
    @DisplayName("Cut should change the buffer and the clipboard too")
    void cutShouldChangeTheBufferAndTheClipboardToo() {
        invoker.setCommand("cut", cutCommand);
        invoker.cutText();

        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", engine.getBufferContents());
    }

    @Test
    @DisplayName("SelectionChange should change the index of the selection")
    void selectionChangeShouldChangeTheIndexOfTheSelection() {
        selectionChangeCommand = new SelectionChangeCommand(engine, invoker, recorder, undoManager);
        invoker.setCommand("selection", selectionChangeCommand);

        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        invoker.selectionChange();

        assertEquals(60, selection.getBeginIndex());
        assertEquals(230, selection.getEndIndex());
    }

    @Test
    @DisplayName("Insert should change the buffer selection with the parameter s of the invoker in the buffer")
    void insertShouldChangeTheBufferSelectionWithTheParameterSOfTheInvokerInTheBuffer() {
        invoker.setS("=== TEXTE A INSERER DANS LE BUFFER ===");

        invoker.setCommand("insert", insertCommand);
        invoker.insert();
        String msg = "Simply dummy text of === TEXTE A INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());
    }

    @Test
    @DisplayName("Paste should paste the clipboard content into the buffer")
    void pasteShouldPasteTheClipboardContentIntoTheBuffer() {
        invoker.setCommand("cut", cutCommand);
        invoker.cutText();

        String cutted = "Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        //Check if the cut has been done
        assertEquals(cutted, engine.getBufferContents());

        String result = "Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        invoker.setCommand("paste", pasteCommand);

        //Changer les index de la s�lection pour recoller au m�me endroit (pas obligatoire)
        selectionChangeCommand = new SelectionChangeCommand(engine, invoker, recorder, undoManager);
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setBeginIndex(21);
        invoker.setEndIndex(21);
        invoker.selectionChange();

        invoker.pasteClipboard();

        assertEquals(result, engine.getBufferContents());
    }

}
