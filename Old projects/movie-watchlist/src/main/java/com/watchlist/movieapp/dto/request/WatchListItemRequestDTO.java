package com.watchlist.movieapp.dto.request;

import com.watchlist.movieapp.entity.WatchStatus;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchListItemRequestDTO {

    @NotNull(message = "Media ID is required")
    private Long mediaId;

    @NotNull(message = "Status is required")
    private WatchStatus status;

    @DecimalMin(value = "0.0", message = "Personal rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Personal rating must not exceed 10.0")
    private Double personalRating;

    private LocalDate startDate;
    private LocalDate finishDate;
}

