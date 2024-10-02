package com.br.moviehub.dtos.TMDB.filters;

import lombok.Getter;

import java.util.List;

@Getter
public class MovieFilterDto {
    private int id;
    private Boolean adult;
    private String title;
    private String original_title;
    private String original_language;
    private String overview;
    private double popularity;
    private String release_date;
    private double vote_average;
    private int vote_count;
    private List<Integer> genre_ids;
}