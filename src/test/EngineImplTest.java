package test;

import istic.aco.editor.Engine;
import istic.aco.editor.EngineImpl;
import istic.aco.editor.Selection;
import istic.aco.editor.SelectionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        this.stringBuilder = new StringBuilder("Un string de buffer dans les tests.");
        this.selection = new SelectionImpl(3, 20, stringBuilder);
        engine2 = new EngineImpl(stringBuilder, selection);
    }

    private void todo() {
        fail("Unimplemented test");
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void bufferMustBeEmptyAfterInitialisation() {
        assertEquals("", this.engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        assertEquals("Un string de buffer dans les tests.", this.engine2.getBufferContents());
    }

    @Test
    @DisplayName("Should return the buffer of the given engine")
    void getBuffer(){
        assertEquals(this.stringBuilder, this.engine2.getBuffer());
    }

    @Test
    void getClipboardContents() {
        this.todo();
    }

    @Test
    void cutSelectedText() {
        this.todo();
    }

    @Test
    void copySelectedText() {
        this.todo();
    }

    @Test
    void pasteClipboard() {
        this.todo();
    }
}
