package com.br.moviehub.controller;

import com.br.moviehub.dtos.watchlistMovie.WatchlistMovieDto;
import com.br.moviehub.dtos.watchlistMovie.WatchlistMovieUpdateDto;
import com.br.moviehub.dtos.watchlistMovie.DeleteWatchlistByStatusDto;
import com.br.moviehub.enums.Status;
import com.br.moviehub.model.WatchlistMovie;
import com.br.moviehub.service.WatchlistMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("watchlistMovie")
@RequiredArgsConstructor
public class WatchlistMovieController {
    private final WatchlistMovieService watchlistMovieService;

    @GetMapping("/getAllByUserId/{id}")
    public ResponseEntity<List<WatchlistMovie>> getAllByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(watchlistMovieService.getAllByUserId(id));
    }

    @GetMapping("/isMovieInWatchlist")
    public ResponseEntity<Boolean> isMovieInWatchlist(@RequestParam Long userId, @RequestParam Long movieId) {
        return ResponseEntity.ok(watchlistMovieService.isMovieInWatchlist(userId, movieId));
    }

    @GetMapping("/getMoviesByStatus")
    public ResponseEntity<List<WatchlistMovie>> getMoviesByStatus(@RequestParam Long userId, @RequestParam Status status) {
        return ResponseEntity.ok(watchlistMovieService.getAllByUserIdAndStatus(userId, status));
    }

    @PostMapping("/add")
    public ResponseEntity<WatchlistMovie> addInWatchlist(@RequestBody WatchlistMovieDto watchlistMovieDto) {
        return new ResponseEntity<>(watchlistMovieService.addInWatchlist(watchlistMovieDto), HttpStatus.CREATED);
    }

    @PatchMapping("updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestBody WatchlistMovieUpdateDto updateDto) {
        watchlistMovieService.updateStatus(updateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByStatus")
    public ResponseEntity<Void> deleteByStatus(@RequestBody DeleteWatchlistByStatusDto statusDto){
        watchlistMovieService.deleteByUserIdAndStatus(statusDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteByUserAndMovie(@RequestBody WatchlistMovieDto watchlistMovieDto){
        watchlistMovieService.deleteByUserIdAndMovieId(watchlistMovieDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAll(@PathVariable Long userId){
        watchlistMovieService.deleteAllByUserId(userId);
        return ResponseEntity.noContent().build();
    }

}
