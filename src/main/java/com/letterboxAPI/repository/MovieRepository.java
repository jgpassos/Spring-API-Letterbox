package com.letterboxAPI.repository;

import com.letterboxAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> { }
