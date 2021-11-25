package test.java.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.EngineImpl;
import main.java.istic.aco.editor.Selection;
import main.java.istic.aco.editor.SelectionImpl;
import main.java.istic.aco.editor.Command.CopyCommand;
import main.java.istic.aco.editor.Command.CutCommand;
import main.java.istic.aco.editor.Command.InsertCommand;
import main.java.istic.aco.editor.Command.PasteCommand;
import main.java.istic.aco.editor.Command.SelectionChangeCommand;
import main.java.istic.aco.editor.Invoker.InvokerImpl;
import main.java.istic.aco.editor.Recorder.Recorder;
import main.java.istic.aco.editor.Recorder.RecorderImpl;

public class InvokerTest {
	InvokerImpl invoker;
    SelectionChangeCommand selectionChangeCommand;
    InsertCommand insertCommand;
    PasteCommand pasteCommand;
    CopyCommand copyCommand;
    CutCommand cutCommand;
    StringBuilder stringBuilder;
    Selection selection;
    Engine engine;

    Recorder recorder;

    @BeforeEach
    @Test
    void setCopyCommand() {
        stringBuilder = new StringBuilder("Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        selection = new SelectionImpl(21, 104, stringBuilder);

        engine = new EngineImpl(stringBuilder, selection);
        recorder = new RecorderImpl();
        invoker = new InvokerImpl();

        insertCommand = new InsertCommand(engine, invoker, recorder);
        pasteCommand = new PasteCommand(engine, recorder);
        copyCommand = new CopyCommand(engine, recorder);
        cutCommand = new CutCommand(engine, recorder);

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
        selectionChangeCommand = new SelectionChangeCommand(selection, invoker, recorder);
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

        //Changer les index de la sélection pour recoller au même endroit (pas obligatoire)
        selectionChangeCommand = new SelectionChangeCommand(selection, invoker, recorder);
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setBeginIndex(21);
        invoker.setEndIndex(21);
        invoker.selectionChange();

        invoker.pasteClipboard();

        assertEquals(result, engine.getBufferContents());
    }

}
