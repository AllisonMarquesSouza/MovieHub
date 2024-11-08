package com.br.moviehub.model;

import com.br.moviehub.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "watchlist_movie")
@Getter
@Setter
@NoArgsConstructor
public class WatchlistMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public WatchlistMovie(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.creationDate = LocalDate.now();
        this.status = Status.NOT_STARTED;
    }
}



