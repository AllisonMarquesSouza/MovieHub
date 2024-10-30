package com.br.moviehub.dtos.watchlistMovie;

import com.br.moviehub.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteWatchlistByStatusDto {
    @NotNull(message = "The userId cannot be null")
    private Long userId;
    @NotNull(message = "The status cannot be null")
    @Enumerated(EnumType.STRING)
    private Status status;
}
