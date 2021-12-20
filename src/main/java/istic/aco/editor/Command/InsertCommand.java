package istic.aco.editor.Command;

import istic.aco.editor.EngineOriginator;
import istic.aco.editor.Invoker.Invoker;
import istic.aco.editor.Memento.InsertMemento;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Recorder.UndoManager;

import java.util.Optional;

/**
 * Concrete Command, insertCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class InsertCommand implements CommandOriginator {
    private EngineOriginator engine;
    private Invoker inv;
    private Recorder recorder;
    private UndoManager undoManager;
    private String s = "";

    /**
     * @param engine      The Receiver where are the functions
     * @param inv         The invoker who call this concrete command
     * @param recorder    The recorder for record the command
     * @param undoManager The memento for store this command parameters
     */
    public InsertCommand(EngineOriginator engine, Invoker inv, Recorder recorder, UndoManager undoManager) {
        if (test(engine, inv, recorder, undoManager)) {
            this.engine = engine;
            this.inv = inv;
            this.recorder = recorder;
            this.undoManager = undoManager;
        }
    }

    /**
     * call the insert method of the engine
     * take the text to insert in the invoker who call this command
     * save the recorder in the recorder
     */
    @Override
    public void execute() {
        undoManager.save(engine.save());
        if (this.s.isEmpty()) {
            this.s = inv.getS();
            recorder.save(this);
        }
        engine.insert(this.s);
        s = "";
    }

    @Override
    public Optional<Memento> save() {
        return Optional.of(new InsertMemento(this.s));
    }

    @Override
    public void restore(Memento m) {
        if (m == null) {
            throw new IllegalArgumentException("vous devez passer un objet memento non nul");
        } else {
            Object[] t = m.getParameter();
            this.s = t[0].toString();
        }
    }

    /**
     * Lift an error if the parameters are null and send true if not.
     *
     * @param engine
     * @param recorder
     * @param invoker
     * @param undoManager
     * @return
     * @throws NullPointerException if the method parameters are null
     */
    public boolean test(EngineOriginator engine, Invoker invoker, Recorder recorder, UndoManager undoManager) throws NullPointerException {
        if (engine == null || recorder == null || invoker == null || undoManager == null) {
            throw new NullPointerException("Vous devez passer des param�tres non nul");
        } else {
            return true;
        }
    }

}
