package com.br.moviehub.controller;

import com.br.moviehub.dtos.watchlist.DeleteWatchlistByStatusDto;
import com.br.moviehub.dtos.watchlist.WatchlistTvShowDto;
import com.br.moviehub.dtos.watchlist.WatchlistTvShowUpdateDto;
import com.br.moviehub.enums.Status;
import com.br.moviehub.model.WatchlistTvShow;
import com.br.moviehub.service.WatchlistTvShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlistTvShow")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class WatchlistTvShowController {
    private final WatchlistTvShowService watchlistTvShowService;

    @Operation(summary =  "getAllByUserId", method = "GET", description ="Get all Tv Shows in watchlist by user id", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WatchlistTvShow.class)
                    ))})
    @GetMapping("/getAllByUserId/{id}")
    public ResponseEntity<List<WatchlistTvShow>> getAllByUserId(@PathVariable Long id){
        return ResponseEntity.ok(watchlistTvShowService.getAllByUserId(id));
    }

    @Operation(summary =  "isTvShowInWatchlist", method = "GET", description ="Check if Tv Show is in watchlist", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)
                    ))})
    @GetMapping("/isTvShowInWatchlist")
    public ResponseEntity<Boolean> isTvShowInWatchlist(@RequestParam Long userId, @RequestParam Long tvShowId){
        return ResponseEntity.ok(watchlistTvShowService.isTvShowInWatchlist(userId, tvShowId));
    }

    @Operation(summary =  "getTvShowByUserIdAndStatus", method = "GET", description ="Get Tv Show in watchlist by user id and status", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WatchlistTvShow.class)
                    ))})
    @GetMapping("/getAllByUserIdAndStatus")
    public ResponseEntity<List<WatchlistTvShow>> getTvShowByStatus(@RequestParam Long userId, @RequestParam Status status){
        return ResponseEntity.ok(watchlistTvShowService.getAllByUserIdAndStatus(userId, status));
    }

    @Operation(summary =  "add", method = "POST", description ="Add Tv Show in watchlist", responses = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WatchlistTvShow.class)
                    ))})
    @PostMapping("/add")
    public ResponseEntity<WatchlistTvShow> add(@RequestBody WatchlistTvShowDto watchlistDto){
        return new ResponseEntity<>(watchlistTvShowService.addInWatchlist(watchlistDto), HttpStatus.CREATED);
    }

    @Operation(summary =  "updateStatus", method = "PATCH", description ="Update the status of the Tv Show in the watchlist", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @PatchMapping("/updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestBody WatchlistTvShowUpdateDto watchlistDto){
        watchlistTvShowService.updateStatus(watchlistDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "delete", method = "DELETE", description ="Delete Tv Show from watchlist", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody WatchlistTvShowDto watchlistDto){
        watchlistTvShowService.deleteByUserIdAndTvShowId(watchlistDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "deleteByStatus", method = "DELETE", description ="Delete Tv Show from watchlist by status", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/deleteByStatus")
    public ResponseEntity<Void> deleteByStatus(@RequestBody DeleteWatchlistByStatusDto watchlistDto){
        watchlistTvShowService.deleteByUserIdAndStatus(watchlistDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "deleteAll", method = "DELETE", description ="Delete all Tv Shows from watchlist by userId", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAll(@PathVariable Long userId){
        watchlistTvShowService.deleteAllByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
