package com.br.moviehub.service;

import com.br.moviehub.model.Genre;
import com.br.moviehub.model.Movie;
import com.br.moviehub.repository.MovieGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieGenreService {
    private final MovieGenreRepository movieGenreRepository;


    public List<Genre> findGenresByMovieId(Long id) {
        return movieGenreRepository.findGenresByMovieId(id);
    }

    public List<Movie> findMoviesByGenreId(Long id) {
        return movieGenreRepository.findMoviesByGenreId(id);
    }

    public List<Movie> findMoviesByGenreName(String name) {
        return movieGenreRepository.findMoviesByGenreName(name);
    }

}
