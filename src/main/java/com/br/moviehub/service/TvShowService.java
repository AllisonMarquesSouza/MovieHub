package com.br.moviehub.service;

import com.br.moviehub.model.TvShow;
import com.br.moviehub.repository.TvShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TvShowService {
    private final TvShowRepository tvShowRepository;

    @Transactional
    public TvShow save(TvShow tvShow) {
        if(!tvShowRepository.existsById(tvShow.getId())){
            return tvShowRepository.save(tvShow);
        }
        return null;
    }
}
