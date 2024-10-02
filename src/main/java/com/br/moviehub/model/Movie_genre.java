package com.br.moviehub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_genre")
public class Movie_genre {
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
