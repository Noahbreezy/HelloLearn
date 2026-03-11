package com.example.demo.Hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void index_testCorrectResponse() throws Exception {
        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World, this will be a spring boot application")));
    }

    @Test
    void countStringCharacters_testIsLive() throws Exception {
        mvc.perform(get("/countStringCharacters").accept(MediaType.APPLICATION_JSON)
                .param("givenString", "Hello World, this will be a spring boot application"))
                .andExpect(status().isOk());
    }

    @Test
    void countStringCharacters_testCountIsCorrect() throws Exception {
        mvc.perform(get("/countStringCharacters").accept(MediaType.APPLICATION_JSON)
                .param("givenString", "Hello World"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World" + " " + "has " + 11 + " characters")));

        mvc.perform(get("/countStringCharacters").accept(MediaType.APPLICATION_JSON)
                .param("givenString", ""))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("No string was given.")));
    }

    @ParameterizedTest(name = "{0} has {1} characters.")
    @CsvSource({
            "Hello, Hello has 5 characters.",
            "Hello World, Hello World has 11 characters.",
            "Noah, Noah has 4 characters.",
            "Stagiair, Stagiair has 8 characters."
    })
    @DisplayName("Parameterised test to check whether the correct amount of characters are returned")
    public void countStringCharacters_testCorrectResults(String givenString, String expectedResult ) throws Exception {
        mvc.perform(get("/countStringCharacters").accept(MediaType.APPLICATION_JSON)
                .param("givenString", givenString))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedResult)));
    }

}
