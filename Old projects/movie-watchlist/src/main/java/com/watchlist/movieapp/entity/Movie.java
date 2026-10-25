package com.watchlist.movieapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@EqualsAndHashCode(callSuper = true) // 2 objects are equal only if they have the same value for the fields, including the ones in the superclass

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Media{

    private Integer duration; //in minutes
    private String director;
    private boolean hasSequel;


}


