package com.watchlist.movieapp.repository;

import com.watchlist.movieapp.entity.Series;
import com.watchlist.movieapp.entity.SeriesStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Long> {

    List<Series> findByTitleContainingIgnoreCase(String title);
    List<Series> findByStatus(SeriesStatus status);
    List<Series> findByRatingGreaterThanEqual(Double rating);
    List<Series> findBySeasons(Integer seasons);
}
