package com.br.moviehub.service;

import com.br.moviehub.model.Genre;
import com.br.moviehub.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public List<Genre> saveAll(List<Genre> genres) {
        List<Genre> collectGenres = genres.stream()
                .filter(genre -> !genreRepository.existsById(genre.getId()))
                .toList();
        if(!collectGenres.isEmpty()) {
            return genreRepository.saveAll(collectGenres); // Save new genres
        }

        // Fetch all genres (either existing or newly saved) by their IDs
        List<Long> genreIds = genres.stream()
                        .map(Genre::getId)
                        .toList();
        return genreRepository.findAllById(genreIds); // Retrieve existing genres
    }

}
