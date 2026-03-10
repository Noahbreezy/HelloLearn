package com.example.demo.Calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.Calculator.CalculatorOperations.calculate;

@RestController
@RequestMapping("/calculator")
public class CalculatorAPI {

    @GetMapping("/info")
    public String calculator() {
        return "Hello you can use /add, /subtract, /multiply, /divide as simple calculator endpoints by providing 2 numbers.";
    }

    @GetMapping("/add")
    public String add(@RequestParam Double a, @RequestParam Double b) {
        return calculate(a, b, "add").toString();
    }

    @GetMapping("/subtract")
    public String subtract(@RequestParam Double a, @RequestParam Double b) {
        return calculate(a, b, "subtract").toString();
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam Double a, @RequestParam Double b) {
        return calculate(a, b, "multiply").toString();
    }

    @GetMapping("/divide")
    public String divide(@RequestParam Double a, @RequestParam Double b) {
        String result = calculate(a, b, "divide").toString();
        if (result == null) {
            result = "invalid divide";
        }
        return result;
    }

    @GetMapping("/error")
    public String error() {
        return "Oops, something went wrong with the calculator";
    }
}
