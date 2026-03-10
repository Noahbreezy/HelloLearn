package com.example.demo.Hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
}
