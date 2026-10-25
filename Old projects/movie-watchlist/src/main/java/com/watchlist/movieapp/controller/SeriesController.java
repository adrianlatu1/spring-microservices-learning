package com.watchlist.movieapp.controller;


import com.watchlist.movieapp.dto.request.SeriesRequestDTO;
import com.watchlist.movieapp.dto.response.SeriesResponseDTO;
import com.watchlist.movieapp.service.SeriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/series") // prefix for endpoints
@Tag(name = "Series Controller", description = "Endpoints for managing TV series in the watchlist")
public class SeriesController {

    private final SeriesService seriesService;


    //create series - POST w/o ResponseEntity
/*    @PostMapping
    public SeriesResponseDTO createSeries(@RequestBody SeriesRequestDTO seriesRequestDTO) {
        return  seriesService.createSeries(seriesRequestDTO);
    }*/

    //create series with ResponseEntity
    @PostMapping
    public ResponseEntity<SeriesResponseDTO> createSeries(@RequestBody SeriesRequestDTO seriesRequestDTO){
        SeriesResponseDTO seriesResponseDTO = seriesService.createSeries(seriesRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(seriesResponseDTO); // status 201 + body with created series
    }

    //get series by id - GET
    @GetMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> getSeriesById(@PathVariable Long id) {
        SeriesResponseDTO series = seriesService.getSeriesById(id);
        return ResponseEntity.ok(series); // status 200 + body with found series
    }
     //get all series - GET
    @GetMapping
    public List<SeriesResponseDTO> getAllSeries() {
        return seriesService.getAllSeries();
    }

    //get series by title - GET  (es: /api/series/title?title=Breaking Bad)
    @GetMapping("/title")
    public List<SeriesResponseDTO> getSeriesByTitle(@RequestParam String title) {
        return seriesService.searchSeriesByTitle(title);
    }

    //update series by id
    @PutMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> updateSeries(
            @PathVariable Long id,
            @RequestBody SeriesRequestDTO seriesRequestDTO) {
        SeriesResponseDTO updatedSeries = seriesService.updateSeries(id, seriesRequestDTO);
        return ResponseEntity.ok(updatedSeries);
    }


    //delete series by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
        return ResponseEntity.noContent().build(); // status 204 No Content, no body needed for delete response
    }


}
