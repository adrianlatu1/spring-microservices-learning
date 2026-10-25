package com.watchlist.movieapp.dto.response;

import com.watchlist.movieapp.entity.WatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchListItemResponseDTO {

    private Long id;
    private Long userId;
    private Long mediaId;
    private String mediaTitle;  // manually included
    private String mediaType;   // ← "MOVIE" / "SERIES"
    private WatchStatus status;
    private Double personalRating;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate addedAt;
}

