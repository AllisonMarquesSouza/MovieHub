package com.br.moviehub.service;

import com.br.moviehub.model.Movie;
import com.br.moviehub.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional
    public Movie save(Movie movie) {
        if(!movieRepository.existsById(movie.getId())){
            return movieRepository.save(movie);
        }
        return null;
    }

}
