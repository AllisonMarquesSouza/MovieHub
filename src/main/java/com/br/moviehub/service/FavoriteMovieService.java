package com.br.moviehub.service;

import com.br.moviehub.dtos.TMDB.details.MovieDetailsDto;
import com.br.moviehub.dtos.favorites.FavoriteMovieDto;
import com.br.moviehub.model.*;
import com.br.moviehub.repository.FavoriteMovieRepository;
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
public class FavoriteMovieService {
    private final MovieService movieService;
    private final GenreService genreService;
    private final MovieGenreService movieGenreService;
    private final FavoriteMovieRepository favoriteMovieRepository;
    private final TmdbService tmdbService;
    private final UserRepository userRepository;

    public List<FavoriteMovie> getFavoritesByUserId(Long userId) {
        return favoriteMovieRepository.findAllByUserId(userId);
    }

    public Boolean isMovieFavorited(FavoriteMovieDto favoriteMovieDto) {
        return favoriteMovieRepository.existsByUserIdAndMovieId(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId());
    }

    @Transactional
    public FavoriteMovie addFavorite(FavoriteMovieDto favoriteMovieDto) {
        User user = userRepository.findById(favoriteMovieDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        MovieDetailsDto movieFound = Optional.ofNullable(tmdbService.getMovieById(favoriteMovieDto.getMovieId()))
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

        if(favoriteMovieRepository.existsByUserIdAndMovieId(user.getId(), movieToSave.getId())) {
            throw new EntityExistsException("Movie already exists in the favorite list");
        }
        FavoriteMovie favoriteMovie = new FavoriteMovie(user, movieToSave);
        return favoriteMovieRepository.save(favoriteMovie);
    }

    @Transactional
    public void deleteFavorite(FavoriteMovieDto favoriteMovieDto) {
        favoriteMovieRepository.findByUserIdAndMovieId(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Not found in favorite list, check the UserId and MovieId"));

        favoriteMovieRepository.deleteByUserIdAndMovieId(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId());

    }

    @Transactional
    public void deleteAllFavorites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        favoriteMovieRepository.deleteAllByUserId(user.getId());
    }
}
