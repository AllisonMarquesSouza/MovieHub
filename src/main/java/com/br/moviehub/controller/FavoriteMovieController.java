package com.br.moviehub.controller;

import com.br.moviehub.dtos.favorites.FavoriteMovieDto;
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

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<FavoriteMovie>> getFavoritesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteMovieService.getFavoritesByUserId(userId));
    }

    @GetMapping("/isFavorited")
    public ResponseEntity<Boolean> isMovieFavorited(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        return ResponseEntity.ok(favoriteMovieService.isMovieFavorited(favoriteMovieDto));
    }

    @PostMapping("/add")
    public ResponseEntity<FavoriteMovie> addFavorite(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        return new ResponseEntity<>(favoriteMovieService.addFavorite(favoriteMovieDto),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFavorite(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        favoriteMovieService.deleteFavorite(favoriteMovieDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAllFavorites(@PathVariable Long userId) {
        favoriteMovieService.deleteAllFavorites(userId);
        return ResponseEntity.noContent().build();
    }
}
