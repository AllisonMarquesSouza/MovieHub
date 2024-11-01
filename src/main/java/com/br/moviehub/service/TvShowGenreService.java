package com.br.moviehub.service;

import com.br.moviehub.model.TvShowGenre;
import com.br.moviehub.repository.TvShowGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TvShowGenreService {
    private final TvShowGenreRepository tvShowGenreRepository;
    public Boolean existsByTvShowIdAndGenreId(Long tvShowId, Long genreId) {
        return tvShowGenreRepository.existsByTvShowIdAndGenreId(tvShowId, genreId);
    }

    @Transactional
    public TvShowGenre save(TvShowGenre tvShowGenre) {
        return tvShowGenreRepository.save(tvShowGenre);
    }
}
