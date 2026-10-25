package com.watchlist.movieapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "watchlist_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Many WatchListItems can belong to one User
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // Many WatchListItems can have one Media
    @JoinColumn(name = "media_id", nullable = false) // Foreign key to Media
    private Media media;

    @Enumerated(EnumType.STRING) // saves the enum values as strings in the database
    @Column(nullable = false)
    private WatchStatus status;

    @Column(name = "personal_rating")
    private Double personalRating;  // user's personal rating for the media

    @Column(name = "start_date")
    private LocalDate startDate;    // date when the user started watching

    @Column(name = "finish_date")
    private LocalDate finishDate;   // date when the user finished watching

    @Column(name = "added_at")
    private LocalDate addedAt;      // date when the item was added to the watchlist by the user

    protected void onCreate() {
        this.addedAt = LocalDate.now();
        if(status == null){
            this.status = WatchStatus.PLAN_TO_WATCH;
        }
        if(status == WatchStatus.WATCHING) {
            this.startDate = LocalDate.now();
        }

    }

}
