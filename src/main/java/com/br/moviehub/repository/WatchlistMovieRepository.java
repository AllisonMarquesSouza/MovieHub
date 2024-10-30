package com.br.moviehub.repository;

import com.br.moviehub.enums.Status;
import com.br.moviehub.model.WatchlistMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchlistMovieRepository extends JpaRepository<WatchlistMovie, Long> {
    List<WatchlistMovie> findAllByUserId(Long userId);
    Boolean existsByUserIdAndMovieId(Long userId, Long movieId);
    Optional<WatchlistMovie> findByUserIdAndMovieId(Long userId, Long movieId);
    void deleteByUserIdAndMovieId(Long userId, Long movieId);
    void deleteAllByUserId(Long userId);
    void deleteAllByUserIdAndStatus(Long userId, Status status);
    Boolean existsByUserIdAndStatus(Long userId, Status status);
    List<WatchlistMovie> findAllByUserIdAndStatus(Long userId, Status status);
}
