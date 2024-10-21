package com.br.moviehub.model;

import com.br.moviehub.dtos.TMDB.details.MovieDetailsDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@NoArgsConstructor
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

    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @ManyToMany
    private List<Genre> genres;

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

    public Movie(MovieDetailsDto movieDetailsDto) {
        this.id = movieDetailsDto.getId();
        this.title = movieDetailsDto.getTitle();
        this.originalTitle = movieDetailsDto.getOriginal_title();
        this.overview = movieDetailsDto.getOverview();
        this.originalLanguage = movieDetailsDto.getOriginal_language();
        this.genres = movieDetailsDto.getGenres();
        this.adult = movieDetailsDto.getAdult();
        this.popularity = movieDetailsDto.getPopularity();
        this.status = movieDetailsDto.getStatus();
        this.releaseDate = movieDetailsDto.getRelease_date();
        this.voteAverage = movieDetailsDto.getVote_average();
        this.voteCount = movieDetailsDto.getVote_count();
    }
}
