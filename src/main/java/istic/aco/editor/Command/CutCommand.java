package main.java.istic.aco.editor.Command;

import java.util.Optional;

import main.java.istic.aco.editor.EngineOriginator;
import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.Recorder.Recorder;
import main.java.istic.aco.editor.Recorder.UndoManager;

/**
 * Concrete Command, cutCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class CutCommand implements CommandOriginator {
    private EngineOriginator engine;
    private Recorder recorder;
    private UndoManager undoManager;

    /**
     * @param engine   The Receiver where are the functions
     * @param recorder The recorder for record the command
     * @param undoManager The manager for manage the engine state
     */
    public CutCommand(EngineOriginator engine, Recorder recorder, UndoManager undoManager) {
        if (test(engine, recorder, undoManager)) {
            this.engine = engine;
            this.recorder = recorder;
            this.undoManager = undoManager;
        }

    }

    /**
     * call the cutText method of the engine
     * save this command in the recorder
     */
    @Override
    public void execute() {
        undoManager.save(engine.save());
        this.engine.cutSelectedText();
        recorder.save(this);
    }

    /**
     * Lift an error if the parameters are null and send true if not.
     * @param engine the receiver who contains the cut function
     * @param recorder the recorder for store the command
     * @param undoManager the manager for manage the engine state
     * @return true if all parameters are good
     * @throws NullPointerException if the method parameters are null
     */
    public boolean test(EngineOriginator engine, Recorder recorder, UndoManager undoManager) throws NullPointerException {
        if (engine == null || recorder == null || undoManager == null) {
            throw new NullPointerException("Vous devez passer des param�tres non nul");
        } else {
            return true;
        }
    }

    @Override
    public Optional<Memento> save() {
        return Optional.empty();
    }

    @Override
    public void restore(Memento m) throws IllegalArgumentException {

    }

}
