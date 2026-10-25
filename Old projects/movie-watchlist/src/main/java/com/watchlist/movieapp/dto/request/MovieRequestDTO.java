package com.watchlist.movieapp.dto.request;


import com.watchlist.movieapp.entity.Genre;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {

   @NotBlank(message = "Title is required")
   @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
    private boolean hasSequel;

    @Min(value = 1912, message = "Release year must be after 1912")
    @Max(value = 2026, message = "Release year must be before 2026")
    private Integer releaseYear;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10.0")
    private Double rating;

    private List<Genre> genres;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    @NotBlank(message = "Director is required")
    private String director;

}
