package com.br.moviehub.dtos.watchlistMovie;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WatchlistMovieDto {
    @NotNull(message = "The userId cannot be null")
    private Long userId;
    @NotNull(message = "The movieId cannot be null")
    private Long movieId;
}
