package com.letterboxAPI.controller;

import com.letterboxAPI.model.Movie;
import com.letterboxAPI.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;

    @Test
    void testAddMovie() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");

        when(movieService.addMovie(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/api/movies")
                .content("{\"title\":\"Test Movie\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Movie"));

        verify(movieService, times(1)).addMovie(any(Movie.class));
    }
    @Test
    void testGetAllMovies() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Test movie");

        when(movieService.getAllMovies()).thenReturn(Collections.singletonList(movie));

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Movie"));

        verify(movieService, times(1)).getAllMovies();
    }
}
