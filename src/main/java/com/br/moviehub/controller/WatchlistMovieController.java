package com.br.moviehub.controller;

import com.br.moviehub.dtos.watchlist.WatchlistMovieDto;
import com.br.moviehub.dtos.watchlist.WatchlistMovieUpdateDto;
import com.br.moviehub.dtos.watchlist.DeleteWatchlistByStatusDto;
import com.br.moviehub.enums.Status;
import com.br.moviehub.model.WatchlistMovie;
import com.br.moviehub.service.WatchlistMovieService;
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
@RequestMapping("watchlistMovie")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class WatchlistMovieController {
    private final WatchlistMovieService watchlistMovieService;

    @Operation(summary =  "getAllByUserId", method = "GET", description ="Get all Movies in watchlist by user id", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WatchlistMovie.class)
                    ))})
    @GetMapping("/getAllByUserId/{id}")
    public ResponseEntity<List<WatchlistMovie>> getAllByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(watchlistMovieService.getAllByUserId(id));
    }

    @Operation(summary =  "isMovieInWatchlist", method = "GET", description ="Check if Movie is in watchlist", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)
                    ))})
    @GetMapping("/isMovieInWatchlist")
    public ResponseEntity<Boolean> isMovieInWatchlist(@RequestParam Long userId, @RequestParam Long movieId) {
        return ResponseEntity.ok(watchlistMovieService.isMovieInWatchlist(userId, movieId));
    }

    @Operation(summary =  "getMovieByUserIdAndStatus", method = "GET", description ="Get Movie in watchlist by user id and status", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WatchlistMovie.class)
                    ))})
    @GetMapping("/getMoviesByStatus")
    public ResponseEntity<List<WatchlistMovie>> getMoviesByStatus(@RequestParam Long userId, @RequestParam Status status) {
        return ResponseEntity.ok(watchlistMovieService.getAllByUserIdAndStatus(userId, status));
    }

    @Operation(summary =  "add", method = "POST", description ="Add Movie in watchlist", responses = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WatchlistMovie.class)
                    ))})
    @PostMapping("/add")
    public ResponseEntity<WatchlistMovie> addInWatchlist(@RequestBody WatchlistMovieDto watchlistMovieDto) {
        return new ResponseEntity<>(watchlistMovieService.addInWatchlist(watchlistMovieDto), HttpStatus.CREATED);
    }

    @Operation(summary =  "updateStatus", method = "PATCH", description ="Update the status of the Movie in the watchlist", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @PatchMapping("updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestBody WatchlistMovieUpdateDto updateDto) {
        watchlistMovieService.updateStatus(updateDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "deleteByStatus", method = "DELETE", description ="Delete Movie from watchlist by status", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/deleteByStatus")
    public ResponseEntity<Void> deleteByStatus(@RequestBody DeleteWatchlistByStatusDto statusDto){
        watchlistMovieService.deleteByUserIdAndStatus(statusDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "delete", method = "DELETE", description ="Delete Movie from watchlist", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteByUserAndMovie(@RequestBody WatchlistMovieDto watchlistMovieDto){
        watchlistMovieService.deleteByUserIdAndMovieId(watchlistMovieDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "deleteAll", method = "DELETE", description ="Delete all Movies from watchlist by userId", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAll(@PathVariable Long userId){
        watchlistMovieService.deleteAllByUserId(userId);
        return ResponseEntity.noContent().build();
    }

}
