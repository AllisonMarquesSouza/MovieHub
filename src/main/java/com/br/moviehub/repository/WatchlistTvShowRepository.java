package com.br.moviehub.repository;

import com.br.moviehub.enums.Status;
import com.br.moviehub.model.WatchlistTvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchlistTvShowRepository extends JpaRepository<WatchlistTvShow, Long> {
    List<WatchlistTvShow> findAllByUserId(Long userId);
    Boolean existsByUserIdAndTvShowId(Long userId, Long tvShowId);
    List<WatchlistTvShow> findAllByUserIdAndStatus(Long userId, Status status);
    Optional<WatchlistTvShow> findByUserIdAndTvShowId(Long userId, Long tvShowId);
    Boolean existsByUserIdAndStatus(Long userId, Status status);
    void deleteAllByUserIdAndStatus(Long userId, Status status);
    void deleteByUserIdAndTvShowId(Long userId, Long tvShowId);
    void deleteAllByUserId(Long userId);
}
