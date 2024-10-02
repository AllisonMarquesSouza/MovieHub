package com.br.moviehub.dtos.TMDB.filters;

import com.br.moviehub.dtos.TMDB.details.GenreDetailsDto;
import lombok.Getter;

import java.util.List;

@Getter
public class GenresFilterDto {
    private List<GenreDetailsDto> genres;
}
