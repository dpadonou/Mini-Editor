package istic.aco.editor;

public interface Selection {

    int getBeginIndex();

    int getEndIndex();

    int getBufferBeginIndex();


    int getBufferEndIndex();

    void setBeginIndex(int var1);

    void setEndIndex(int var1);

    StringBuilder getBuffer();
}
