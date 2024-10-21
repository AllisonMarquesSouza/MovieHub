package com.br.moviehub.service;

import com.br.moviehub.dtos.TMDB.details.MovieDetailsDto;
import com.br.moviehub.dtos.favoriteMovie.FavoriteMovieDto;
import com.br.moviehub.model.*;
import com.br.moviehub.repository.FavoriteMovieRepository;
import com.br.moviehub.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class FavoriteMovieService {
    private final MovieService movieService;
    private final GenreService genreService;
    private final MovieGenreService movieGenreService;
    private final FavoriteMovieRepository favoriteMovieRepository;
    private final TmdbService tmdbService;
    private final UserRepository userRepository;

    public List<FavoriteMovie> getFavoritesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return favoriteMovieRepository.findAllByUser(user);
    }

    public Boolean isMovieFavorited(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return favoriteMovieRepository.existsByUserIdAndMovieId(user.getId(), movieId);
    }


    @Transactional
    public FavoriteMovie addFavorite(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        MovieDetailsDto movieFound = Optional.ofNullable(tmdbService.getMovieById(movieId)).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        Movie movieToSave = new Movie(movieFound);


        // Create and save genres, ensure they are saved before using them
        List<Genre> genreListSaved = genreService.saveAll(movieFound.getGenres());

        //Save the movie
        movieService.save(movieToSave);


        for(Genre genre : genreListSaved) {
            // Check if the movie and genre relationship already exists
            if (!movieGenreService.existsByMovieIdAndGenreId(movieToSave.getId(), genre.getId())) {
                MovieGenre movieGenreToSave = new MovieGenre(movieToSave, genre);
                movieGenreService.save(movieGenreToSave);
            }
            if (!movieToSave.getGenres().contains(genre)) {
                movieToSave.getGenres().add(genre);
            }
        }

        if(favoriteMovieRepository.existsByUserIdAndMovieId(user.getId(), movieToSave.getId())) {
            throw new EntityExistsException("Movie already exists in the favorite list");
        }
        FavoriteMovie favoriteMovie = new FavoriteMovie(user, movieToSave);
        return favoriteMovieRepository.save(favoriteMovie);
    }

    @Transactional
    public void removeFavorite(FavoriteMovieDto favoriteMovieDto) {
        favoriteMovieRepository.findByUserIdAndMovieId(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Not found in favorite list, check the UserId and MovieId"));

        if(favoriteMovieRepository.existsByUserIdAndMovieId(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId())) {
            favoriteMovieRepository.deleteByUserIdAndMovieId(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId());
        }
    }

    @Transactional
    public void removeAllFavorites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        favoriteMovieRepository.deleteAllByUserId(user.getId());
    }
}
