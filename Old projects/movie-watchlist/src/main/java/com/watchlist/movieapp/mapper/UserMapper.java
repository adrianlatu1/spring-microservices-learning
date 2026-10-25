package com.watchlist.movieapp.mapper;

import com.watchlist.movieapp.dto.request.UserRequestDTO;
import com.watchlist.movieapp.dto.response.UserResponseDTO;
import com.watchlist.movieapp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // DTO Request → Entity
    User toEntity(UserRequestDTO dto);
    // Entity → DTO Response
    UserResponseDTO toResponseDTO(User user);
}
