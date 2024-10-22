package com.br.moviehub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tv_show_genre")
@Getter
@NoArgsConstructor
public class TvShowGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tv_show_id")
    private TvShow tvShow;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public TvShowGenre(TvShow tvShow, Genre genre) {
        this.tvShow = tvShow;
        this.genre = genre;
    }
}
