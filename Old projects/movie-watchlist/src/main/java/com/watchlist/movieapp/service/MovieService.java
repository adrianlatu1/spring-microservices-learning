package com.watchlist.movieapp.service;


import com.watchlist.movieapp.dto.request.MovieRequestDTO;
import com.watchlist.movieapp.dto.response.MovieResponseDTO;
import com.watchlist.movieapp.entity.Movie;
import com.watchlist.movieapp.exception.ResourseNotFoundException;
import com.watchlist.movieapp.exception.ValidationException;
import com.watchlist.movieapp.mapper.MovieMapper;
import com.watchlist.movieapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional // every method in the class will run within a transaction(in case of error -> rollback)
@RequiredArgsConstructor // Lombok Constructor injection automat
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MessageSource messageSource;
    //private final LocaleContextHolder localeContextHolder;


    // Constructor injection
   /* public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }*/

    //Application context - the main container in Spring that manages the beans and their dependencies.
    // It is responsible for creating, configuring, and managing the lifecycle of beans in a Spring application. The application context is created when the application starts and is used to retrieve beans and manage their dependencies throughout the application's lifecycle.
/*
    @Autowired
    private ApplicationContext applicationContext;
    MovieService movieService = applicationContext.getBean(MovieService.class); */// get the MovieService bean from the application context


    //create movie
    public MovieResponseDTO createMovie(MovieRequestDTO movierequestDTO) {

        log.info("Creating new movie: {}", movierequestDTO.getTitle());
        if(movierequestDTO.getTitle() == null || movierequestDTO.getTitle().isEmpty()){
            log.error("Title is required");
            throw new ValidationException("title", "Title is required");
        }

        Map<String, String> errors = new HashMap<>();

        if(movierequestDTO.getReleaseYear()>2026 || movierequestDTO.getReleaseYear()<1888){
            //throw new ValidationException("releaseYear", "Release year must be between 1888 and 2026");
            log.warn("Invalid release year: {}", movierequestDTO.getReleaseYear());
            errors.put("release_year", "Invalid release_year - must be between 1888 and 2026");
        }

        if(movierequestDTO.getRating() < 1 || movierequestDTO.getRating() > 10){
            //throw new ValidationException("rating", "Rating must be between 0 and 10");
            log.warn("Invalid rating: {}", movierequestDTO.getRating());
            errors.put("rating", "Invalid rating - must be between 1 and 10");
        }
        if(!errors.isEmpty()){
            throw new ValidationException("Validation failed", errors);
        }

        Movie movie = movieMapper.toMovie(movierequestDTO);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toMovieResponseDTO(savedMovie);
    }

    // find a movie by id
    public MovieResponseDTO findMovieById(Long id) {
        Movie movieEntity = movieRepository.findById(id)
                //.orElseThrow( () -> new RuntimeException("Movie not found with id: " + id));
                //.orElseThrow( () -> new ResourseNotFoundException("Movie", "id", id)); // without i18nternationalization
                .orElseThrow(() -> {
                    String message = messageSource.getMessage(
                            "movie.not.found",
                            new Object[]{id},
                            LocaleContextHolder.getLocale()
                    );
                    return new ResourseNotFoundException(message);
                });
        return movieMapper.toMovieResponseDTO(movieEntity);
    }

    //find all movies
    public List<MovieResponseDTO> findAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieResponseDTO)
                .collect(Collectors.toList());
    }

    //find movie by title
    public List<MovieResponseDTO> findMoviesByTitle(String title){
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(movieMapper::toMovieResponseDTO)
                .collect(Collectors.toList());
    }

    //delete movie by id
    public void deleteMovieById(Long id){
        if(movieRepository.existsById(id)){
            movieRepository.deleteById(id);
        } else{
            throw new RuntimeException("Movie not found with id: " + id);
        }
    }

    //update a movie by id
    public MovieResponseDTO updateMovieById(Long id, MovieRequestDTO movieRequestDTO) {
        Movie existingMovie = movieRepository.findById(id)
                //.orElseThrow( () -> new RuntimeException("Movie not found with id: " + id));
                .orElseThrow(() -> new ResourseNotFoundException("Movie", "id", id));

        existingMovie.setTitle(movieRequestDTO.getTitle());
        existingMovie.setDescription(movieRequestDTO.getDescription());
        existingMovie.setReleaseYear(movieRequestDTO.getReleaseYear());
        existingMovie.setRating(movieRequestDTO.getRating());
        existingMovie.setGenres(movieRequestDTO.getGenres());
        existingMovie.setDuration(movieRequestDTO.getDuration());
        existingMovie.setDirector(movieRequestDTO.getDirector());

        Movie updatedMovie = movieRepository.save(existingMovie);
        return movieMapper.toMovieResponseDTO(updatedMovie);
    }
}
