package com.br.moviehub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "genre")
@Getter
public class Genre {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;
}
