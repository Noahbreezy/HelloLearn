package com.example.demo.Calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/calculator")
public class CalculatorAPI {

    private final Calculator calculator;

    public CalculatorAPI(Calculator calculator) {
        this.calculator = calculator;
    }

    private Double calculate(String operation, double... numbers) {
        return calculator.calculate(operation, numbers);
    }

    @GetMapping("/info")
    public String calculator() {
        return "Hello you can use /add, /subtract, /multiply, /divide as simple calculator endpoints by providing 2 numbers. If you provide more numbers we will use the advanced calculator.";
    }

    @GetMapping("/add")
    public String add(@RequestParam double[] numbers) {
        return Objects.requireNonNull(calculate("add", numbers)).toString();
    }

    @GetMapping("/subtract")
    public String subtract(@RequestParam double[] numbers) {
        return Objects.requireNonNull(calculate("subtract", numbers)).toString();
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam double[] numbers) {
        return Objects.requireNonNull(calculate("multiply", numbers)).toString();
    }

    @GetMapping("/divide")
    public String divide(@RequestParam double[] numbers) {
        String result = Objects.requireNonNull(calculate("divide", numbers)).toString();
        if (result == null) {
            result = "invalid divide";
        }
        return result;
    }



}
