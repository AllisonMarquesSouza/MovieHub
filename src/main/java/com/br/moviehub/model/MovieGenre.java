package com.br.moviehub.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "movie_genre")
@Getter
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
