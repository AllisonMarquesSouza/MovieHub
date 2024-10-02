CREATE TABLE movie_genre
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,

    CONSTRAINT fk_movie_genre FOREIGN KEY (movie_id) REFERENCES movie(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE ,
    CONSTRAINT fk_genre_movie FOREIGN KEY (genre_id) REFERENCES genre(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);