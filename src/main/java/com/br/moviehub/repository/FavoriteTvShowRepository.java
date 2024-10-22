package com.br.moviehub.repository;

import com.br.moviehub.model.FavoriteTvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteTvShowRepository extends JpaRepository<FavoriteTvShow, Long> {
    List<FavoriteTvShow> findAllByUserId(Long userId);
    Boolean existsByUserIdAndTvShowId(Long userId, Long tvShowId);
    Optional<FavoriteTvShow> findByUserIdAndTvShowId(Long userId, Long tvShowId);
    void deleteByUserIdAndTvShowId(Long userId, Long tvShowId);
    void deleteAllByUserId(Long userId);
}
