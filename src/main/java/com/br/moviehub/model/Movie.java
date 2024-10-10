package com.br.moviehub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movie")
@Getter
public class Movie {
    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "overview")
    private String overview;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "adult")
    private Boolean adult;

    @Column(name = "popularity")
    private BigDecimal popularity;

    @Column(name = "status")
    private String status;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "vote_average")
    private BigDecimal voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;
}
