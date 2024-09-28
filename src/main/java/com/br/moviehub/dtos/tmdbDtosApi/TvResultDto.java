package com.br.moviehub.dtos.tmdbDtosApi;

import lombok.Getter;

import java.util.List;

@Getter
public class TvResultDto {
    private int page;
    private List<TMDbTvDto> results;
    private int total_pages;
    private int total_results;
}
