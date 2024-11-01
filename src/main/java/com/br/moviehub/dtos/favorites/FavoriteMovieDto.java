package com.br.moviehub.dtos.favorites;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FavoriteMovieDto {
    @NotNull(message = "The userId cannot be null")
    private Long userId;
    @NotNull(message = "The movieId cannot be null")
    private Long movieId;
}
