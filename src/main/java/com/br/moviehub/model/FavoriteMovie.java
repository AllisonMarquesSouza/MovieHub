package com.br.moviehub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "favorite_movie")
@Getter
@NoArgsConstructor
public class FavoriteMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public FavoriteMovie(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.creationDate = LocalDate.now();
    }
}
