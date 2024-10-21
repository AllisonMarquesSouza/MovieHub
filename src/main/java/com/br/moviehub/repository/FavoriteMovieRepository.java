package com.br.moviehub.repository;

import com.br.moviehub.model.FavoriteMovie;
import com.br.moviehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
    List<FavoriteMovie> findAllByUser(User user);
    Boolean existsByUserIdAndMovieId(Long userId, Long movieId);
    void deleteByUserIdAndMovieId(Long userId, Long movieId);
    void deleteAllByUserId(Long userId);
    Optional<FavoriteMovie> findByUserIdAndMovieId(Long userId, Long movieId);
}