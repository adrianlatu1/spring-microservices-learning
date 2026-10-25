package com.watchlist.movieapp.repository;

import com.watchlist.movieapp.entity.WatchListItem;
import com.watchlist.movieapp.entity.WatchStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WatchListItemRepository extends JpaRepository<WatchListItem, Long> {


    List<WatchListItem> findByUserId(Long userId);
    List<WatchListItem> findByUserIdAndStatus(Long userId, WatchStatus status);
    Optional<WatchListItem> findByUserIdAndMediaId(Long userId, Long mediaId);// Optional - wrapper for avoiding NPE
    boolean existsByUserIdAndMediaId(Long userId, Long mediaId);
    Long countByUserIdAndStatus(Long userId, WatchStatus status);


    //custom query - find all items for a user, sorted by personal rating
    //TODO - using @Query annotation with Jakarta Persistence Query Language
    @Query("SELECT w FROM WatchListItem w WHERE w.user.id = :userId ORDER BY w.personalRating DESC")
    List<WatchListItem> findTopRatedWatchListItemsByUserId(@Param(("userId")) Long userId);

    //custom query - find top 5 highest rated items for a user - using pageable
    //TODO - using @Query annotation with Jakarta Persistence Query Language
    @Query("SELECT w FROM WatchListItem w WHERE w.user.id = :userId AND w.status = 'WATCHED' ORDER BY w.personalRating DESC")
    List<WatchListItem> findTop5RatedWatchedByUserId(@Param("userId") Long userId, Pageable pageable);

    //custom query - find top 5 highest rated items for a user - using Naming Convention
    List<WatchListItem> findTop5ByUserIdAndStatusOrderByPersonalRatingDesc(Long userId, WatchStatus status);

    List<WatchListItem> findAllByStatus(WatchStatus status);


}
