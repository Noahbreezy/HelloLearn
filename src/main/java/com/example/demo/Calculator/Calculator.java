package com.example.demo.Calculator;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    private final CalculatorOperationsSimple simple;
    private final CalculatorOperationsAdvanced advanced;

    public Calculator(CalculatorOperationsSimple simple, CalculatorOperationsAdvanced advanced) {
        this.simple = simple;
        this.advanced = advanced;
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
}
