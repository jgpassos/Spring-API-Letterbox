package com.letterboxAPI.service;

import com.letterboxAPI.model.Movie;
import com.letterboxAPI.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should add a new Movie successfully.")
    void addMovie() {
        Movie movie = new Movie();
        movie.setTitle("Teste Movie");

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        Movie createdMovie = movieService.addMovie(movie);

        assertEquals("Test Movie", createdMovie.getTitle());
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    @DisplayName("Should trow Exception when add a new movie is not allowed.")
    void addMovieCase2() {
    }

    @Test
    @DisplayName("Should return a movie specified by id.")
    void getMovie() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Teste Movie");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Optional<Movie> foundMovie = movieService.getMovie(1L);

        assertEquals("Test Movie", foundMovie.get().getTitle());
        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }
}