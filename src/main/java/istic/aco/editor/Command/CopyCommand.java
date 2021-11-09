package istic.aco.editor.Command;

import istic.aco.editor.Engine;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Recorder.Record;

/**
 * Concrete Command, copyCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */

public class CopyCommand implements Command {
    private Engine engine;
    private Record recorder;

    /**
     * @param engine   The Receiver where are the functions
     * @param recorder The recorder for record the command
     */

    public CopyCommand(Engine engine, Record recorder) {
        super();
        if (test(engine, recorder)) {
            this.engine = engine;
            this.recorder = recorder;
        }
    }

    /**
     * Call the copyText method of the engine
     * save this command in the recorder
     */
    @Override
    public void execute() {
        engine.copySelectedText();
        recorder.save(this);
    }

    @Override
    public Memento save() {
        return null;
    }

    @Override
    public void restore(Memento m) {

    }

    /**
     * Lift an error if the parameters are null and send true if not.
     *
     * @param engine
     * @param recorder
     * @return
     * @throws NullPointerException if the method parameters are null
     */
    private boolean test(Engine engine, Record recorder) throws NullPointerException {
        if (engine.equals(null) || recorder.equals(null)) {
            throw new NullPointerException("Vous devez passer des parametres non nuls.");
        } else {
            return true;
        }
    }

    public Engine getEngine() {
        return engine;
    }

}
