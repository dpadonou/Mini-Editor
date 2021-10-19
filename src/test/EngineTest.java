//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package test;

import istic.aco.editor.Engine;
import istic.aco.editor.EngineImpl;
import istic.aco.editor.Selection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EngineTest {
    private Engine engine;

    public EngineTest() {
    }

    @BeforeEach
    void setUp() {
        this.engine = new EngineImpl();
    }

    private void todo() {
        Assertions.fail("Unimplemented test");
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = this.engine.getSelection();
        Assertions.assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        Assertions.assertEquals("", this.engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        this.todo();
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
