package com.br.moviehub.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tv_show_genre")
@Getter
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
}
