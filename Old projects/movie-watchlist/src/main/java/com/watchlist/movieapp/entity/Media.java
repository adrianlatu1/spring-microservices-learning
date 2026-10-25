package com.watchlist.movieapp.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "media")
@Inheritance(strategy = InheritanceType.JOINED) //this class is a base class for other entities (Movie and Series), and each subclass will have its own table in the database, with a foreign key to the media table. This allows us to store common attributes in the media table and specific attributes in the subclass tables, while still maintaining a clear relationship between them.

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;

    @ElementCollection(targetClass = Genre.class) // genres is a collection of elements from the enmu, not separate entities
    @CollectionTable(name = "media_genres", joinColumns = @JoinColumn(name = "media_id")) //separate table to store the genres for each media, with a foreign key to the media table
    @Enumerated(EnumType.STRING) // saves the enum values as strings in the database
    @Column(name = "genre") // the name of the column in the media_genres table that will store the genre values
    private List<Genre> genres = new ArrayList<>();


    private Integer releaseYear ;
    @DecimalMin(value = "0.0", message = "Rating must be positive")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10.0")
    private Double rating; // global rating for the media, calculated as the average of all user ratings
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist // called only when the entity is SAVED to the DB(only at INSERT), not when an object is created in memory or read from the DB
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate // called only when the entity is UPDATED in the DB, not when an object is created in memory or read from the DB
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }



}
