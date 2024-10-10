package com.br.moviehub.controller;

import com.br.moviehub.model.Genre;
import com.br.moviehub.model.TvShow;
import com.br.moviehub.service.TvShowGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tvshowgenre")
@RequiredArgsConstructor
public class TvShowGenreController {
    private final TvShowGenreService tvShowGenreService;

    @GetMapping("findGenresByTvShowId/{tvShowId}")
    public ResponseEntity<List<Genre>> findGenresByTvShowId(@PathVariable Long tvShowId) {
        return ResponseEntity.ok(tvShowGenreService.findGenresByTvShowId(tvShowId));
    }

    @GetMapping("findTvShowByGenreId/{genreId}")
    public ResponseEntity<List<TvShow>> findTvShowByGenreId(@PathVariable Long genreId) {
        return ResponseEntity.ok(tvShowGenreService.findTvShowByGenreId(genreId));
    }

    @GetMapping("findTvShowByGenreName/{genreName}")
    public ResponseEntity<List<TvShow>> findTvShowByGenreName(@PathVariable String genreName) {
        return ResponseEntity.ok(tvShowGenreService.findTvShowByGenreName(genreName));
    }
 }
