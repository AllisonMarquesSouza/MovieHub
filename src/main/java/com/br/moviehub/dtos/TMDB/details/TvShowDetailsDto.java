package com.br.moviehub.dtos.TMDB.details;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class TvShowDetailsDto {

    private Long id;

    private String original_name;

    private String original_language;

    private String overview;

    private List<GenreDetailsDto> genres;

    private Boolean adult;

    private Integer number_of_episodes;

    private Integer number_of_seasons;

    private String backdrop_path;

    private LocalDate first_air_date;

    private BigDecimal popularity;

    private String poster_path;

    private String status;

    private String type;

    private BigDecimal vote_average;

    private Integer vote_count;
}
