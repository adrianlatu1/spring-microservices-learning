package com.watchlist.movieapp.service;

import com.watchlist.movieapp.dto.request.WatchListItemRequestDTO;
import com.watchlist.movieapp.dto.response.WatchListItemResponseDTO;
import com.watchlist.movieapp.entity.Media;
import com.watchlist.movieapp.entity.User;
import com.watchlist.movieapp.entity.WatchListItem;
import com.watchlist.movieapp.entity.WatchStatus;
import com.watchlist.movieapp.exception.ResourseNotFoundException;
import com.watchlist.movieapp.mapper.WatchListItemMapper;
import com.watchlist.movieapp.repository.MovieRepository;
import com.watchlist.movieapp.repository.SeriesRepository;
import com.watchlist.movieapp.repository.UserRepository;
import com.watchlist.movieapp.repository.WatchListItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class WatchListItemService {

    private final WatchListItemRepository watchListItemRepository;
    private final WatchListItemMapper watchListItemMapper;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;

    // add itme to watchlist
    public WatchListItemResponseDTO addToWatchList4TheUserId(Long userId, WatchListItemRequestDTO watchListItemRequestDTO) {

        //  find the user for which we want to add the item to the watchlist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        //find the media that we want to add to the watchList for the user
        Media media = movieRepository.findById(watchListItemRequestDTO.getMediaId())
                .map(m -> (Media) m)  // Movie → Media
                .or(() -> seriesRepository.findById(watchListItemRequestDTO.getMediaId())
                        .map(s -> (Media) s))  // Series → Media
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + watchListItemRequestDTO.getMediaId()));

        // check if the media is already in the user's watchlist
        if (watchListItemRepository.existsByUserIdAndMediaId(userId, media.getId())) {
            throw new RuntimeException("WatchList item already has this media for the user with id: " + userId);
        }

        //if everything is ok,create the watchlist item and save it to the database
        WatchListItem watchlistitem = watchListItemMapper.toEntity(watchListItemRequestDTO);
        watchlistitem.setUser(user);
        watchlistitem.setMedia(media);

        WatchListItem savedWatchListItem = watchListItemRepository.save(watchlistitem);
        return watchListItemMapper.toResponseDTO(savedWatchListItem);
    }

    // get the complete watchlist for a user

    public List<WatchListItemResponseDTO> getWatchListForUserId(Long userId) {

        // always check if the user exists
        if (!userRepository.existsById(userId)) {
            //throw new RuntimeException("User not found with id: " + userId);
            throw new ResourseNotFoundException("User", "id", userId);
        }
        return watchListItemRepository.findByUserId(userId)
                .stream()
                .map(watchListItemMapper::toResponseDTO)
                .collect(Collectors.toList());

    }

    // get the list of watchlist items for a user, filtered by the status of the item

    public List<WatchListItemResponseDTO> getWatchListForUserIdAndStatus(Long userId, WatchStatus status) {

        // always check if the user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return watchListItemRepository.findByUserIdAndStatus(userId, status)
                .stream()
                .map(watchListItemMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //find top 5 highest rated items for a user - using pageable

    public List<WatchListItemResponseDTO> getTop5HighestRatedItemsForUserId(Long userId) {

        // always check if the user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        //return watchListItemRepository.findTop5ByUserIdAndStatusOrderByPersonalRatingDesc() - naming convention
        return watchListItemRepository.findTop5RatedWatchedByUserId(userId, Pageable.ofSize(5))
                .stream()
                .map(watchListItemMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public WatchListItemResponseDTO updateWatchlistItem(Long id, WatchListItemRequestDTO dto) {
        WatchListItem existingItem = watchListItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Watchlist item not found with id: " + id));

        existingItem.setStatus(dto.getStatus());
        existingItem.setPersonalRating(dto.getPersonalRating());
        existingItem.setStartDate(dto.getStartDate());
        existingItem.setFinishDate(dto.getFinishDate());

        WatchListItem updatedItem = watchListItemRepository.save(existingItem);
        return watchListItemMapper.toResponseDTO(updatedItem);
    }

    public void deleteWatchlistItemById(Long id) {
        if (!watchListItemRepository.existsById(id)) {
            throw new RuntimeException("Watchlist item not found with id: " + id);
        }
        watchListItemRepository.deleteById(id);
    }

    public Long countCompletedItems(Long userId) {
        return watchListItemRepository.countByUserIdAndStatus(userId, WatchStatus.WATCHED);
    }

    public List<WatchListItemResponseDTO> getWatchListItemsByStatus(String status) {
        WatchStatus watchStatus = WatchStatus.valueOf(status.toUpperCase());
        List<WatchListItem> items = watchListItemRepository.findAllByStatus(watchStatus);
        return items.stream()
                .map(watchListItemMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<WatchListItemResponseDTO> getAllWatchListItems(){
        return watchListItemRepository.findAll()
                .stream()
                .map(watchListItemMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}


