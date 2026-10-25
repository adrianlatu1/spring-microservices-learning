package com.watchlist.movieapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "series")

@Data
@NoArgsConstructor  // for JPA
@AllArgsConstructor // for JPA
//@RequiredArgsConstructor // generates a constructor for final and @NonNull fields
public class Series extends Media{

    private Integer seasons;
    private Integer totalEpisodes;

    @Enumerated(EnumType.STRING)  // Store the enum as a string in the database
    @Column(name = "series_status")
    private SeriesStatus status;
}
