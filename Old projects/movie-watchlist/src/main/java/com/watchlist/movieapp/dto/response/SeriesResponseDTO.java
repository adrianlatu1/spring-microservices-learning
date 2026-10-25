package com.watchlist.movieapp.dto.response;

import com.watchlist.movieapp.entity.Genre;
import com.watchlist.movieapp.entity.SeriesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesResponseDTO {
    private Long id;
    private String title;
    private String description;
    private List<Genre> genres;
    private Integer releaseYear;
    private Double rating;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer seasons;
    private Integer totalEpisodes;
    private SeriesStatus status;
}
