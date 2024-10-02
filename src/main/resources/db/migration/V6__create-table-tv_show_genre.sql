CREATE TABLE tv_show_genre
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    tv_show_id INT NOT NULL,
    genre_id INT NOT NULL,

    CONSTRAINT fk_tv_genre FOREIGN KEY (tv_show_id) REFERENCES tv_show(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE ,
    CONSTRAINT fk_genres_tv FOREIGN KEY (genre_id) REFERENCES genre(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);