package com.br.moviehub.dtos.TMDB.filters;

import lombok.Getter;

import java.util.List;

@Getter
public class TvShowResultDto {
    private int page;
    private List<TvShowFilterDto> results;
    private int total_pages;
    private int total_results;
}
