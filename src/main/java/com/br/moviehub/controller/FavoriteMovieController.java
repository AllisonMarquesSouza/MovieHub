package com.br.moviehub.controller;

import com.br.moviehub.dtos.favoriteMovie.FavoriteMovieDto;
import com.br.moviehub.model.FavoriteMovie;
import com.br.moviehub.service.FavoriteMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favoriteMovie")
@RequiredArgsConstructor
public class FavoriteMovieController {
    private final FavoriteMovieService favoriteMovieService;

    @PostMapping("/addFavorite")
    public ResponseEntity<FavoriteMovie> addFavorite(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        return new ResponseEntity<>(favoriteMovieService.addFavorite(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId()),
                HttpStatus.CREATED);
    }

    @GetMapping("/getFavoritesByUserId/{userId}")
    public ResponseEntity<List<FavoriteMovie>> getFavoritesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteMovieService.getFavoritesByUserId(userId));
    }

    @GetMapping("/isMovieFavorited")
    public ResponseEntity<Boolean> isMovieFavorited(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        return ResponseEntity.ok(favoriteMovieService.isMovieFavorited(favoriteMovieDto.getUserId(), favoriteMovieDto.getMovieId()));
    }

    @DeleteMapping("/removeFavorite")
    public ResponseEntity<Void> removeFavorite(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        favoriteMovieService.removeFavorite(favoriteMovieDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/removeAllFavorites/{userId}")
    public ResponseEntity<Void> removeAllFavorites(@PathVariable Long userId) {
        favoriteMovieService.removeAllFavorites(userId);
        return ResponseEntity.noContent().build();
    }
}
