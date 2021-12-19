package istic.aco.editor.Command;

import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Recorder.Recorder;

import java.util.Optional;

/**
 * Concrete Command, Replay
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class Replay implements CommandOriginator {
    private Recorder recorder;

    /**
     * @param recorder The recorder who contains the command for replaying
     */
    public Replay(Recorder recorder) {
        super();
        if (test(recorder)) {
            this.recorder = recorder;
        }
    }

    /**
     * Call the recorder replay method
     */
    @Override
    public void execute() {
        this.recorder.replay();
    }

    /**
     * Lift an error if the parameters are null and send true if not.
     *
     * @param recorder
     * @return
     * @throws NullPointerException if the method parameters are null
     */
    public boolean test(Recorder recorder) throws NullPointerException {
        if (recorder == null) {
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
