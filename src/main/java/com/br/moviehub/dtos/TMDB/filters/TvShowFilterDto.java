package com.br.moviehub.dtos.TMDB.filters;

import lombok.Getter;

import java.util.List;

@Getter
public class TvShowDto {
    private Boolean adult;
    private String backdrop_path;
    private String first_air_date;
    private List<Integer> genre_ids;
    private Integer id;
    private String name;
    private List<String> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private Float popularity;
    private String poster_path;
    private Float vote_average;
    private Integer vote_count;
}
