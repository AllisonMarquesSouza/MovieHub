package com.br.moviehub.controller;

import com.br.moviehub.model.Genre;
import com.br.moviehub.model.Movie;
import com.br.moviehub.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moviegenre")
@RequiredArgsConstructor
public class MovieGenreController {
    private final MovieGenreService movieGenreService;

    @GetMapping("/findGenresByMovieId/{id}")
    public ResponseEntity<List<Genre>> findGenresByMovieId(@PathVariable Long id) {
        List<Genre> movieGenre = movieGenreService.findGenresByMovieId(id);
        return ResponseEntity.ok(movieGenre);
    }
    @GetMapping("/findMoviesByGenreId/{id}")
    public ResponseEntity<List<Movie>> findMoviesByGenreId(@PathVariable Long id) {
        List<Movie> movieGenre = movieGenreService.findMoviesByGenreId(id);
        return ResponseEntity.ok(movieGenre);
    }
    @GetMapping("/findMoviesByGenreName/{name}")
    public ResponseEntity<List<Movie>> findMoviesByGenreName(@PathVariable String name) {
        List<Movie> movieGenre = movieGenreService.findMoviesByGenreName(name);
        return ResponseEntity.ok(movieGenre);
    }


}

