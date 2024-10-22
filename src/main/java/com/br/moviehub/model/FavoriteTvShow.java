package com.br.moviehub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "favorite_tv_show")
@Getter
@NoArgsConstructor
public class FavoriteTvShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tv_show_id")
    private TvShow tvShow;

    public FavoriteTvShow(User user, TvShow tvShow) {
        this.user = user;
        this.tvShow = tvShow;
        this.creationDate = LocalDate.now();
    }
}
