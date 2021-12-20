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
        selectionChangeCommand = new SelectionChangeCommand(engine, invoker, recorder, undoManager);
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
        invoker.setS("=== NOUVEAU TEXTE INSERER DANS LE BUFFER ===");

        invoker.setCommand("insert", insertCommand);
        invoker.insert();
        String msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
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

        //Changer les index de la selection pour recoller au meme endroit (pas obligatoire)
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setBeginIndex(21);
        invoker.setEndIndex(21);
        invoker.selectionChange();

        invoker.pasteClipboard();

        assertEquals(result, engine.getBufferContents());
    }

    @Test
    @DisplayName("Recorder shouldn't record if it's not explicitly started")
    void recorderShouldnTRecordIfItSNotExplicitlyStarted() {
        invoker.setCommand("selection", selectionChangeCommand);

        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        //First call to selctionChange without calling recorder.start().
        invoker.selectionChange();
        assertEquals(60, selection.getBeginIndex());
        assertEquals(230, selection.getEndIndex());

        invoker.setBeginIndex(130);
        invoker.setEndIndex(200);
        //Second call to selctionChange without calling recorder.start().
        invoker.selectionChange();
        assertEquals(130, selection.getBeginIndex());
        assertEquals(200, selection.getEndIndex());

        //Trying to go back to the first selection begin and end index by calling recorder.replay()
        recorder.replay();
        //Nothing hadn't changed
        assertEquals(130, selection.getBeginIndex());
        assertEquals(200, selection.getEndIndex());
    }

    @Test
    @DisplayName("Recorder should start recording after a call to recorder.start()")
    void recorderShouldStartRecordingAfterACallToRecorderStart() {
        invoker.setCommand("selection", selectionChangeCommand);

        //Start recording
        recorder.start();

        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        //First call to selctionChange.
        invoker.selectionChange();
        assertEquals(60, selection.getBeginIndex());
        assertEquals(230, selection.getEndIndex());
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        invoker.setBeginIndex(130);
        invoker.setEndIndex(200);
        //Second call to selctionChange.
        invoker.selectionChange();
        assertEquals(130, selection.getBeginIndex());
        assertEquals(200, selection.getEndIndex());

        //Trying to go back to the first selection begin and end index by calling recorder.replay()
        recorder.replay();
        //The beginIndex and the endIndex had returned to their prévious value : 60 and 230
        assertEquals(60, selection.getBeginIndex());
        assertEquals(230, selection.getEndIndex());
    }

    @Test
    @DisplayName("Test recording on insertCommand")
    void testRecordingOnInsertCommand() {
        invoker.setS("=== NOUVEAU TEXTE INSERER DANS LE BUFFER ===");
        invoker.setCommand("insert", insertCommand);
        invoker.setCommand("selection", selectionChangeCommand);
        //Vérification de l'état initial du buffer
        assertEquals(stringBuilder.toString(), engine.getBufferContents());

        //Start recording
        recorder.start();

        //Change selection to insert first piece of text
        invoker.setBeginIndex(21);
        invoker.setEndIndex(104);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());

        //Firt insert after recording
        invoker.insert();
        String msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());

        //Change selection to insert another piece of text
        invoker.setBeginIndex(103);
        invoker.setEndIndex(114);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(103, selection.getBeginIndex());
        assertEquals(114, selection.getEndIndex());

        invoker.insert();
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());

        //Replay all insertion on the brand-new buffer
        recorder.replay();
        // The two insertCommand has been made at begin: 103 and end: 114, because those are the last beginIndex and endIndex of the engine
        // Which means all the commands (selectionChange > insertComand > selectionChange > insertCommand)
        // have been successfully replayed
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === TEXTE INSERER DANS LE BUFFER === TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());
        //Selection indexes has returned the first values we gave to selectionChangeCommand
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
    }

    @Test
    @DisplayName("Teststing stop recording method")
    void teststingStopRecordingMethod() {
        invoker.setS("=== NOUVEAU TEXTE INSERER DANS LE BUFFER ===");
        invoker.setCommand("insert", insertCommand);
        invoker.setCommand("selection", selectionChangeCommand);
        //Vérification de l'état initial du buffer
        assertEquals(stringBuilder.toString(), engine.getBufferContents());

        //Start recording
        recorder.start();

        //Change selection to insert first piece of text
        invoker.setBeginIndex(21);
        invoker.setEndIndex(104);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());

        //Firt insert after recording
        invoker.insert();
        String msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());

        //Change selection to insert another piece of text
        invoker.setBeginIndex(103);
        invoker.setEndIndex(114);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(103, selection.getBeginIndex());
        assertEquals(114, selection.getEndIndex());

        //Stop recording
        recorder.stop();

        //This insert shouldn't be recorded
        invoker.insert();
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());

        //Replay all insertion on the brand-new buffer
        recorder.replay();
        /*
         * Only one insertCommand has been applied at begin: 103 and end: 114
         * Which means only this commands <b>(selectionChange > insertComand > selectionChange)</b>
         * have been successfully replayed
         */
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine.getBufferContents());
        //Selection indexes has returned the first values we gave to selectionChangeCommand
        assertEquals(21, selection.getBeginIndex());
        assertEquals(104, selection.getEndIndex());
    }
}
