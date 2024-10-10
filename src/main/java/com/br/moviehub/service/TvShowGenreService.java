package com.br.moviehub.service;

import com.br.moviehub.model.Genre;
import com.br.moviehub.model.TvShow;
import com.br.moviehub.repository.TvShowGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TvShowGenreService {
    private final TvShowGenreRepository tvShowGenreRepository;

    public List<Genre> findGenresByTvShowId(Long tvShowId) {
        return tvShowGenreRepository.findGenresByTvShowId(tvShowId);
    }

    public List<TvShow> findTvShowByGenreId(Long genreId) {
        return tvShowGenreRepository.findTvShowByGenreId(genreId);
    }

    public List<TvShow> findTvShowByGenreName(String genreName) {
        return tvShowGenreRepository.findTvShowByGenreName(genreName);
    }
}
