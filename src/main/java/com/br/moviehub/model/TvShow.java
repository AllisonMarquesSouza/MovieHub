package com.br.moviehub.model;

import com.br.moviehub.dtos.TMDB.details.TvShowDetailsDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tv_show")
@Getter
@NoArgsConstructor
public class TvShow {
    @Id
    private Long id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "overview")
    private String overview;

    @JoinTable(
            name = "tv_show_genre",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @ManyToMany
    private List<Genre> genres;

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

    public TvShow(TvShowDetailsDto tvShowDetails) {
        this.id = tvShowDetails.getId();
        this.originalName = tvShowDetails.getOriginal_name();
        this.originalLanguage = tvShowDetails.getOriginal_language();
        this.overview = tvShowDetails.getOverview();
        this.genres = tvShowDetails.getGenres();
        this.adult = tvShowDetails.getAdult();
        this.numberOfEpisodes = tvShowDetails.getNumber_of_episodes();
        this.numberOfSeasons = tvShowDetails.getNumber_of_seasons();
        this.backdropPath = tvShowDetails.getBackdrop_path();
        this.firstAirDate = tvShowDetails.getFirst_air_date();
        this.popularity = tvShowDetails.getPopularity();
        this.posterPath = tvShowDetails.getPoster_path();
        this.status = tvShowDetails.getStatus();
        this.type = tvShowDetails.getType();
        this.voteAverage = tvShowDetails.getVote_average();
        this.voteCount = tvShowDetails.getVote_count();
    }
}
