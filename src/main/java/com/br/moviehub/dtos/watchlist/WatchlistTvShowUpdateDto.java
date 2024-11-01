package com.br.moviehub.dtos.watchlist;

import com.br.moviehub.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WatchlistTvShowUpdateDto {
    @NotNull(message = "The userId cannot be null")
    private Long userId;
    @NotNull(message = "The tvShowId cannot be null")
    private Long tvShowId;
    @NotNull(message = "The status cannot be null")
    private Status status;
}
