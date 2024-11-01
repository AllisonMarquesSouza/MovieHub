package com.br.moviehub.repository;

import com.br.moviehub.model.TvShowGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowGenreRepository extends JpaRepository<TvShowGenre, Long> {
    Boolean existsByTvShowIdAndGenreId(Long tvShowId, Long genreId);
}
