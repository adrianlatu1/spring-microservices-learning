package com.watchlist.movieapp.repository;

import com.watchlist.movieapp.entity.Genre;
import com.watchlist.movieapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

     List<Movie> findByGenres(Genre genre);
     List<Movie> findByTitleContainingIgnoreCase(String title);
     List<Movie> findByDirector(String director);
     List<Movie> findByReleaseYear(Integer releaseYear);
     List<Movie> findByRatingGreaterThanEqual(Double rating);
     List<Movie> findByRatingLessThanEqual(Double rating);
}
