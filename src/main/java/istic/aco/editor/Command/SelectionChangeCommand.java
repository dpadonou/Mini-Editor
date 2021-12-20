package istic.aco.editor.Command;

import istic.aco.editor.Engine;
import istic.aco.editor.EngineOriginator;
import istic.aco.editor.Invoker.Invoker;
import istic.aco.editor.Memento.Memento;
import istic.aco.editor.Memento.SelectionChangeMemento;
import istic.aco.editor.Recorder.Recorder;
import istic.aco.editor.Recorder.UndoManager;

import java.util.Optional;

/**
 * Concrete Command, selectionChangeCommand
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class SelectionChangeCommand implements CommandOriginator {

    private EngineOriginator engine;
    private Invoker inv;
    private Recorder recorder;
    private UndoManager undoManager;
    private int beginIndex = 0;
    private int endIndex = 0;

    /**
     * @param engine      The receiver where are the functions
     * @param inv         The invoker who call this concrete command
     * @param recorder    for record this command
     * @param undoManager .
     */
    public SelectionChangeCommand(EngineOriginator engine, Invoker inv, Recorder recorder, UndoManager undoManager) {
        if (test(engine, inv, recorder, undoManager)) {
            this.engine = engine;
            this.inv = inv;
            this.recorder = recorder;
            this.undoManager = undoManager;
        }
    }

    /**
     * Get the end and the begining index through the invoker
     * set at the selection new begin and end index
     * Save this command in the recorder
     */
    @Override
    public void execute() {
        if (this.beginIndex == 0 && this.endIndex == 0) {
            this.beginIndex = inv.getBeginIndex();
            this.endIndex = inv.getEndIndex();
            recorder.save(this);
        }
        engine.getSelection().setBeginIndex(this.beginIndex);
        engine.getSelection().setEndIndex(this.endIndex);

        this.beginIndex = 0;
        this.endIndex = 0;

        undoManager.save(engine.save());
    }

    @Override
    public Optional<Memento> save() {
        return Optional.of(new SelectionChangeMemento(this.beginIndex, this.endIndex));
    }

    /**
     * Set in this object properties the memento values
     */
    @Override
    public void restore(Memento m) {
        if (m == null) {
            throw new IllegalArgumentException("Vous devez mpasser en param�tre un memento non null");
        } else {
            Object[] t = m.getParameter();
            this.beginIndex = Integer.parseInt(t[0].toString());
            this.endIndex = Integer.parseInt(t[1].toString());
        }
    }

    /**
     * Lift an error if the parameters are null and send true if not.
     *
     * @param engine
     * @param recorder
     * @param undoManager
     * @return
     * @throws NullPointerException if the method parameters are null
     */
    public boolean test(Engine engine, Invoker invoker, Recorder recorder, UndoManager undoManager) throws NullPointerException {
        if (engine == null || recorder == null || invoker == null || undoManager == null) {
            throw new IllegalArgumentException("Vous devez passer des param�tres non nul");
        } else {
            return true;
        }
    }

}
