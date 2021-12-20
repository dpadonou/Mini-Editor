package test.java.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.EngineImpl;
import main.java.istic.aco.editor.EngineOriginator;
import main.java.istic.aco.editor.Selection;
import main.java.istic.aco.editor.SelectionImpl;
import main.java.istic.aco.editor.Command.Command;
import main.java.istic.aco.editor.Command.CopyCommand;
import main.java.istic.aco.editor.Command.CutCommand;
import main.java.istic.aco.editor.Command.InsertCommand;
import main.java.istic.aco.editor.Command.PasteCommand;
import main.java.istic.aco.editor.Command.RedoCommand;
import main.java.istic.aco.editor.Command.Replay;
import main.java.istic.aco.editor.Command.SelectionChangeCommand;
import main.java.istic.aco.editor.Command.UndoCommand;
import main.java.istic.aco.editor.Invoker.InvokerImpl;
import main.java.istic.aco.editor.Recorder.Recorder;
import main.java.istic.aco.editor.Recorder.RecorderImpl;
import main.java.istic.aco.editor.Recorder.UndoManager;
import main.java.istic.aco.editor.Recorder.UndoManagerImpl;
import main.java.istic.aco.editor.exceptions.CannotRedoException;
import main.java.istic.aco.editor.exceptions.CannotUndoException;

public class Tests {
    Engine engine;
    Engine engine2;
    StringBuilder stringBuilder;
    Selection selection;

    StringBuilder testBf;
    SelectionImpl sp;

    InvokerImpl invoker;
    Command selectionChangeCommand;
    Command insertCommand;
    Command pasteCommand;
    Command copyCommand;
    Command cutCommand;
    Command redoCommand;
    Command undoCommand;
    Command replayCommand;
    StringBuilder stringBuilder1;
    Selection selection1;
    EngineOriginator engine1;
    UndoManager undoManager;

    Recorder recorder;

    @BeforeEach
    void setUp() {
        this.engine = new EngineImpl();
        this.stringBuilder = new StringBuilder("Un buffer de string dans les tests.");
        this.selection = new SelectionImpl(3, 20, stringBuilder);
        engine2 = new EngineImpl(stringBuilder, selection);

        testBf = new StringBuilder("Buffer cr�er pour faire des test sur les getters et getters setters.");
        sp = new SelectionImpl(2, 12, testBf);

        stringBuilder1 = new StringBuilder("Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        selection1 = new SelectionImpl(21, 104, stringBuilder1);

        engine1 = new EngineImpl(stringBuilder1, selection1);
        undoManager = new UndoManagerImpl(engine1);
        undoCommand = new UndoCommand(undoManager);
        redoCommand = new RedoCommand(undoManager);
        recorder = new RecorderImpl();
        replayCommand = new Replay(recorder);
        invoker = new InvokerImpl();
        selectionChangeCommand = new SelectionChangeCommand(engine1, invoker, recorder, undoManager);
        insertCommand = new InsertCommand(engine1, invoker, recorder, undoManager);
        pasteCommand = new PasteCommand(engine1, recorder, undoManager);
        copyCommand = new CopyCommand(engine1, recorder, undoManager);
        cutCommand = new CutCommand(engine1, recorder, undoManager);
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void bufferMustBeEmptyAfterInitialisation() {
        assertEquals("", this.engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        assertEquals("Un buffer de string dans les tests.", this.engine2.getBufferContents());
    }

    @Test
    @DisplayName("Selection shouldn't be null.")
    void notNullSelection() {
        assertThrows(NullPointerException.class, () -> new EngineImpl(stringBuilder, null));
    }

    @Test
    @DisplayName("Selection shouldn't have different buffer than Engine.")
    void notSelectionWithDifferentBuffer() {
        Selection s = new SelectionImpl(0, 10, new StringBuilder("Un nouveau buffer."));
        assertThrows(IllegalArgumentException.class, () -> new EngineImpl(stringBuilder, s));
    }

    @Test
    @DisplayName("buffer shouldn't be null.")
    void notNullBuffer() {
        assertThrows(NullPointerException.class, () -> new EngineImpl(null, selection));
    }

    @Test
    @DisplayName("It shouldn't be possible to set a NULL selection.")
    void cantSetNullSelection() {
        assertThrows(NullPointerException.class, () -> engine2.setSelection(null));
    }

    @Test
    @DisplayName("It shouldn't be possible to a selection with different buffer.")
    void cantSetSelectionWithAnotherBuffer() {
        Selection s = new SelectionImpl(0, 10, new StringBuilder("Un nouveau buffer."));
        assertThrows(IllegalArgumentException.class, () -> engine2.setSelection(s));
    }

    @Test
    @DisplayName("It shouldn't be possible to a NULL buffer.")
    void cantSetNullBuffer() {
        assertThrows(NullPointerException.class, () -> engine2.setBuffer(null));
    }

    @Test
    @DisplayName("Insertion should insert the given char sequence into the given range")
    void insert() {
        String s = "NOUVEAU";
        String msg = "Un NOUVEAU dans les tests.";
        engine2.setSelection(new SelectionImpl(3, 19, stringBuilder));
        engine2.insert(s);
        assertEquals(msg, engine2.getBufferContents());
    }

    @Test
    @DisplayName("It shouldn't be possible to set empty string to insert.")
    void canSetEmptyStringToInsert() {
        String s = "";
        engine2.setSelection(new SelectionImpl(3, 19, stringBuilder));
        assertThrows(IllegalArgumentException.class, () -> engine2.insert(s));
    }

    @Test
    @DisplayName("Clipboard should be empty if no cut or copy hasn't be done.")
    void getClipboardContentsAfterInstanciation() {
        assertEquals("", engine.getClipboardContents());
    }

    @Test
    @DisplayName("Copy shouldn't modify the buffer's content.")
    void copySelectedText() {
        String newBuffer = "Un buffer de string dans les tests.";
        engine2.copySelectedText();
        assertEquals(newBuffer, engine2.getBufferContents());
    }

    @Test
    @DisplayName("Clipboard should be impacted after a copy.")
    void getClipboardContentsAfterCopy() {
        String result = "buffer de string ";
        engine2.copySelectedText();
        assertEquals(result, (engine2.getClipboardContents()));
    }

    @Test
    @DisplayName("Cut should remove the selection from buffer and put it in the clipboard.")
    void cutSelectedText() {
        String newBuffer = "Un dans les tests.";
        engine2.cutSelectedText();
        assertEquals(newBuffer, engine2.getBufferContents());
    }

    @Test
    @DisplayName("Clipboard should be impacted after a cut.")
    void getClipboardContentsAfterCut() {
        String result = "buffer de string ";
        engine2.cutSelectedText();
        assertEquals(result, (engine2.getClipboardContents()));
    }

    @Test
    @DisplayName("delete should remove the selection from buffer. And keep the clipboard unmodified.")
    void delete() {
        String newBuffer = "Un dans les tests.";

        engine2.delete();

        assertEquals(newBuffer, engine2.getBufferContents());
        assertEquals("", engine2.getClipboardContents());
    }

    @Test
    void pasteClipboard() {
        engine2.copySelectedText();

        String ans1 = "Un buffer de string dans les tests.";
        engine2.pasteClipboard();
        assertEquals(ans1, engine2.getBufferContents());

        String ans2 = "buffer de string Un dans les tests.";
        engine2.cutSelectedText();

        //Trying to put the cuted sequence at the begining
        engine2.setSelection(new SelectionImpl(0, 0, stringBuilder));
        engine2.pasteClipboard();
        assertEquals(ans2, engine2.getBufferContents());
    }

    @Test
    @DisplayName("Begin index should be lower than end index")
    void beginIndexShouldBeLowerThanEndIndex() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SelectionImpl(27, 8, testBf));
    }

    @Test
    @DisplayName("Begin index should be greater or equals to 0")
    void beginIndexShouldBeGreaterThan0() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SelectionImpl(-6, 8, testBf));
    }

    @Test
    @DisplayName("End index should be greater 0")
    void EndIndexShouldBeGreaterThan0() {
        Assertions.assertDoesNotThrow(() -> new SelectionImpl(0, 0, testBf), "L'index de fin doit �tre sup�rieure ou �gale � 0.");
    }

    @Test
    @DisplayName("End index can't be greater than buffer length")
    void selectionLengthShouldLesserOrEqualToBufferLength2() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> new SelectionImpl(2, 73, testBf));
    }

    @Test
    @DisplayName("getBeginIndex should return the selection's begin index")
    void getBeginIndex() {
        assert (2 == sp.getBeginIndex());
    }

    @Test
    @DisplayName("getEndIndex should return the selection's end index")
    void getEndIndex() {
        assert (12 == sp.getEndIndex());
    }

    @Test
    @DisplayName("getBufferBeginIndex should always return 0")
    void getBufferBeginIndex() {
        assert (0 == sp.getBufferBeginIndex());
    }

    @Test
    @DisplayName("getBuffer should return the given selection buffer")
    void getBuffer() {
        assertEquals(testBf, sp.getBuffer());
    }

    @Test
    @DisplayName("getBufferEndIndex should return the buffer's end index")
    void getBufferEndIndex() {
        assert (67 == sp.getBufferEndIndex());
    }

    @Test
    @DisplayName("Can't set a begin index lower than 0")
    void setBeginIndex1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setBeginIndex(-3));
    }

    @Test
    @DisplayName("Can't set a begin index greater than the end index")
    void setBeginIndex2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setBeginIndex(39));
    }

    @Test
    @DisplayName("Can't set a end index greater than the buffer length")
    void setBeginIndex3() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sp.setBeginIndex(73));
    }

    @Test
    @DisplayName("Can't set a end index lower than 0")
    void setEndIndex1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setEndIndex(-3));
    }

    @Test
    @DisplayName("Can't set a end index lesser than the begin index")
    void setEndIndex2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setEndIndex(1));
    }

    @Test
    @DisplayName("Can't set a end index greater than the buffer length")
    void setEndIndex3() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sp.setEndIndex(105));
    }


    @Test
    @DisplayName("copy should change the engine clipboard and let the buffer unchanged")
    void copyShouldChangeTheEngineClipboardAndLetTheBufferUnchanged() {
        invoker.setCommand("copy", copyCommand);
        invoker.copyText();
        //Change clipboard content
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
        //Buffer remains the same
        assertEquals(stringBuilder1.toString(), engine1.getBufferContents());
    }

    @Test
    @DisplayName("Cut should change the buffer and the clipboard too")
    void cutShouldChangeTheBufferAndTheClipboardToo() {
        invoker.setCommand("cut", cutCommand);
        invoker.cutText();

        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", engine1.getBufferContents());
    }

    @Test
    @DisplayName("SelectionChange should change the index of the selection")
    void selectionChangeShouldChangeTheIndexOfTheSelection() {
        selectionChangeCommand = new SelectionChangeCommand(engine1, invoker, recorder, undoManager);
        invoker.setCommand("selection", selectionChangeCommand);

        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        invoker.selectionChange();

        assertEquals(60, selection1.getBeginIndex());
        assertEquals(230, selection1.getEndIndex());
    }

    @Test
    @DisplayName("Insert should change the buffer selection1 with the parameter s of the invoker in the buffer")
    void insertShouldChangeTheBufferSelectionWithTheParameterSOfTheInvokerInTheBuffer() {
        invoker.setS("=== TEXTE A INSERER DANS LE BUFFER ===");

        invoker.setCommand("insert", insertCommand);
        invoker.insert();
        String msg = "Simply dummy text of === TEXTE A INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine1.getBufferContents());
    }

    @Test
    @DisplayName("Paste should paste the clipboard content into the buffer")
    void pasteShouldPasteTheClipboardContentIntoTheBuffer() {
        invoker.setCommand("cut", cutCommand);
        invoker.cutText();

        String cutted = "Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        //Check if the cut has been done
        assertEquals(cutted, engine1.getBufferContents());

        String result = "Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        invoker.setCommand("paste", pasteCommand);

        //Changer les index de la s?lection pour recoller au m?me endroit (pas obligatoire)
        selectionChangeCommand = new SelectionChangeCommand(engine1, invoker, recorder, undoManager);
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setBeginIndex(21);
        invoker.setEndIndex(21);
        invoker.selectionChange();

        invoker.pasteClipboard();

        assertEquals(result, engine1.getBufferContents());
    }

    @Test
    @DisplayName("Recorder shouldn't record if it's not explicitly started")
    void recorderShouldnTRecordIfItSNotExplicitlyStarted() {
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("replay", replayCommand);

        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        //First call to selctionChange without calling recorder.start().
        invoker.selectionChange();
        assertEquals(60, selection1.getBeginIndex());
        assertEquals(230, selection1.getEndIndex());

        invoker.setBeginIndex(130);
        invoker.setEndIndex(200);
        //Second call to selctionChange without calling recorder.start().
        invoker.selectionChange();
        assertEquals(130, selection1.getBeginIndex());
        assertEquals(200, selection1.getEndIndex());

        //Trying to go back to the first selection begin and end index by calling recorder.replay()
        invoker.replay();
        //Nothing hadn't changed
        assertEquals(130, selection1.getBeginIndex());
        assertEquals(200, selection1.getEndIndex());
    }

    @Test
    @DisplayName("Recorder should start recording after a call to recorder.start()")
    void recorderShouldStartRecordingAfterACallToRecorderStart() {
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("replay", replayCommand);

        //Start recording
        recorder.start();

        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        //First call to selctionChange.
        invoker.selectionChange();
        assertEquals(60, selection1.getBeginIndex());
        assertEquals(230, selection1.getEndIndex());
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        invoker.setBeginIndex(130);
        invoker.setEndIndex(200);
        //Second call to selctionChange.
        invoker.selectionChange();
        assertEquals(130, selection1.getBeginIndex());
        assertEquals(200, selection1.getEndIndex());

        //Trying to go back to the first selection begin and end index by calling recorder.replay()
        invoker.replay();
        //The beginIndex and the endIndex had returned to their pr�vious value : 60 and 230
        assertEquals(60, selection1.getBeginIndex());
        assertEquals(230, selection1.getEndIndex());
    }

    @Test
    @DisplayName("Test recording on insertCommand")
    void testRecordingOnInsertCommand() {
        invoker.setS("=== NOUVEAU TEXTE INSERER DANS LE BUFFER ===");
        invoker.setCommand("insert", insertCommand);
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("replay", replayCommand);

        //V�rification de l'�tat initial du buffer
        assertEquals(stringBuilder1.toString(), engine1.getBufferContents());

        //Start recording
        recorder.start();

        //Change selection to insert first piece of text
        invoker.setBeginIndex(21);
        invoker.setEndIndex(104);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());

        //Firt insert after recording
        invoker.insert();
        String msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine1.getBufferContents());

        //Change selection to insert another piece of text
        invoker.setBeginIndex(103);
        invoker.setEndIndex(114);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(103, selection1.getBeginIndex());
        assertEquals(114, selection1.getEndIndex());

        invoker.insert();
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine1.getBufferContents());

        //Replay all insertion on the brand-new buffer
        invoker.replay();
        // The two insertCommand has been made at begin: 103 and end: 114, because those are the last beginIndex and endIndex of the engine1
        // Which means all the commands (selectionChange > insertComand > selectionChange > insertCommand)
        // have been successfully replayed
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === TEXTE INSERER DANS LE BUFFER === TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine1.getBufferContents());
        //Selection indexes has returned the first values we gave to selectionChangeCommand
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
    }

    @Test
    @DisplayName("Teststing stop recording method")
    void teststingStopRecordingMethod() {
        invoker.setS("=== NOUVEAU TEXTE INSERER DANS LE BUFFER ===");
        invoker.setCommand("insert", insertCommand);
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("replay", replayCommand);

        //V�rification de l'�tat initial du buffer
        assertEquals(stringBuilder1.toString(), engine1.getBufferContents());

        //Start recording
        recorder.start();

        //Change selection to insert first piece of text
        invoker.setBeginIndex(21);
        invoker.setEndIndex(104);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());

        //Firt insert after recording
        invoker.insert();
        String msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine1.getBufferContents());

        //Change selection to insert another piece of text
        invoker.setBeginIndex(103);
        invoker.setEndIndex(114);
        //This selection has been recorded too
        invoker.selectionChange();
        assertEquals(103, selection1.getBeginIndex());
        assertEquals(114, selection1.getEndIndex());

        //Stop recording
        recorder.stop();

        //This insert shouldn't be recorded
        invoker.insert();
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine1.getBufferContents());

        //Replay all insertion on the brand-new buffer
        invoker.replay();
        /*
         * Only one insertCommand has been applied at begin: 103 and end: 114
         * Which means only this commands <b>(selectionChange > insertComand > selectionChange)</b>
         * have been successfully replayed
         */
        msg = "Simply dummy text of === NOUVEAU TEXTE INSERER DANS LE BUFFER === dummy text ever since the 1500s, when=== NOUVEAU TEXTE INSERER DANS LE BUFFER === TEXTE INSERER DANS LE BUFFER === printer took a galley of type and scrambled it to make a type specimen book.";
        assertEquals(msg, engine1.getBufferContents());
        //Selection indexes has returned the first values we gave to selectionChangeCommand
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
    }

    @Test
    @DisplayName("Calls to undo() should put the engine1 in it's last state")
    void oneCallToUndoShouldPutTheEngineInItSLastState() {
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("undo", undoCommand);
        invoker.setCommand("redo", redoCommand);

        //Change the selection1 ounce
        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        invoker.selectionChange();
        assertEquals(60, selection1.getBeginIndex());
        assertEquals(230, selection1.getEndIndex());

        //Change the selection1 twice
        invoker.setBeginIndex(100);
        invoker.setEndIndex(200);
        invoker.selectionChange();
        assertEquals(100, selection1.getBeginIndex());
        assertEquals(200, selection1.getEndIndex());

        //Undo the last command (selectionChangeCommand in this case)
        invoker.undo();
        invoker.undo();
        //The selection1 state might return to its previous state
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
    }

    @Test
    @DisplayName("If any command hadn't been triggered then undo and redo should raise an exception")
    void ifAnyCommandHadnTBeenTriggeredThenUndoAndRedoShouldRaiseAnException() {
        invoker.setCommand("undo", undoCommand);
        invoker.setCommand("redo", redoCommand);
        assertThrows(CannotUndoException.class, () -> undoManager.undo());
        assertThrows(CannotRedoException.class, () -> undoManager.redo());
    }

    @Test
    @DisplayName("redo or undo should raise en exception if there no more state to novigate to")
    void redoOrUndoShouldRaiseEnExceptionIfThereNoMoreStateToNovigateTo() {
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("undo", undoCommand);
        invoker.setCommand("redo", redoCommand);

        //Change the selection1 ounce
        invoker.setBeginIndex(60);
        invoker.setEndIndex(230);
        invoker.selectionChange();
        assertEquals(60, selection1.getBeginIndex());
        assertEquals(230, selection1.getEndIndex());

        //Change the selection1 twice
        invoker.setBeginIndex(100);
        invoker.setEndIndex(200);
        invoker.selectionChange();
        assertEquals(100, selection1.getBeginIndex());
        assertEquals(200, selection1.getEndIndex());

        //Undo the last command (selectionChangeCommand in this case)
        invoker.undo();
        invoker.undo();
        //The selection1 state might return to its initial state
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());

        //Calling undo a third time (exception should raise)
        assertThrows(CannotUndoException.class, () -> undoManager.undo());

        invoker.redo();
        invoker.redo();
        //The selection1 state might return to its previous state
        assertEquals(60, selection1.getBeginIndex());
        assertEquals(230, selection1.getEndIndex());

        //No more redo possible
        assertThrows(CannotRedoException.class, () -> undoManager.redo());
    }

    @Test
    @DisplayName("Trying to undo and redo clipboard state")
    void tryingToUndoAndRedoClipboardState() {
        invoker.setCommand("copy", copyCommand);
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("undo", undoCommand);
        invoker.setCommand("redo", redoCommand);

        invoker.copyText();////////////////////////////////////////////////////
        //Change clipboard content
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
        // The beginning state of beginIndex And endIndex have been saved
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());

        //Change the selection1 ounce
        invoker.setEndIndex(197);
        invoker.setBeginIndex(105);
        invoker.selectionChange();///////////////////////////////////////////////
        assertEquals(105, selection1.getBeginIndex());
        assertEquals(197, selection1.getEndIndex());

        //Copy the text into the new selection1 range
        invoker.copyText();////////////////////////////////////////////////////

        //Its change clipboard content too
        assertEquals("dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled", engine1.getClipboardContents());

        //First undo:
        //Go back to the latest saved state
        invoker.undo();
        assertEquals(105, selection1.getBeginIndex());
        assertEquals(197, selection1.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());

        //Second undo
        //The beginIndex and the endIndex stays the same
        //But the cliboard content return to the state in which it was after the first copyCommand occurs
        invoker.undo();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());

        //Third undo
        //The beginIndex and the endIndex returns to their initial state
        //The cliboard content stays in the state in which it was after the first copyCommand occurs
        invoker.undo();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
        //Return to initial state
        assertEquals("", engine1.getClipboardContents());

        //Another undo
        //Anyother undo should raise an error
        //There is no more state to go back to
        assertThrows(CannotUndoException.class, () -> undoManager.undo());

        //The second REDO
        //The beginIndex and the endIndex should go back to 21 and 104
        //But the cliboard content return to the state in which it was after the last copyCommand occurs
        invoker.redo();
        invoker.redo();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
    }

    @Test
    @DisplayName("Trying to do and undo clipboard, buffer and selection1 state at ounce")
    void tryingToDoAndUndoClipboardBufferAndSelectionStateAtOunce() {
        invoker.setCommand("selection", selectionChangeCommand);
        invoker.setCommand("cut", cutCommand);
        invoker.setCommand("undo", undoCommand);
        invoker.setCommand("redo", redoCommand);

        invoker.cutText();////////////////////////////////////////////////////

        //Change clipboard content
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
        //Change buffer content
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", engine1.getBufferContents());
        //Initial begin index and end index
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());

        //Change the selection1 ounce
        invoker.setBeginIndex(7);
        invoker.setEndIndex(114);
        invoker.selectionChange();///////////////////////////////////////////////
        //Change beginIndex and endIndex
        assertEquals(7, selection1.getBeginIndex());
        assertEquals(114, selection1.getEndIndex());

        //cut the text between the new selection1 range
        invoker.cutText();////////////////////////////////////////////////////

        //Its change clipboard content too
        assertEquals("dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled", engine1.getClipboardContents());
        //Its change the buffer content too
        assertEquals("Simply  it to make a type specimen book.", engine1.getBufferContents());

        //First undo:
        //Go back to the state  after the latest selectionChange
        invoker.undo();
        assertEquals(7, selection1.getBeginIndex());
        assertEquals(114, selection1.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine1.getBufferContents());

        //Second undo
        //The beginIndex and the endIndex stays the same
        //But the cliboard content return to the state in which it was after the first copyCommand occurs
        invoker.undo();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine1.getBufferContents());

        //Third undo
        //The beginIndex and the endIndex returns to their initial state
        //The cliboard content stays in the state in which it was after the first copyCommand occurs
        invoker.undo();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
        assertEquals("", engine1.getClipboardContents());
        assertEquals("Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine1.getBufferContents());

        //Another undo
        //Anyother undo should raise an error
        //There is no more state to go back to
        assertThrows(CannotUndoException.class, () -> undoManager.undo());

        //The Second REDO
        //The beginIndex and the endIndex should go back to 7 and 114
        //But the cliboard and the buffer content return to the state in which they were after the second Command occurs
        invoker.redo();
        invoker.redo();
        assertEquals(21, selection1.getBeginIndex());
        assertEquals(104, selection1.getEndIndex());
        assertEquals("the printing and typesetting industry. Lorem Ipsum has been the industry's standard", engine1.getClipboardContents());
        assertEquals("Simply dummy text of  dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book..", engine1.getBufferContents());
    }
}
