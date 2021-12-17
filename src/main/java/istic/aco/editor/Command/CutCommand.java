package main.java.istic.aco.editor.Command;

import java.util.Optional;

import main.java.istic.aco.editor.Engine;
import main.java.istic.aco.editor.Memento.Memento;
import main.java.istic.aco.editor.Recorder.Recorder;

/**
 * Concrete Command, cutCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class CutCommand implements Command {
    private Engine engine;
    private Recorder recorder;

    /**
     * @param engine   The Receiver where are the functions
     * @param recorder The recorder for record the command
     */
    public CutCommand(Engine engine, Recorder recorder) {
        if (test(engine, recorder)) {
            this.engine = engine;
            this.recorder = recorder;
        }

    }

    /**
     * call the cutText method of the engine
     * save this command in the recorder
     */
    @Override
    public void execute() {
        this.engine.cutSelectedText();
        recorder.save(this);
    }

    /**
     * Lift an error if the parameters are null and send true if not.
     *
     * @param engine
     * @param recorder
     * @return
     * @throws NullPointerException if the method parameters are null
     */
    public boolean test(Engine engine, Recorder recorder) throws NullPointerException {
        if (engine == null || recorder == null) {
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
        // TODO Auto-generated method stub

    }

}
