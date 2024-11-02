package com.br.moviehub.controller;

import com.br.moviehub.dtos.favorites.FavoriteTvShowDto;
import com.br.moviehub.model.FavoriteMovie;
import com.br.moviehub.model.FavoriteTvShow;
import com.br.moviehub.service.FavoriteTvShowService;
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
@RequestMapping("favoriteTvShow")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class FavoriteTvShowController {
    private final FavoriteTvShowService favoriteTvShowService;

    @Operation(summary =  "getByUserId", method = "GET", description ="Get favorites Tv Shows by user id", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FavoriteTvShow.class)
                    ))})
    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<List<FavoriteTvShow>> getFavoritesByUserId(@PathVariable Long id){
        return ResponseEntity.ok(favoriteTvShowService.getFavoritesByUserId(id));
    }

    @Operation(summary =  "isFavorited", method = "GET", description ="Check if Tv Show is favorite", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)
                    ))})
    @GetMapping("/isFavorited")
    public ResponseEntity<Boolean> isTvShowFavorited(@RequestBody FavoriteTvShowDto tvShowDto){
        return ResponseEntity.ok(favoriteTvShowService.isTvShowFavorited(tvShowDto));
    }

    @Operation(summary =  "add", method = "POST", description ="Add Tv Show in favorite list", responses = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FavoriteTvShow.class)
                    ))})
    @PostMapping("/add")
    public ResponseEntity<FavoriteTvShow> addFavorite(@RequestBody FavoriteTvShowDto tvShowDto){
        return new ResponseEntity<>(favoriteTvShowService.addFavorite(tvShowDto), HttpStatus.CREATED);
    }

    @Operation(summary =  "delete", method = "DELETE", description ="Delete Tv Show from favorite list", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFavorite(@RequestBody FavoriteTvShowDto tvShowDto){
        favoriteTvShowService.deleteFavorite(tvShowDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary =  "deleteAll", method = "DELETE", description ="Delete all Tv Shows from favorite list", responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content",
                    content = @Content(mediaType = "application/json"
                    ))})
    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAllFavorites(@PathVariable Long userId){
        favoriteTvShowService.removeAllFavorites(userId);
        return ResponseEntity.noContent().build();
    }

}
