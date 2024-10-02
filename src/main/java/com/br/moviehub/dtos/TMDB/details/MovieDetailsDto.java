package com.br.moviehub.dtos.TMDB.details;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieDetailsDto {
    private Long id;

    private String title;

    private String original_title;

    private String overview;

    private String original_language;

    private List<GenreDetailsDto> genres;

    private Boolean adult;

    private BigDecimal popularity;

    private String status;

    private LocalDate release_date;

    private BigDecimal vote_average;

    private Integer vote_count;
}
