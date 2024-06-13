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

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("Should update an existing movie.")
    void testUpdateMovie() {
        Movie existingMovie = new Movie();
        existingMovie.setId(1L);
        existingMovie.setTitle("Old Title");

        Movie updatedDetails = new Movie();
        updatedDetails.setTitle("New Title");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(any(Movie.class))).thenReturn(existingMovie);

        Movie updatedMovie = movieService.updateMovie(1L, updatedDetails);

        assertEquals("New Title", updatedMovie.getTitle());
        verify(movieRepository, times(1)).findById(1L);
        verify(movieRepository, times(1)).save(existingMovie);
    }
    @Test
    @DisplayName("Should throw Exception when update a movie is not allowed because the movie is not found.")
    void testUpdateMovieNotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        Movie updatedDetails = new Movie();

        assertThrows(RuntimeException.class, () -> {
            movieService.updateMovie(1L, updatedDetails);
        });

        verify(movieRepository, times(1)).findById(1L);
        verify(movieRepository, never()).save(any(Movie.class));
    }
    @Test
    @DisplayName("Should delete an existing movie.")
    void testDeleteMovie() {
        Movie movie = new Movie();
        movie.setId(1L);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        movieService.deleteMovie(1L);

        verify(movieRepository, times(1)).findById(1L);
        verify(movieRepository, times(1)).delete(movie);
    }
    @Test
    @DisplayName("Should throw Exception when delete a movie is not allowed because the movie is not found.")
    void testDeleteMovieNotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            movieService.deleteMovie(1L);
        });

        verify(movieRepository, times(1)).findById(1L);
        verify(movieRepository, never()).delete(any(Movie.class));
    }
}