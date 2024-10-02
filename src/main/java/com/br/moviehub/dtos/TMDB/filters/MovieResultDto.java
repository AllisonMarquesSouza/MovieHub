package com.br.moviehub.dtos.TMDB.filters;

import lombok.Getter;
import java.util.List;

@Getter
public class MovieResultDto {
    private int page;
    private List<MovieFilterDto> results;
    private int total_pages;
    private int total_results;
}
