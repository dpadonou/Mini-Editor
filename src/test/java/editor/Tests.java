package editor;

import istic.aco.editor.Command.*;
import istic.aco.editor.*;
import istic.aco.editor.Invoker.InvokerImpl;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Recorder.RecorderImpl;
import istic.aco.editor.Recorder.UndoManager;
import istic.aco.editor.Recorder.UndoManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    Engine engine;
    Engine engine2;
    StringBuilder stringBuilder;
    Selection selection;

    StringBuilder testBf;
    SelectionImpl sp;

    InvokerImpl invoker;
    SelectionChangeCommand selectionChangeCommand;
    InsertCommand insertCommand;
    PasteCommand pasteCommand;
    CopyCommand copyCommand;
    CutCommand cutCommand;
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

        testBf = new StringBuilder("Buffer créer pour faire des test sur les getters et getters setters.");
        sp = new SelectionImpl(2, 12, testBf);

        stringBuilder1 = new StringBuilder("Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        selection1 = new SelectionImpl(21, 104, stringBuilder1);

        engine1 = new EngineImpl(stringBuilder1, selection1);
        undoManager = new UndoManagerImpl(engine1);
        recorder = new RecorderImpl();
        invoker = new InvokerImpl();
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
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SelectionImpl(27, 8, testBf));
        String msg = "L'index de fin doit être supérieure ou égale à l'index de début.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Begin index should be greater or equals to 0")
    void beginIndexShouldBeGreaterThan0() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SelectionImpl(-6, 8, testBf));
        String msg = "L'index de début doit être supérieure ou égale à 0.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("End index should be greater 0")
    void EndIndexShouldBeGreaterThan0() {
        Assertions.assertDoesNotThrow(() -> new SelectionImpl(0, 0, testBf), "L'index de fin doit être supérieure ou égale à 0.");
    }

    @Test
    @DisplayName("End index can't be greater than buffer length")
    void selectionLengthShouldLesserOrEqualToBufferLength2() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> new SelectionImpl(2, 73, testBf));
        String msg = "L'index de fin est hors du buffer.";
        assert (msg.equals(exception.getMessage()));
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
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setBeginIndex(-3));
        String msg = "L'index de début doit être supérieure ou égale à 0.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a begin index greater than the end index")
    void setBeginIndex2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setBeginIndex(39));
        String msg = "L'index de début doit être inférieure ou égale à l'index de fin.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index greater than the buffer length")
    void setBeginIndex3() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sp.setBeginIndex(73));
        String msg = "L'index de début est hors du buffer.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index lower than 0")
    void setEndIndex1() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setEndIndex(-3));
        String msg = "L'index de fin doit être supérieure ou égale à 0.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index lesser than the begin index")
    void setEndIndex2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setEndIndex(1));
        String msg = "L'index de fin doit être supérieure ou égale à l'index de début.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index greater than the buffer length")
    void setEndIndex3() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sp.setEndIndex(105));
        String msg = "L'index de fin est hors du buffer.";
        assert (msg.equals(exception.getMessage()));
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
}
