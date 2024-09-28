package com.br.moviehub.controller;

import com.br.moviehub.dtos.tmdbDtosApi.MovieResultDto;
import com.br.moviehub.dtos.tmdbDtosApi.TMDbGenresDto;
import com.br.moviehub.dtos.tmdbDtosApi.TvResultDto;
import com.br.moviehub.service.TmdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tmdb")
@RequiredArgsConstructor
public class TMDbController {
    private final TmdbService tmdbService;

    @GetMapping("/languages")
    public ResponseEntity<List<?>> allLanguages() {
        return ResponseEntity.ok(tmdbService.getAllLanguages());
    }

    @GetMapping("/genreMovie")
    public ResponseEntity<TMDbGenresDto> allGenreMovie() {
        return ResponseEntity.ok(tmdbService.getAllGenresMovie());
    }

    @GetMapping("/genreTv")
    public ResponseEntity<TMDbGenresDto> allGenreTv() {
        return ResponseEntity.ok(tmdbService.getAllGenresTv());
    }

    //Movie Methods
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

    @GetMapping("/popularMovies")
    public ResponseEntity<MovieResultDto> popularMovies(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getPopularMovies(language));
    }

    @GetMapping("/nowPlayingMovie")
    public ResponseEntity<MovieResultDto> nowPlayingMovie(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getNowPlayingMovie(language));
    }

    @GetMapping("/topRatedMovies")
    public ResponseEntity<MovieResultDto> topRatedMovies(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getTopRatedMovies(language));
    }

    @GetMapping("/upcomingMovies")
    public ResponseEntity<MovieResultDto> upcomingMovies(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getUpcomingMovies(language));
    }

    //Tv methods
    @GetMapping("/discoverTv")
    public ResponseEntity<TvResultDto> discoverTv(@RequestParam(required = false) LocalDate air_date_gte,
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

    @GetMapping("/airingTodayTv")
    public ResponseEntity<TvResultDto> airingTodayTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getAiringTodayTv(language));
    }
    @GetMapping("/popularTv")
    public ResponseEntity<TvResultDto> popularTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getPopularTv(language));
    }
    @GetMapping("/topRatedTv")
    public ResponseEntity<TvResultDto> topRatedTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getTopRatedTv(language));
    }
    @GetMapping("/onTheAirTv")
    public ResponseEntity<TvResultDto> onTheAirTv(@RequestParam(required = false) String language) {
        return ResponseEntity.ok(tmdbService.getOnTheAirTv(language));
    }
}
