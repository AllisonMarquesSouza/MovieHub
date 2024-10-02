package com.br.moviehub.dtos.TMDB;

import lombok.Getter;
import java.util.List;

@Getter
public class MovieResultDto {
    private int page;
    private List<MovieDto> results;
    private int total_pages;
    private int total_results;
}
