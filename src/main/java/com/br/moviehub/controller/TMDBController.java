package com.br.moviehub.controller;

import com.br.moviehub.dtos.TMDB.details.MovieDetailsDto;
import com.br.moviehub.dtos.TMDB.details.TvShowDetailsDto;
import com.br.moviehub.dtos.TMDB.filters.GenresFilterDto;
import com.br.moviehub.dtos.TMDB.filters.MovieResultDto;
import com.br.moviehub.dtos.TMDB.filters.TvShowResultDto;
import com.br.moviehub.service.TmdbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tmdb")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class TMDBController {
    private final TmdbService tmdbService;

    @Operation(summary =  "languages", method = "GET", description ="Get all languages from TMDB", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json",  array = @ArraySchema(schema = @Schema(implementation = List.class))

                    ))})
    @GetMapping("/languages")
    public ResponseEntity<List<?>> allLanguages() {
        return ResponseEntity.ok(tmdbService.getAllLanguages());
    }

    @Operation(summary =  "getAllGenresMovie", method = "GET", description ="Get all genres of Movie", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenresFilterDto.class)
                    ))})
    @GetMapping("/getAllGenresMovie")
    public ResponseEntity<GenresFilterDto> allGenreMovie() {
        return ResponseEntity.ok(tmdbService.getAllGenresMovie());
    }

    @Operation(summary =  "getAllGenresTvShow", method = "GET", description ="Get all genres of Tv Show", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenresFilterDto.class)
                    ))})
    @GetMapping("/getAllGenresTvShow")
    public ResponseEntity<GenresFilterDto> allGenreTv() {
        return ResponseEntity.ok(tmdbService.getAllGenresTv());
    }

    //Movie methods
    @Operation(summary =  "getMovieById", method = "GET", description ="Get Movie by id ", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDetailsDto.class)
                    ))})
    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDetailsDto> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(tmdbService.getMovieById(id));
    }

    @Operation(summary =  "discoverMovie", method = "GET", description ="Discover Movies by params ", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieResultDto.class)
                    ))})
    @GetMapping("/discoverMovie")
    public ResponseEntity<MovieResultDto> discoverMovie(@RequestParam(required = false) Boolean adult,
                                                        @RequestParam(required = false) String language,
                                                        @RequestParam(required = false) Integer primary_release_year,
                                                        @RequestParam(required = false) String region,
                                                        @RequestParam(required = false) LocalDate release_date_gte,
                                                        @RequestParam(required = false) Float vote_average_gte,
                                                        @RequestParam(required = false) String with_genres,
                                                        @RequestParam(required = false) String with_keywords,
                                                        @RequestParam(required = false) String with_origin_country,
                                                        @RequestParam(required = false) String  without_genres,
                                                        @RequestParam(required = false)String without_keywords,
                                                        @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(tmdbService.getDiscoverMovie
                (adult, language, primary_release_year, region, release_date_gte, vote_average_gte, with_genres,
                        with_keywords, with_origin_country, without_genres, without_keywords, year));
    }

    @Operation(summary =  "popularMovies", method = "GET", description ="Get popular Movies ", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieResultDto.class)
                    ))})
    @GetMapping("/popularMovies")
    public ResponseEntity<MovieResultDto> popularMovies(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getPopularMovies(language));
    }

    @Operation(summary =  "nowPlayingMovie", method = "GET", description ="Get now playing Movies ", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieResultDto.class)
                    ))})
    @GetMapping("/nowPlayingMovie")
    public ResponseEntity<MovieResultDto> nowPlayingMovie(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getNowPlayingMovie(language));
    }

    @Operation(summary =  "topRatedMovies", method = "GET", description ="Get to rated Movies", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieResultDto.class)
                    ))})
    @GetMapping("/topRatedMovies")
    public ResponseEntity<MovieResultDto> topRatedMovies(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getTopRatedMovies(language));
    }

    @Operation(summary =  "upcomingMovies", method = "GET", description ="Get Upcoming Movies", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieResultDto.class)
                    ))})
    @GetMapping("/upcomingMovies")
    public ResponseEntity<MovieResultDto> upcomingMovies(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getUpcomingMovies(language));
    }

    //Tv methods
    @Operation(summary =  "getTvShowById", method = "GET", description ="Get Tv Show by id ", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TvShowDetailsDto.class)
                    ))})
    @GetMapping(value = "/tv/{id}")
    public ResponseEntity<TvShowDetailsDto> getTvById(@PathVariable Long id) {
        return ResponseEntity.ok(tmdbService.getTvById(id));
    }

    @Operation(summary =  "discoverTv", method = "GET", description ="Discover Tv Shows by params ", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TvShowResultDto.class)
                    ))})
    @GetMapping("/discoverTv")
    public ResponseEntity<TvShowResultDto> discoverTv(@RequestParam(required = false) LocalDate air_date_gte,
                                                      @RequestParam(required = false) Integer first_air_date_year,
                                                      @RequestParam(required = false) LocalDate first_air_date_gte,
                                                      @RequestParam(required = false) Boolean include_adult,
                                                      @RequestParam(required = false) String language,
                                                      @RequestParam(required = false) Float vote_average_gte,
                                                      @RequestParam(required = false) Float vote_count_gte,
                                                      @RequestParam(required = false) String with_genres,
                                                      @RequestParam(required = false) String with_keywords,
                                                      @RequestParam(required = false) String with_origin_country,
                                                      @RequestParam(required = false) String with_original_language,
                                                      @RequestParam(required = false) String without_genres,
                                                      @RequestParam(required = false) String without_keywords) {
        return ResponseEntity.ok(tmdbService.getDiscoverTv
                (air_date_gte, first_air_date_year, first_air_date_gte, include_adult, language, vote_average_gte,
                        vote_count_gte, with_genres, with_keywords, with_origin_country, with_original_language,
                        without_genres, without_keywords) );
    }

    @Operation(summary =  "airingTodayTv", method = "GET", description ="Get airing today Tv Show", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TvShowResultDto.class)
                    ))})
    @GetMapping("/airingTodayTv")
    public ResponseEntity<TvShowResultDto> airingTodayTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getAiringTodayTv(language));
    }

    @Operation(summary =  "popularTv", method = "GET", description ="Get popular Tv Shows", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TvShowResultDto.class)
                    ))})
    @GetMapping("/popularTv")
    public ResponseEntity<TvShowResultDto> popularTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getPopularTv(language));
    }

    @Operation(summary =  "topRatedTv", method = "GET", description ="Get top rated Tv Shows", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TvShowResultDto.class)
                    ))})
    @GetMapping("/topRatedTv")
    public ResponseEntity<TvShowResultDto> topRatedTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getTopRatedTv(language));
    }

    @Operation(summary =  "onTheAirTv", method = "GET", description ="Get on the air Tv Shows", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TvShowResultDto.class)
                    ))})
    @GetMapping("/onTheAirTv")
    public ResponseEntity<TvShowResultDto> onTheAirTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getOnTheAirTv(language));
    }
}
