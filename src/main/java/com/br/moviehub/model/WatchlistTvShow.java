package com.br.moviehub.model;

import com.br.moviehub.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "watchlist_tv_show")
@Getter
@Setter
@NoArgsConstructor
public class WatchlistTvShow {
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
    private User user;

    @ManyToOne
    @JoinColumn(name = "tv_show_id")
    private TvShow tvShow;

    public WatchlistTvShow(User user, TvShow tvShowToSave) {
        this.user = user;
        this.tvShow = tvShowToSave;
        this.creationDate = LocalDate.now();
        this.status = Status.NOT_STARTED;
    }
}
