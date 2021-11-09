package istic.aco.editor.invoker;

import istic.aco.editor.Command.CopyCommand;
import istic.aco.editor.Engine;
import istic.aco.editor.EngineImpl;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Selection;
import istic.aco.editor.SelectionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvokerTest {
    CopyCommand command;
    Engine engine;
    Recorder recorder;

    @BeforeEach
    @Test
    void setCopyCommand() {
        StringBuilder stringBuilder = new StringBuilder("simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        Selection selection = new SelectionImpl(20, 100, stringBuilder);
        engine = new EngineImpl(stringBuilder, selection);
        recorder = new Recorder();
        engine.copySelectedText();
        command = new CopyCommand(engine, recorder);
    }

    @Test
    @DisplayName("Calling execute() on CopyCommand object shoud copy the selection from the engine.")
    void executeOnCopyCommandObjectShoudCopyTheSelectionFromTheEngine() {
        String result = " the printing and typesetting industry. Lorem Ipsum has been the industry's stan";
        command.execute();
        assertEquals(result, (command.getEngine().getClipboardContents()));
    }

    @Test
    @DisplayName("Shouldn't be able to create a copyCommand objet with null engine.")
    void shouldnTBeAbleToCreateACopyCommandObjetWithNullArguments() {
        engine = null;
        assertThrows(NullPointerException.class, () -> command = new CopyCommand(engine, recorder));
    }

}
