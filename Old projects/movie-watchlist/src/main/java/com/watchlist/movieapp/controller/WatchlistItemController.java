package com.watchlist.movieapp.controller;

import com.watchlist.movieapp.dto.request.WatchListItemRequestDTO;
import com.watchlist.movieapp.dto.response.WatchListItemResponseDTO;
import com.watchlist.movieapp.entity.WatchListItem;
import com.watchlist.movieapp.service.WatchListItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchlist-items") // prefix for all endpoints in this controller
@Tag(name = "Watchlist", description = "Endpoints for managing watchlist items")
public class WatchlistItemController {

    private final WatchListItemService watchlistItemService;

    // add an item to the watchlist for a specific user
    @PostMapping("/user/{userId}")
    @Operation(summary = "Get all watchlist items for a specific user", description = "Retrieve all items for a specific user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved watchlist items for this user ")
    public ResponseEntity<WatchListItemResponseDTO> createWatchlist(@RequestBody WatchListItemRequestDTO watchlistRequestDTO, Long userId) {
        WatchListItemResponseDTO watchlistResponseDTO = watchlistItemService.addToWatchList4TheUserId(userId, watchlistRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(watchlistResponseDTO);
    }

    //get all the items in the watchlist for a specific user
    @GetMapping("/user/{userId}")
    public List<WatchListItemResponseDTO> getWatchListItemsForUser(@PathVariable Long userId) {
        return watchlistItemService.getWatchListForUserId(userId);
    }

    //get getTop5HighestRatedItemsForUserId
    @GetMapping("/user/{userId}/top-5")
    public List<WatchListItemResponseDTO> getTop5HighestRatedItemsForUserId(@PathVariable Long userId) {
        return watchlistItemService.getTop5HighestRatedItemsForUserId(userId);
    }

    //update the status/rating of a watchlist item
    @PutMapping("/{id}")
    public WatchListItemResponseDTO updateWatchListItem(@PathVariable Long id, @RequestBody WatchListItemRequestDTO watchlistItemRequestDTO) {
        return watchlistItemService.updateWatchlistItem(id, watchlistItemRequestDTO);
    }

    //delete a watchlist item
    @DeleteMapping("/{id}")
    public void deleteWatchListItem(@PathVariable Long id) {
        watchlistItemService.deleteWatchlistItemById(id);
    }

    //get number of watched  items for a specific user
    @GetMapping("/user/{userId}/completed")
    public ResponseEntity<Long> getCompletedItemsForUser(@PathVariable Long userId) {
        Long watchedItemsCount = watchlistItemService.countCompletedItems(userId);
        return ResponseEntity.ok(watchedItemsCount);
    }


    // Get all watchlist items filtered by status (query param)
    @GetMapping("/status")
    public List<WatchListItemResponseDTO> getWatchListItemsByStatus(@RequestParam String status) {
        return watchlistItemService.getWatchListItemsByStatus(status);
    }

    //Get all watchlist items
    @GetMapping
    @Operation(summary = "Get all watchlist items", description = "Retrieve all items in the watchlist")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved watchlist items")
    public List<WatchListItemResponseDTO> getWatchListItems() {
        return watchlistItemService.getAllWatchListItems();
    }

}
