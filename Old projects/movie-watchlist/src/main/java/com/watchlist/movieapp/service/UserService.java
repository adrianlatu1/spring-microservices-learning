package com.watchlist.movieapp.service;

import com.watchlist.movieapp.dto.request.UserRequestDTO;
import com.watchlist.movieapp.dto.response.UserResponseDTO;
import com.watchlist.movieapp.entity.User;
import com.watchlist.movieapp.mapper.UserMapper;
import com.watchlist.movieapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new RuntimeException("Username already exists: " + userRequestDTO.getUsername());
        }

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + userRequestDTO.getEmail());
        }
        User userEntity = userMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(userEntity);
        return userMapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return userMapper.toResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
      //TODO
        return null;
    }

    private final Map<String, Integer> userViewCounts = new ConcurrentHashMap<>();

    public void incrementViewCount(String userId) {
        userViewCounts.merge(userId, 1, Integer::sum);
        log.info("Incremented view count for user {}: {}", userId, userViewCounts.get(userId));
    }

    public int getViewCount(String userId) {
        return userViewCounts.getOrDefault(userId, 0);
    }

}
