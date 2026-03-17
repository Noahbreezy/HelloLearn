package com.example.demo.Calculator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Calculator {

    private final CalculatorService simple;
    private final CalculatorService advanced;
    private final CalculatorService multiply;

    public Calculator(@Qualifier("calculatorOperationsSimple") CalculatorService simple, @Qualifier("calculatorOperationsAdvanced") CalculatorService advanced, @Qualifier("calculatorOperationsMultiply") CalculatorService multiply) {
        this.simple = simple;
        this.advanced = advanced;
        this.multiply = multiply;
    }

    public Double calculate(String givenString, double... numbers) {
        if (numbers.length == 2) {
            return simple.calculate(givenString, numbers);
        } else if (numbers.length > 2) {
            return advanced.calculate(givenString, numbers);
        } else  {
            throw new IllegalArgumentException();
        }
    }
    
    public Double multiply6(String givenString, double... numbers) {
        return multiply.calculate(givenString, numbers);
    }
}
