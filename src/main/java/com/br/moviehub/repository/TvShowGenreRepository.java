package com.br.moviehub.repository;

import com.br.moviehub.model.Genre;
import com.br.moviehub.model.TvShow;
import com.br.moviehub.model.TvShowGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvShowGenreRepository extends JpaRepository<TvShowGenre, Long> {

    @Query("SELECT t.genre FROM TvShowGenre t WHERE t.tvShow.id= :id")
    List<Genre> findGenresByTvShowId(@Param("id") Long id);

    @Query("SELECT t.tvShow FROM TvShowGenre t WHERE t.genre.id = :id")
    List<TvShow> findTvShowByGenreId(@Param("id") Long id);

    @Query("SELECT t.tvShow FROM TvShowGenre t WHERE t.genre.name = :name")
    List<TvShow> findTvShowByGenreName(@Param("name") String name);

    Boolean existsByTvShowIdAndGenreId(Long tvShowId, Long genreId);
}
