package com.example.demo.Calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullSource;

import static com.example.demo.Calculator.CalculatorOperations.calculate;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorOperationsTest {

    @ParameterizedTest(name = "{0} -> {1} {0} {2} = {3} ")
    @CsvSource({
            "add, 5, 2, 7",
            "subtract, 5, 2, 3",
            "multiply, 6, 2, 12",
            "divide, 5, 2, 2.5"
    })
    @DisplayName("Calculator.calculate should correctly calculate. obviously...")
    public void calculate_testCorrectResults(
            String operation, Double a, Double b, Double expected
    ) {
        Double result = calculate(a, b, operation);
        assertEquals(expected, result, "Calculated result");
    }

    @Test
    @DisplayName("Calculator should throw a divideByZero exception when dividing by zero.")
    public void calculate_testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculate(5.0, 0.0, "divide"));
    }

    @Test
    @DisplayName("Calculator should display \"Division by zero\"")
    public void calculate_testDivisionByZeroMessage() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> calculate(5.0, 0.0, "divide"));

        assertEquals("Division by zero", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Noah", "ghjndbf", "root", "randomOperation", ""})
    @NullSource
    @DisplayName("")
    public void calculate_testInvalidOperation(String operation) {
        Double result = calculate(5.0, 0.0, operation);

        assertNull(result);
    }
}
