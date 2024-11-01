package com.br.moviehub.controller;

import com.br.moviehub.dtos.watchlist.DeleteWatchlistByStatusDto;
import com.br.moviehub.dtos.watchlist.WatchlistTvShowDto;
import com.br.moviehub.dtos.watchlist.WatchlistTvShowUpdateDto;
import com.br.moviehub.enums.Status;
import com.br.moviehub.model.WatchlistTvShow;
import com.br.moviehub.service.WatchlistTvShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlistTvShow")
@RequiredArgsConstructor
public class WatchlistTvShowController {
    private final WatchlistTvShowService watchlistTvShowService;

    @GetMapping("/getAllByUserId/{id}")
    public ResponseEntity<List<WatchlistTvShow>> getAllByUserId(@PathVariable Long id){
        return ResponseEntity.ok(watchlistTvShowService.getAllByUserId(id));
    }

    @GetMapping("/isTvShowInWatchlist")
    public ResponseEntity<Boolean> isTvShowInWatchlist(@RequestParam Long userId, @RequestParam Long tvShowId){
        return ResponseEntity.ok(watchlistTvShowService.isTvShowInWatchlist(userId, tvShowId));
    }

    @GetMapping("/getAllByUserIdAndStatus")
    public ResponseEntity<List<WatchlistTvShow>> getTvShowByStatus(@RequestParam Long userId, @RequestParam Status status){
        return ResponseEntity.ok(watchlistTvShowService.getAllByUserIdAndStatus(userId, status));
    }

    @PostMapping("/add")
    public ResponseEntity<WatchlistTvShow> add(@RequestBody WatchlistTvShowDto watchlistDto){
        return new ResponseEntity<>(watchlistTvShowService.addInWatchlist(watchlistDto), HttpStatus.CREATED);
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestBody WatchlistTvShowUpdateDto watchlistDto){
        watchlistTvShowService.updateStatus(watchlistDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody WatchlistTvShowDto watchlistDto){
        watchlistTvShowService.deleteByUserIdAndTvShowId(watchlistDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByStatus")
    public ResponseEntity<Void> deleteByStatus(@RequestBody DeleteWatchlistByStatusDto watchlistDto){
        watchlistTvShowService.deleteByUserIdAndStatus(watchlistDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAll(@PathVariable Long userId){
        watchlistTvShowService.deleteAllByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
