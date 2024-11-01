package com.br.moviehub.service;

import com.br.moviehub.model.MovieGenre;
import com.br.moviehub.repository.MovieGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieGenreService {
    private final MovieGenreRepository movieGenreRepository;

    @Transactional
    public MovieGenre save(MovieGenre movieGenre) {
        return movieGenreRepository.save(movieGenre);
    }

    public Boolean existsByMovieIdAndGenreId(Long movieId, Long genreId) {
        return movieGenreRepository.existsByMovieIdAndGenreId(movieId, genreId);
    }

}
