package com.example.demo.Error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Error {

    @GetMapping("/error")
    public String error() {
        return "Oops, something went wrong with the calculator";
    }
}
