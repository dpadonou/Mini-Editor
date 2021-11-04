package main.test.java.istic.aco.editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.EngineImpl;
import main.java.istic.aco.editor.Selection;
import main.java.istic.aco.editor.SelectionImpl;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EngineImplTest for Engine test
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn Padonou
 */
public class EngineImplTest {

    private Engine engine;
    private Engine engine2;
    private StringBuilder stringBuilder;
    private Selection selection;

    public EngineImplTest() {
    }

    @BeforeEach
    void setUp() {
        this.engine = new EngineImpl();
        this.stringBuilder = new StringBuilder("Un buffer de string dans les tests.");
        this.selection = new SelectionImpl(3, 20, stringBuilder);
        engine2 = new EngineImpl(stringBuilder, selection);
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
    @DisplayName("Should return the buffer of the given engine")
    void getBuffer() {
        assertEquals(this.stringBuilder, this.engine2.getBuffer());
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
    void canSetNullStringToInsert() {
        //engine2.setSelection(new SelectionImpl(3, 19, stringBuilder));
        assertThrows(IllegalArgumentException.class, () -> engine2.insert(null));
    }

    @Test
    @DisplayName("Clipboard should be null if no cut or copy hasn't be done.")
    void getClipboardContentsAfterInstanciation() {
        assertNull(engine.getClipboardContents());
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
        assertNull(engine2.getClipboardContents());
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
}
