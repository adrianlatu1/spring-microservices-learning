package com.watchlist.movieapp.service;

import com.watchlist.movieapp.dto.request.SeriesRequestDTO;
import com.watchlist.movieapp.dto.response.SeriesResponseDTO;
import com.watchlist.movieapp.entity.Series;
import com.watchlist.movieapp.entity.SeriesStatus;
import com.watchlist.movieapp.mapper.SeriesMapper;
import com.watchlist.movieapp.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor // Lombok Constructor injection automat
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;

    public SeriesResponseDTO createSeries(SeriesRequestDTO seriesRequestDTO) {
        Series series = seriesMapper.toEntity(seriesRequestDTO);
        Series savedSeries = seriesRepository.save(series);
        return seriesMapper.toResponseDTO(savedSeries);
        
    }

    public SeriesResponseDTO getSeriesById(Long id) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found with id: " + id));
        return seriesMapper.toResponseDTO(series);
    }

    public List<SeriesResponseDTO> getAllSeries() {
        List<Series> savedSeries = seriesRepository.findAll();
        return savedSeries.stream()
                .map(seriesMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<SeriesResponseDTO> searchSeriesByTitle(String title) {
        return seriesRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(seriesMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<SeriesResponseDTO> getSeriesByStatus(SeriesStatus status) {
        return seriesRepository.findByStatus(status)
                .stream()
                .map(seriesMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteSeries(Long id) {
        if (!seriesRepository.existsById(id)) {
            throw new RuntimeException("Series not found with id: " + id);
        }
        seriesRepository.deleteById(id);
    }

    public SeriesResponseDTO updateSeries(Long id, SeriesRequestDTO seriesRequestDTO) {
        Series existingSeries = seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found with id: " + id));

        existingSeries.setTitle(seriesRequestDTO.getTitle());
        existingSeries.setDescription(seriesRequestDTO.getDescription());
        existingSeries.setReleaseYear(seriesRequestDTO.getReleaseYear());
        existingSeries.setRating(seriesRequestDTO.getRating());
        existingSeries.setStatus(seriesRequestDTO.getStatus());
        existingSeries.setSeasons(seriesRequestDTO.getSeasons());

        Series updatedSeries = seriesRepository.save(existingSeries);
        return seriesMapper.toResponseDTO(updatedSeries);
    }
}
