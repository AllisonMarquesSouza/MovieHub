package com.br.moviehub.controller;

import com.br.moviehub.dtos.favorites.FavoriteTvShowDto;
import com.br.moviehub.model.FavoriteTvShow;
import com.br.moviehub.service.FavoriteTvShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favoriteTvShow")
@RequiredArgsConstructor
public class FavoriteTvShowController {
    private final FavoriteTvShowService favoriteTvShowService;

    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<List<FavoriteTvShow>> getFavoritesByUserId(@PathVariable Long id){
        return ResponseEntity.ok(favoriteTvShowService.getFavoritesByUserId(id));
    }

    @GetMapping("/isFavorited")
    public ResponseEntity<Boolean> isTvShowFavorited(@RequestBody FavoriteTvShowDto tvShowDto){
        return ResponseEntity.ok(favoriteTvShowService.isTvShowFavorited(tvShowDto));
    }

    @PostMapping("/add")
    public ResponseEntity<FavoriteTvShow> addFavorite(@RequestBody FavoriteTvShowDto tvShowDto){
        return new ResponseEntity<>(favoriteTvShowService.addFavorite(tvShowDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFavorite(@RequestBody FavoriteTvShowDto tvShowDto){
        favoriteTvShowService.deleteFavorite(tvShowDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll/{id}")
    public ResponseEntity<Void> deleteAllFavorites(@PathVariable Long id){
        favoriteTvShowService.removeAllFavorites(id);
        return ResponseEntity.noContent().build();
    }

}
