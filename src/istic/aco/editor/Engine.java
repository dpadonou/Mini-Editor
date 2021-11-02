//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package istic.aco.editor;

public interface Engine {
    Selection getSelection();

    String getBufferContents();

    String getClipboardContents();

    void cutSelectedText();

    void copySelectedText();

    void pasteClipboard();

    void insert(String var1);

    void delete();

    StringBuilder getBuffer();
}
