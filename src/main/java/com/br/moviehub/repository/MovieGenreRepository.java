package com.br.moviehub.repository;

import com.br.moviehub.model.Genre;
import com.br.moviehub.model.Movie;
import com.br.moviehub.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {

    @Query("SELECT m.genre FROM MovieGenre m WHERE m.movie.id = :id")
    List<Genre> findGenresByMovieId(@Param("id") Long id);

    @Query("SELECT m.movie FROM MovieGenre m WHERE m.genre.id = :id")
    List<Movie> findMoviesByGenreId(@Param("id") Long id);

    @Query("SELECT m.movie FROM MovieGenre m WHERE m.genre.name = :name")
    List<Movie> findMoviesByGenreName(@Param("name") String name);

}
