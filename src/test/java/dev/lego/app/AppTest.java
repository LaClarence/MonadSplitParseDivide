package dev.lego.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class AppTest {

    @Test
    void shouldComputeInput() {
        assertEquals(42.0, App.splitParseDivide("126,3").getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = { "126,3", "0,99" })
    void shouldSuccessWithExpectingInputs(String input) {
        assertTrue(App.splitParseDivide(input).isSuccess());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "126.3", "4,0", ",", "ABC", "12." })
    void shouldFailedWithUnexpectedInputs(String input) {
        assertTrue(App.splitParseDivide(input).isFailure());
    }
}
