package com.example.demo.Calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorOperationsAdvanced implements CalculatorService{

    @Override
    public Double calculate(String operation, double... numbers) {
        return switch (operation) {
            case "add" -> {
                double result = 0;
                for (double number : numbers) {
                    result += number;
                }
                yield result;
            }
            case "subtract" -> {
                double result = 0;
                for (double number : numbers) {
                    result -= number;
                }
                yield result;
            }
            case "multiply" -> {
                double result = 0;
                for (double number : numbers) {
                    result *= number;
                }
                yield result;
            }
            case "divide" -> {
                double result = 0;
                for (double number : numbers) {
                    result /= number;
                }
                yield result;
            }
            default -> throw new IllegalArgumentException();
        };
    }
}
