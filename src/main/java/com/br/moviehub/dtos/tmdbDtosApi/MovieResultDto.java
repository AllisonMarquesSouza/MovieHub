package com.br.moviehub.dtos.tmdbDtosApi;

import lombok.Getter;
import java.util.List;

@Getter
public class MovieResultDto {
    private int page;
    private List<TMDbMovieDto> results;
    private int total_pages;
    private int total_results;
}
