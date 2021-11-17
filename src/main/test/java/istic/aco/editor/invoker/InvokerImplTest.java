package istic.aco.editor.invoker;

import istic.aco.editor.Command.CopyCommand;
import istic.aco.editor.Command.CutCommand;
import istic.aco.editor.Command.InsertCommand;
import istic.aco.editor.Command.SelectionChangeCommand;
import istic.aco.editor.Engine;
import istic.aco.editor.EngineImpl;
import istic.aco.editor.Invoker.InvokerImpl;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Recorder.RecorderImpl;
import istic.aco.editor.Selection;
import istic.aco.editor.SelectionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvokerImplTest {

    InvokerImpl invoker;
    SelectionChangeCommand selectionChangeCommand;
    InsertCommand insertCommand;
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

        copyCommand = new CopyCommand(engine, recorder);
        cutCommand = new CutCommand(engine, recorder);

        invoker = new InvokerImpl();
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
        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        invoker.selectionChange();

        selectionChangeCommand = new SelectionChangeCommand(selection, invoker, recorder);
        invoker.setCommand("selection", selectionChangeCommand);

        assertEquals(60, engine.getSelection().getBeginIndex());
        assertEquals(230, engine.getSelection().getEndIndex());

        String msg = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        invoker.setCommand("copy", copyCommand);
        invoker.copyText();
        assertEquals(msg, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Insert should change the buffer selection with the parameter s of the invoker in the buffer")
    void insertShouldChangeTheBufferSelectionWithTheParameterSOfTheInvokerInTheBuffer() {
        invoker.setS("=== TEXTE A INSERER DANS LE BUFFER ===");

        insertCommand = new InsertCommand(engine, invoker, recorder);
        invoker.setCommand("insert", insertCommand);
        invoker.insert();

        String msg = "Simply dummy text of === TEXTE A INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());
    }
}
