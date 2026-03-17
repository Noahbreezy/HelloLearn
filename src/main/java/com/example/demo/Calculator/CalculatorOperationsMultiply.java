package com.example.demo.Calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorOperationsMultiply implements CalculatorService{

    @Override
    public Double calculate(String operation, double... numbers) {
        double result = 0;
        for (double number : numbers) {
            result *= number;
        }
        return result;
    }
}
