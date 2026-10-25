package com.watchlist.movieapp.mapper;

import com.watchlist.movieapp.dto.request.SeriesRequestDTO;
import com.watchlist.movieapp.dto.response.SeriesResponseDTO;
import com.watchlist.movieapp.entity.Series;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesMapper {

    // DTO Request → Entity
    Series toEntity(SeriesRequestDTO dto);

    // Entity → DTO Response
    SeriesResponseDTO toResponseDTO(Series series);
}
