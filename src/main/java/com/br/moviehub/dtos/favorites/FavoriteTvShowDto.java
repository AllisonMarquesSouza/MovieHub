package com.br.moviehub.dtos.favorites;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FavoriteTvShowDto {
    @NotNull(message = "The userId cannot be null")
    private Long userId;

    @NotNull(message = "The tvShowId cannot be null")
    private Long tvShowId;
}
