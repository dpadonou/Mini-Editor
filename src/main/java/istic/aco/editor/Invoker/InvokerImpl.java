package istic.aco.editor.Invoker;

import istic.aco.editor.Command.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Invoker interface implementation, InvokerImpl
 *
 * @author Arnauld Djedjemel
 * @author Dieu-Donné Padonou
 */
public class InvokerImpl implements Invoker {
    private final Map<String, Command> commands;
    private String s;
    private int beginIndex;
    private int endIndex;

    /**
     *
     */
    public InvokerImpl() {
        commands = new HashMap<>();
    }

    /**
     * @return s the text to insert
     */
    @Override
    public String getS() {
        return s;
    }

    /**
     * @param s the s to insert
     */
    @Override
    public void setS(String s) {
        if (s == null) {
            throw new NullPointerException("Vous devez passer une chaine non vide");
        } else {
            this.s = s;
        }
    }

    /**
     * @return the beginIndex
     */
    @Override
    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * @param beginIndex the beginIndex to set
     */
    @Override
    public void setBeginIndex(int beginIndex) {
        if (beginIndex < 0) {
            throw new IllegalArgumentException("l'index ne doit pas etre negatif");
        } else {
            this.beginIndex = beginIndex;
        }
    }

    /**
     * @return the endIndex
     */
    @Override
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * @param endIndex the endIndex to set
     */
    @Override
    public void setEndIndex(int endIndex) {
        if (endIndex < 0 || endIndex < beginIndex) {
            throw new IllegalArgumentException("l'index ne doit pas etre negatif");
        } else {
            this.endIndex = endIndex;
        }
    }

    /**
     * the user action to change selection
     */
    @Override
    public void selectionChange() {
        if (commands.get("selection") != null) {
            commands.get("selection").execute();
        }
    }

    /**
     * the user action to cut text
     */
    @Override
    public void cutText() {
        if (commands.get("cut") != null) {
            commands.get("cut").execute();
        }
    }

    /**
     * the user action to copy text
     */
    @Override
    public void copyText() {
        if (commands.get("copy") != null) {
            commands.get("copy").execute();
        }
    }

    /**
     * the user action to paste the clipboard content's
     */
    @Override
    public void pasteClipboard() {
        if (commands.get("paste") != null) {
            commands.get("paste").execute();
        }
    }

    /**
     * the user action to insert text
     */
    @Override
    public void insert() {
        if (commands.get("insert") != null) {
            commands.get("insert").execute();
        }
    }

    /**
     * Replay the last command
     */
    @Override
    public void replay() {
        if (commands.get("replay") != null) {
            commands.get("replay").execute();
        }
    }

    @Override
    public void setCommand(String s, Command command) throws IllegalArgumentException {
        if (command == null || s.isEmpty()) {
            throw new IllegalArgumentException("Vous devez passé des pramètres valides.");
        } else {
            commands.put(s, command);
        }
    }

}
