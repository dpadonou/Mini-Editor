package istic.aco.editor.Recorder;

import istic.aco.editor.Command.CommandOriginator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Recorder interface implementation, RecorderImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */

public class RecorderImpl implements Recorder {
    private final Deque<CommandFormat> commands = new ArrayDeque<CommandFormat>();
    private boolean power = false;

    /**
     * Save one command in the receiver
     *
     * @param c the command who wants to save in the recorder
     */
    @Override
    public void save(CommandOriginator c) {
        if (power) {
            if (c == null) {
                throw new NullPointerException("La commande passée doit etre non nul");
            } else {
                commands.addFirst(new CommandFormat(c, c.save()));
            }
        }
    }

    /**
     * Start the recorder
     */
    @Override
    public void start() {
        power = true;
    }

    /**
     * Stop the recorder
     */
    @Override
    public void stop() {
        power = false;
    }

    /**
     * First Restore the state of the command
     * Replay the command save in the recorder
     */
    @Override
    public void replay() {
        for (CommandFormat c : commands) {
            c.getCommand().restore(c.getMemento());
            c.getCommand().execute();
        }
        commands.clear();
    }

}
