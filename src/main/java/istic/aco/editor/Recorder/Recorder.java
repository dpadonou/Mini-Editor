package istic.aco.editor.Recorder;

import istic.aco.editor.Command.Command;
import istic.aco.editor.Memento.Memento;

import java.util.ArrayList;

/**
 * Record interface implementation, Recorder
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */

public class Recorder implements Record {

    private final ArrayList<Command> commands = new ArrayList<Command>();
    private Memento memento;

    /**
     * Save one command in the receiver
     *
     * @param c the command who wants to save in the recorder
     */
    @Override
    public void save(Command c) {
        if (c == null) {
            throw new NullPointerException("La commande passée doit etre non nulle.");
        } else {
            commands.add(c);
            this.memento = c.save();
        }
    }

    /**
     * Start the recorder
     */
    @Override
    public void start() {

    }

    /**
     * Stop the recorder
     */
    @Override
    public void stop() {
    }

    /**
     * First Restore the state of the command
     * Replay the last command save in the recorder
     */
    @Override
    public void replay() {
        this.commands.get(this.commands.size() - 1).restore(this.memento);
        this.commands.get(this.commands.size() - 1).execute();
    }

}
