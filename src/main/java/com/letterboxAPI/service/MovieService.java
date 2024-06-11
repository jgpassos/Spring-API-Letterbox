package com.letterboxAPI.service;

import com.letterboxAPI.model.Movie;
import com.letterboxAPI.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> getMovie(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found."));

        movie.setTitle(movieDetails.getTitle());
        movie.setDirector(movieDetails.getDirector());
        movie.setDescription(movieDetails.getDescription());
        movie.setDuration(movieDetails.getDuration());
        movie.setRateLetterbox(movieDetails.getRateLetterbox());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found."));
        movieRepository.deleteById(id);
    }

    public Movie getLongestMovie() {
        return movieRepository.findAll().stream()
                .max(Comparator.comparingInt(Movie::getDuration))
                .orElseThrow(() -> new RuntimeException("No movies found."));
    }

    public Movie getHighestRatedMovie() {
        return movieRepository.findAll().stream()
                .max(Comparator.comparingDouble(Movie::getRateLetterbox))
                .orElseThrow(() -> new RuntimeException("No movies found"));
    }

    public Movie getLowestRatedMovie() {
        return movieRepository.findAll().stream()
                .min(Comparator.comparingDouble(Movie::getRateLetterbox))
                .orElseThrow(() -> new RuntimeException("No movies found"));
    }

    public List<String> getAllTitles() {
        return movieRepository.findAll().stream()
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }

    public List<String> getAllDirectors() {
        return movieRepository.findAll().stream()
                .map(Movie::getDirector)
                .collect(Collectors.toList());
    }

    public int getTotalDuractionOfRegistredMovies() {
        return movieRepository.findAll().stream()
                .mapToInt(Movie::getDuration)
                .sum();
    }
}
