package com.br.moviehub.repository;

import com.br.moviehub.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
    Boolean existsByMovieIdAndGenreId(Long movieId, Long genreId);

}
