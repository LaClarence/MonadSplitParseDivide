package dev.lego.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void shouldAnswerWithTrue() {
        assertEquals(42.0, App.splitParseDivide("126,3").getValue());
    }
}
