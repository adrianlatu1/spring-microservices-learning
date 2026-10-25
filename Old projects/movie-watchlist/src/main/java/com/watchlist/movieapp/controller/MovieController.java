package com.watchlist.movieapp.controller;


import com.watchlist.movieapp.dto.request.MovieRequestDTO;
import com.watchlist.movieapp.dto.response.MovieResponseDTO;
import com.watchlist.movieapp.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // combines @Controller + @ResponseBody, returns JSON
@RequestMapping("/api/movies") // prefix for all endpoints in this controller
@RequiredArgsConstructor // generates constructor only for the final fields/or @NonNull fields
@Tag(name = "Movie", description = "Movies handling")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    // without response entity for status POST
    public MovieResponseDTO createMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        return movieService.createMovie(movieRequestDTO);
    }
    //create movie with response entity for status
    //TODO

    //find movie by id - GET
    @GetMapping("/{id}")
    public MovieResponseDTO getMovieById(@PathVariable Long id){
        return movieService.findMovieById(id);
    }
    //get movie with response entity for status
    //TODO

    //get all movies GET
    @GetMapping
    public List<MovieResponseDTO> getAllMovies(){
        return movieService.findAllMovies();
    }

    // update a movie by id  - PUT
    @PutMapping("/{id}")
    public MovieResponseDTO updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO){
        return movieService.updateMovieById(id, movieRequestDTO);
    }

    //delete a movie by id -DELETE
    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteMovieById(id);
        return "Movie with id " + id + " has been deleted.";
    }

}
