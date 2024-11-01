package com.br.moviehub.dtos.watchlistMovie;

import com.br.moviehub.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WatchlistMovieUpdateDto {
    @NotNull(message = "The userId cannot be null")
    private Long userId;
    @NotNull(message = "The movieId cannot be null")
    private Long movieId;
    @NotNull(message = "The status cannot be null")
    private Status status;
}
