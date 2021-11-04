package main.test.java.istic.aco.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.java.istic.aco.editor.SelectionImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * SelectionImplTest for Selection test
 * @author Arnauld Djedjemel
 * @author Dieu-Donn Padonou
 *
 */
class SelectionImplTest {

    StringBuilder testBf;
    SelectionImpl sp;

    @BeforeEach
    void initializer() {
        testBf = new StringBuilder("Buffer créer pour faire des test sur les getters et getters setters.");
        sp = new SelectionImpl(2, 12, testBf);
    }

    @Test
    @DisplayName("Begin index should be lower than end index")
    void beginIndexShouldBeLowerThanEndIndex() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SelectionImpl(27, 8, testBf));
        String msg = "L'index de fin doit être supérieure ou égale à l'index de début.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Begin index should be greater or equals to 0")
    void beginIndexShouldBeGreaterThan0() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SelectionImpl(-6, 8, testBf));
        String msg = "L'index de début doit être supérieure ou égale à 0.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("End index should be greater 0")
    void EndIndexShouldBeGreaterThan0() {
        Assertions.assertDoesNotThrow(() -> new SelectionImpl(0, 0, testBf), "L'index de fin doit être supérieure ou égale à 0.");
    }

    @Test
    @DisplayName("End index can't be greater than buffer length")
    void selectionLengthShouldLesserOrEqualToBufferLength2() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> new SelectionImpl(2, 73, testBf));
        String msg = "L'index de fin est hors du buffer.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("getBeginIndex should return the selection's begin index")
    void getBeginIndex() {
        assert (2 == sp.getBeginIndex());
    }

    @Test
    @DisplayName("getEndIndex should return the selection's end index")
    void getEndIndex() {
        assert (12 == sp.getEndIndex());
    }

    @Test
    @DisplayName("getBufferBeginIndex should always return 0")
    void getBufferBeginIndex() {
        assert (0 == sp.getBufferBeginIndex());
    }

    @Test
    @DisplayName("getBuffer should return the given selection buffer")
    void getBuffer() {
        assertEquals(testBf, sp.getBuffer());
    }

    @Test
    @DisplayName("getBufferEndIndex should return the buffer's end index")
    void getBufferEndIndex() {
        assert (67 == sp.getBufferEndIndex());
    }

    @Test
    @DisplayName("Can't set a begin index lower than 0")
    void setBeginIndex1() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setBeginIndex(-3));
        String msg = "L'index de début doit être supérieure ou égale à 0.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a begin index greater than the end index")
    void setBeginIndex2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setBeginIndex(39));
        String msg = "L'index de début doit être inférieure ou égale à l'index de fin.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index greater than the buffer length")
    void setBeginIndex3() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sp.setBeginIndex(73));
        String msg = "L'index de début est hors du buffer.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index lower than 0")
    void setEndIndex1() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setEndIndex(-3));
        String msg = "L'index de fin doit être supérieure ou égale à 0.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index lesser than the begin index")
    void setEndIndex2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sp.setEndIndex(1));
        String msg = "L'index de fin doit être supérieure ou égale à l'index de début.";
        assert (msg.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Can't set a end index greater than the buffer length")
    void setEndIndex3() {
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> sp.setEndIndex(105));
        String msg = "L'index de fin est hors du buffer.";
        assert (msg.equals(exception.getMessage()));
    }
}
