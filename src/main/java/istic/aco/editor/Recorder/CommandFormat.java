package main.java.istic.aco.editor.Recorder;

import main.java.istic.aco.editor.Command.Command;
import main.java.istic.aco.editor.Memento.Memento;

public class CommandFormat {
      private Command command;
      private Memento memento;
	/**
	 * @param command
	 * @param memento
	 */
	public CommandFormat(Command command, Memento memento) {
		super();
		this.command = command;
		this.memento = memento;
	}
	/**
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}
	/**
	 * @param command the command to set
	 */
	public void setCommand(Command command) {
		this.command = command;
	}
	/**
	 * @return the memento
	 */
	public Memento getMemento() {
		return memento;
	}
	/**
	 * @param memento the memento to set
	 */
	public void setMemento(Memento memento) {
		this.memento = memento;
	}
	
}
