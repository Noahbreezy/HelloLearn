package com.example.demo.Calculator;

public class CalculatorOperations {

    public static Double calculate(Double a, Double b, String operation) {
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
