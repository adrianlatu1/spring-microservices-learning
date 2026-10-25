package com.watchlist.movieapp.dto.request;

import com.watchlist.movieapp.entity.Genre;
import com.watchlist.movieapp.entity.SeriesStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Min(value = 1888, message = "Release year must be after 1888")
    @Max(value = 2100, message = "Release year must be before 2100")
    private Integer releaseYear;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10.0")
    private Double rating;

    private List<Genre> genres;

    @Min(value = 1, message = "Seasons must be at least 1")
    private Integer seasons;

    @Min(value = 1, message = "Total episodes must be at least 1")
    private Integer totalEpisodes;

    @NotNull(message = "Status is required")
    private SeriesStatus status;
}
