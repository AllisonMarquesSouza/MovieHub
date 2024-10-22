package com.br.moviehub.dtos.TMDB.filters;

import com.br.moviehub.model.Genre;
import lombok.Getter;

import java.util.List;

@Getter
public class GenresFilterDto {
    private List<Genre> genres;
}
