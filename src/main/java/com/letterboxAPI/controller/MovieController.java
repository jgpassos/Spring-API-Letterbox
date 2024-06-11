package com.letterboxAPI.controller;

import com.letterboxAPI.model.Movie;
import com.letterboxAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovie(id)
                .orElseThrow(() -> new RuntimeException("Movie not found."));

        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = this.movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Movie updatedMovie = movieService.updateMovie(id, movieDetails);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/longestMovie")
    public ResponseEntity<Movie> getLongestMovie() {
        Movie longestMovie = movieService.getLongestMovie();
        return new ResponseEntity<>(longestMovie, HttpStatus.OK);
    }

    @GetMapping("/highestRatedMovie")
    public ResponseEntity<Movie> getHighestRatedMovie() {
        Movie highestRatedMovie = movieService.getHighestRatedMovie();
        return new ResponseEntity<>(highestRatedMovie, HttpStatus.OK);
    }

    @GetMapping("/lowesrRatedMovie")
    public ResponseEntity<Movie> lowestRatedMovie() {
        Movie lowestRatedMovie = movieService.getLowestRatedMovie();
        return new ResponseEntity<>(lowestRatedMovie, HttpStatus.OK);
    }

    @GetMapping("/titles")
    public ResponseEntity<List<String>> getAllTitles() {
        List<String> titles = movieService.getAllTitles();
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @GetMapping("/directors")
    public ResponseEntity<List<String>> getAllDirectors() {
        List<String> directors = movieService.getAllDirectors();
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @GetMapping("/totalDuration")
    public ResponseEntity<Integer> getTotalDuration() {
        int totalDuration = movieService.getTotalDuractionOfRegistredMovies();
        return new ResponseEntity<>(totalDuration, HttpStatus.OK);
    }
}
