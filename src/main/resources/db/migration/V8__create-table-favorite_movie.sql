CREATE TABLE favorite_movie(
    id INT PRIMARY KEY AUTO_INCREMENT,
    created_at DATE NOT NULL,
    user_id INT NOT NULL,
    movie_id INT NOT NULL,

    CONSTRAINT fk_user_favorite_movie FOREIGN KEY (user_id) REFERENCES user(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE ,
    CONSTRAINT fk_movie_favorite FOREIGN KEY  (movie_id) REFERENCES movie(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)