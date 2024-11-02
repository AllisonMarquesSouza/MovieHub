package com.br.moviehub.controller;

import com.br.moviehub.dtos.favorites.FavoriteMovieDto;
import com.br.moviehub.model.FavoriteMovie;
import com.br.moviehub.service.FavoriteMovieService;
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
@RequestMapping("favoriteMovie")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class FavoriteMovieController {
    private final FavoriteMovieService favoriteMovieService;

    @Operation(summary =  "getByUserId", method = "GET", description ="Get favorites Movies by user id", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FavoriteMovie.class)
                    ))})
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<FavoriteMovie>> getFavoritesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteMovieService.getFavoritesByUserId(userId));
    }

    @Operation(summary =  "isFavorited", method = "GET", description ="Check if Movie is favorite", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)
                    ))})
    @GetMapping("/isFavorited")
    public ResponseEntity<Boolean> isMovieFavorited(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        return ResponseEntity.ok(favoriteMovieService.isMovieFavorited(favoriteMovieDto));
    }

    @Operation(summary =  "add", method = "POST", description ="Add Movie in favorite list", responses = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FavoriteMovie.class)
                    ))})
    @PostMapping("/add")
    public ResponseEntity<FavoriteMovie> addFavorite(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        return new ResponseEntity<>(favoriteMovieService.addFavorite(favoriteMovieDto),
                HttpStatus.CREATED);
    }

    @Operation(summary =  "delete", method = "DELETE", description ="Delete Movie from favorite list", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFavorite(@RequestBody FavoriteMovieDto favoriteMovieDto) {
        favoriteMovieService.deleteFavorite(favoriteMovieDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "deleteAll", method = "DELETE", description ="Delete all Movies from favorite list", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAllFavorites(@PathVariable Long userId) {
        favoriteMovieService.deleteAllFavorites(userId);
        return ResponseEntity.noContent().build();
    }
}
