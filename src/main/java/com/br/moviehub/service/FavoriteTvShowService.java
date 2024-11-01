package com.br.moviehub.service;

import com.br.moviehub.dtos.TMDB.details.TvShowDetailsDto;
import com.br.moviehub.dtos.favorites.FavoriteTvShowDto;
import com.br.moviehub.model.*;
import com.br.moviehub.repository.FavoriteTvShowRepository;
import com.br.moviehub.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteTvShowService {
    private final FavoriteTvShowRepository favoriteTvShowRepository;
    private final TvShowService tvShowService;
    private final GenreService genreService;
    private final TvShowGenreService tvShowGenreService;
    private final TmdbService tmdbService;
    private final UserRepository userRepository;

    public List<FavoriteTvShow> getFavoritesByUserId(Long userId) {
        return favoriteTvShowRepository.findAllByUserId(userId);
    }

    public Boolean isTvShowFavorited(FavoriteTvShowDto favoriteTvShowDto) {
        return favoriteTvShowRepository.existsByUserIdAndTvShowId(favoriteTvShowDto.getUserId(), favoriteTvShowDto.getTvShowId());
    }

    @Transactional
    public FavoriteTvShow addFavorite(FavoriteTvShowDto favoriteTvShowDto) {
        User user = userRepository.findById(favoriteTvShowDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        TvShowDetailsDto tvShowFound = Optional.ofNullable(tmdbService.getTvById(favoriteTvShowDto.getTvShowId()))
                .orElseThrow(() -> new EntityNotFoundException("TvShow not found"));

        TvShow tvShowToSave = new TvShow(tvShowFound);

        List<Genre> genreListSaved = genreService.saveAll(tvShowFound.getGenres());

        tvShowService.save(tvShowToSave);

        for(Genre genre : genreListSaved) {
            if (!tvShowGenreService.existsByTvShowIdAndGenreId(tvShowToSave.getId(), genre.getId())) {
                TvShowGenre tvShowGenreToSave = new TvShowGenre(tvShowToSave, genre);
                tvShowGenreService.save(tvShowGenreToSave);
            }
        }

        if(favoriteTvShowRepository.existsByUserIdAndTvShowId(user.getId(), tvShowToSave.getId())) {
            throw new EntityExistsException("Movie already exists in the favorite list");
        }
        FavoriteTvShow favoriteTvShow = new FavoriteTvShow(user, tvShowToSave);
        return favoriteTvShowRepository.save(favoriteTvShow);
    }

    @Transactional
    public void deleteFavorite(FavoriteTvShowDto favoriteTvShowDto) {
        favoriteTvShowRepository.findByUserIdAndTvShowId(favoriteTvShowDto.getUserId(), favoriteTvShowDto.getTvShowId())
                .orElseThrow(() -> new EntityNotFoundException("Not found in favorite list, check the UserId and MovieId"));

        favoriteTvShowRepository.deleteByUserIdAndTvShowId(favoriteTvShowDto.getUserId(), favoriteTvShowDto.getTvShowId());
    }

    @Transactional
    public void removeAllFavorites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        favoriteTvShowRepository.deleteAllByUserId(user.getId());
    }

}
