package com.watchlist.movieapp.mapper;

import com.watchlist.movieapp.dto.request.MovieRequestDTO;
import com.watchlist.movieapp.dto.response.MovieResponseDTO;
import com.watchlist.movieapp.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    public Movie toMovie(MovieRequestDTO movieRequestDTO);
    public MovieResponseDTO toMovieResponseDTO(Movie movie);
}
