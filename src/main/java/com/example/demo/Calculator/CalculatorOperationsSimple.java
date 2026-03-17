package com.example.demo.Calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CalculatorOperationsSimple implements CalculatorService{

    @Override
    public Double calculate(String operation, double... numbers) {

        if (numbers.length != 2) {
            throw new IllegalArgumentException("Simple calculator only supports exactly 2 numbers");
        }

        double a = numbers[0];
        double b = numbers[1];

        return switch (operation) {
            case "add" -> a + b;
            case "subtract" -> a - b;
            case "multiply" -> a * b;
            case "divide" -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield a / b;
            }
            case null -> null;
            default -> null;
        };
    }
}
