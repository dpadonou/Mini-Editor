package istic.aco.editor.invoker;

import istic.aco.editor.Command.CopyCommand;
import istic.aco.editor.Command.CutCommand;
import istic.aco.editor.Invoker.InvokerImpl;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Selection;
import istic.aco.editor.SelectionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InvokerImplTest {

    InvokerImpl invoker;
    CopyCommand copyCommand;
    CutCommand cutCommand;

    Recorder recorder;

    @BeforeEach
    @Test
    void setCopyCommand() {
        StringBuilder stringBuilder = new StringBuilder("Simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        Selection selection = new SelectionImpl(21, 104, stringBuilder);
        //Selection cutSelection = new SelectionImpl(60, 229, stringBuilder);

        //engine = new EngineImpl(stringBuilder, selection);

        recorder = new Recorder();

        //copyCommand = new CopyCommand(engine, recorder);
        //cutCommand = new CutCommand(engine, recorder);

        invoker = new InvokerImpl();
    }

    @Test
    @DisplayName("copy should change the engine clipboard and let the buffer unchanged")
    void copyShouldChangeTheEngineClipboardAndLetTheBufferUnchanged() {
        invoker.setCopyTextCommand(copyCommand);


    }

}
