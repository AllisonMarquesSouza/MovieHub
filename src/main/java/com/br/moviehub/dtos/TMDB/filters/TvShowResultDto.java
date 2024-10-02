package com.br.moviehub.dtos.TMDB;

import lombok.Getter;

import java.util.List;

@Getter
public class TvShowResultDto {
    private int page;
    private List<TvShowDto> results;
    private int total_pages;
    private int total_results;
}
