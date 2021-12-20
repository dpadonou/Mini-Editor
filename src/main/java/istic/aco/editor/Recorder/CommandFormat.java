package main.java.istic.aco.editor.Recorder;

import java.util.Optional;

import main.java.istic.aco.editor.Command.CommandOriginator;
import main.java.istic.aco.editor.Memento.Memento;

/**
 * Data structure to save in recorder,CommandFormat
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donn� Padonou
 */
public class CommandFormat {
    private CommandOriginator command;
    private Optional<Memento> memento;

    /**
     * @param command, a command
     * @param memento, a memento
     */
    public CommandFormat(CommandOriginator command, Optional<Memento> memento) {
        super();
        if (test(command)) {
            this.command = command;
            this.memento = memento;
        }
    }

    /**
     * @return the command
     */
    public CommandOriginator getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(CommandOriginator command) {
        this.command = command;
    }

    /**
     * @return the memento
     */
    public Memento getMemento() {
        return memento.get();
    }

    /**
     * @param memento the memento to set
     */
    public void setMemento(Optional<Memento> memento) {
        this.memento = memento;
    }

    /**
     * Test the constructor parameter
     *
     * @param command a command
     * @return true if the parameter is good
     * @throws NullPointerException if the parameter is null
     */
    public boolean test(CommandOriginator command) throws NullPointerException {
        if (command == null) {
            throw new NullPointerException("Vous devez passer des param�tres non nul");
        } else {
            return true;
        }
    }


}
