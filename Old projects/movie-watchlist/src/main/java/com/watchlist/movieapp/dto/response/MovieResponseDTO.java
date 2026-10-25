package com.watchlist.movieapp.dto.response;

import com.watchlist.movieapp.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String description;
    private List<Genre> genres;
    private Integer releaseYear;
    private Double rating;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer duration;
    private String director;
    private boolean hasSequel;
}
