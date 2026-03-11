package com.example.demo.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/")
    public String index() {
        return "Hello World, this will be a spring boot application";
    }


    @GetMapping("/countStringCharacters")
    public String countStringCharacters(String givenString) {
        int count = givenString.length();
        if (count >= 1) {
            System.out.printf("\n\"%s\" has %d characters.", givenString, count);
            return givenString + " " + "has " + count + " characters.";

        } else  {
            System.out.println("No string was given.");
            return "No string was given.";
        }
    }
//
//    @GetMapping("/error")
//    public String error() {
//        return "Oops, something went wrong with the calculator";
//    }
}
