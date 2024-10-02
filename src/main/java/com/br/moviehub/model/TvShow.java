package com.br.moviehub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tv_show")
public class TvShow {
    @Id
    private Long id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "overview")
    private String overview;

    @Column(name = "adult")
    private Boolean adult;

    @Column(name = "number_of_episodes")
    private Integer numberOfEpisodes;

    @Column(name = "number_of_seasons")
    private Integer numberOfSeasons;

    @Column(name = "backdrop_path")
    private String backdropPath;

    @Column(name = "first_air_date")
    private LocalDate firstAirDate;

    @Column(name = "popularity")
    private BigDecimal popularity;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "vote_average")
    private BigDecimal voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

}
