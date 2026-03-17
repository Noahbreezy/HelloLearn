
package com.example.demo.Calculator;

import com.example.demo.Hello.CalculatorOperationsMultiply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorAPITest {

    private Calculator calculatorMock;
    private CalculatorAPI controller;

    @BeforeEach
    public void setup() {
        calculatorMock = mock(Calculator.class);
        controller = new CalculatorAPI(calculatorMock);
    }

    private Double calculate(String operation, double[] numbers) {
        return calculatorMock.calculate(operation, numbers);
    }

    @Test
    public void testAdd() {
        double[] numbers = new double[]{1.2, 2.8, 3.0};
        when(calculatorMock.calculate("add", numbers)).thenReturn(7.0);


        String result = controller.add(numbers);
        assertEquals("7.0", result);
        verify(calculatorMock).calculate("add", numbers);
    }

    @Test
    public void testSubtract() {
        double[] numbers = new double[]{1.2, 2.8, 3.0};
        when(calculatorMock.calculate("subtract", numbers)).thenReturn(-4.6);

        String result = controller.subtract(numbers);

        assertEquals("-4.6", result);
        verify(calculatorMock).calculate("subtract", numbers);
    }

    @Test
    @DisplayName("Make sure avdanced.calculate is called.")
    public void testCalculate() {

        CalculatorService simple = mock(CalculatorService.class);
        CalculatorService advanced = mock(CalculatorService.class);
        CalculatorService multiply = mock(CalculatorService.class);

        Calculator calculator = new Calculator(simple, advanced, multiply);

        double[] numbers = new double[]{1.2, 2.8, 3.0};

        when(advanced.calculate("add", numbers)).thenReturn(7.0);

        Double result = calculator.calculate("add", numbers);

        verify(advanced).calculate("add", numbers);
        verify(simple, never()).calculate(eq("add"), any());
        assert result.equals(7.0); // better not used, not junit
        assertEquals(7.0, result);
    }

    @Test
    @DisplayName("Make sure the simple.calculate version is called correctly")
    public void testSimpleCalculate() {
        CalculatorService simple = mock(CalculatorService.class);
        CalculatorService advanced = mock(CalculatorService.class);
        CalculatorService multiply = mock(CalculatorService.class);

        Calculator calculator = new Calculator(simple, advanced, multiply);

        double[] numbers = new double[]{1.2, 2.8};

        when(simple.calculate("add", numbers)).thenReturn(4.0);

        Double result = calculator.calculate("add", numbers);

        verify(simple).calculate("add", numbers);
        verify(advanced, never()).calculate(anyString(), any(double[].class));

        assertEquals(4.0, result);
    }
}
