package com.br.moviehub.service;

import com.br.moviehub.dtos.TMDB.details.MovieDetailsDto;
import com.br.moviehub.dtos.watchlistMovie.WatchlistMovieDto;
import com.br.moviehub.dtos.watchlistMovie.WatchlistMovieUpdateDto;
import com.br.moviehub.dtos.watchlistMovie.DeleteWatchlistByStatusDto;
import com.br.moviehub.enums.Status;
import com.br.moviehub.model.*;
import com.br.moviehub.repository.UserRepository;
import com.br.moviehub.repository.WatchlistMovieRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WatchlistMovieService {
    private final WatchlistMovieRepository watchlistMovieRepository;
    private final MovieService movieService;
    private final TmdbService tmdbService;
    private final GenreService genreService;
    private final MovieGenreService movieGenreService;
    private final UserRepository userRepository;

    public List<WatchlistMovie> getAllByUserId(Long userId) {
        return watchlistMovieRepository.findAllByUserId(userId);
    }

    public Boolean isMovieInWatchlist(Long userId, Long movieId) {
        return watchlistMovieRepository.existsByUserIdAndMovieId(userId, movieId);
    }

    public List<WatchlistMovie> getAllByUserIdAndStatus(Long userId, Status status) {
        return watchlistMovieRepository.findAllByUserIdAndStatus(userId, status);
    }

    @Transactional
    public WatchlistMovie addInWatchlist(WatchlistMovieDto watchlistDto) {
        User user = userRepository.findById(watchlistDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        MovieDetailsDto movieFound = Optional.ofNullable(tmdbService.getMovieById(watchlistDto.getMovieId()))
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        Movie movieToSave = new Movie(movieFound);


        List<Genre> genreListSaved = genreService.saveAll(movieFound.getGenres());

        movieService.save(movieToSave);

        for(Genre genre : genreListSaved) {
            if (!movieGenreService.existsByMovieIdAndGenreId(movieToSave.getId(), genre.getId())) {
                MovieGenre movieGenreToSave = new MovieGenre(movieToSave, genre);
                movieGenreService.save(movieGenreToSave);
            }
        }

        if(watchlistMovieRepository.existsByUserIdAndMovieId(user.getId(), movieToSave.getId())) {
            throw new EntityExistsException("Movie already exists in the Watchlist");
        }
        WatchlistMovie watchlistMovie = new WatchlistMovie(user, movieToSave);
        return watchlistMovieRepository.save(watchlistMovie);
    }

    @Transactional
    public void updateStatus(WatchlistMovieUpdateDto watchlistUpdateDto) {
        WatchlistMovie watchListToUpdate =
                watchlistMovieRepository.findByUserIdAndMovieId(watchlistUpdateDto.getUserId(), watchlistUpdateDto.getMovieId())
                        .orElseThrow(() -> new EntityNotFoundException("Movie not found in watchlist, check userId and movieId"));

        watchListToUpdate.setStatus(watchlistUpdateDto.getStatus());
        watchlistMovieRepository.save(watchListToUpdate);
    }

    @Transactional
    public void deleteByUserIdAndStatus(DeleteWatchlistByStatusDto statusDto) {
        if(watchlistMovieRepository.existsByUserIdAndStatus(statusDto.getUserId(), statusDto.getStatus())) {
            watchlistMovieRepository.deleteAllByUserIdAndStatus(statusDto.getUserId(), statusDto.getStatus());
            return;
        }
        throw new EntityNotFoundException("Movies with status " + statusDto.getStatus() + " not found");
    }

    @Transactional
    public void deleteByUserIdAndMovieId(WatchlistMovieDto watchlistDto) {
        watchlistMovieRepository.findByUserIdAndMovieId(watchlistDto.getUserId(), watchlistDto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Not found in watchlist, check the UserId and MovieId"));

        watchlistMovieRepository.deleteByUserIdAndMovieId(watchlistDto.getUserId(), watchlistDto.getMovieId());
    }

    @Transactional
    public void deleteAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        watchlistMovieRepository.deleteAllByUserId(user.getId());
    }
}
