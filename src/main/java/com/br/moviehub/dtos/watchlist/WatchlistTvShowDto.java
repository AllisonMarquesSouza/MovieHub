package com.br.moviehub.dtos.watchlist;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WatchlistTvShowDto {
    @NotNull(message = "The userId can't be null")
    private Long userId;

    @NotNull(message = "The tvShowId can't be null")
    private Long tvShowId;
}
