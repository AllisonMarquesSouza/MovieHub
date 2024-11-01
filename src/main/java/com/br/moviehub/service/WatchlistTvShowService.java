package com.br.moviehub.service;

import com.br.moviehub.dtos.TMDB.details.TvShowDetailsDto;
import com.br.moviehub.dtos.watchlist.DeleteWatchlistByStatusDto;
import com.br.moviehub.dtos.watchlist.WatchlistTvShowDto;
import com.br.moviehub.dtos.watchlist.WatchlistTvShowUpdateDto;
import com.br.moviehub.enums.Status;
import com.br.moviehub.model.*;
import com.br.moviehub.repository.UserRepository;
import com.br.moviehub.repository.WatchlistTvShowRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WatchlistTvShowService {
    private final WatchlistTvShowRepository watchlistTvShowRepository;
    private final TvShowService tvShowService;
    private final TmdbService tmdbService;
    private final GenreService genreService;
    private final TvShowGenreService tvShowGenreService;
    private final UserRepository userRepository;


    public List<WatchlistTvShow> getAllByUserId(Long userId) {
        return watchlistTvShowRepository.findAllByUserId(userId);
    }

    public Boolean isTvShowInWatchlist(Long userId, Long tvShowId) {
        return watchlistTvShowRepository.existsByUserIdAndTvShowId(userId, tvShowId);
    }

    public List<WatchlistTvShow> getAllByUserIdAndStatus(Long userId, Status status) {
        return watchlistTvShowRepository.findAllByUserIdAndStatus(userId, status);
    }

    @Transactional
    public WatchlistTvShow addInWatchlist(WatchlistTvShowDto watchlistDto) {
        User user = userRepository.findById(watchlistDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        TvShowDetailsDto movieFound = Optional.ofNullable(tmdbService.getTvById(watchlistDto.getTvShowId()))
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        TvShow tvShowToSave = new TvShow(movieFound);

        List<Genre> genreListSaved = genreService.saveAll(movieFound.getGenres());

        tvShowService.save(tvShowToSave);

        for(Genre genre : genreListSaved) {
            if (!tvShowGenreService.existsByTvShowIdAndGenreId(tvShowToSave.getId(), genre.getId())) {
                TvShowGenre tvShowGenreToSave = new TvShowGenre(tvShowToSave, genre);
                tvShowGenreService.save(tvShowGenreToSave);
            }
        }

        if(watchlistTvShowRepository.existsByUserIdAndTvShowId(user.getId(), tvShowToSave.getId())) {
            throw new EntityExistsException("TvShow already exists in the Watchlist");
        }
        WatchlistTvShow watchlistTvShow = new WatchlistTvShow(user, tvShowToSave);
        return watchlistTvShowRepository.save(watchlistTvShow);
    }

    @Transactional
    public void updateStatus(WatchlistTvShowUpdateDto watchlistUpdateDto) {
        WatchlistTvShow watchListToUpdate =
                watchlistTvShowRepository.findByUserIdAndTvShowId(watchlistUpdateDto.getUserId(), watchlistUpdateDto.getTvShowId())
                        .orElseThrow(() -> new EntityNotFoundException("Movie not found in watchlist, check userId and movieId"));

        watchListToUpdate.setStatus(watchlistUpdateDto.getStatus());
        watchlistTvShowRepository.save(watchListToUpdate);
    }

    @Transactional
    public void deleteByUserIdAndStatus(DeleteWatchlistByStatusDto statusDto) {
        if(watchlistTvShowRepository.existsByUserIdAndStatus(statusDto.getUserId(), statusDto.getStatus())) {
            watchlistTvShowRepository.deleteAllByUserIdAndStatus(statusDto.getUserId(), statusDto.getStatus());
            return;
        }
        throw new EntityNotFoundException("Movies with status " + statusDto.getStatus() + " not found");
    }

    @Transactional
    public void deleteByUserIdAndTvShowId(WatchlistTvShowDto watchlistDto) {
        watchlistTvShowRepository.findByUserIdAndTvShowId(watchlistDto.getUserId(), watchlistDto.getTvShowId())
                .orElseThrow(() -> new EntityNotFoundException("Not found in watchlist, check the UserId and MovieId"));

        watchlistTvShowRepository.deleteByUserIdAndTvShowId(watchlistDto.getUserId(), watchlistDto.getTvShowId());
    }

    @Transactional
    public void deleteAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        watchlistTvShowRepository.deleteAllByUserId(user.getId());
    }
}
