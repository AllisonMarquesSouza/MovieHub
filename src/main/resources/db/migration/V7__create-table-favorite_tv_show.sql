CREATE TABLE favorite_tv_show(
    id INT PRIMARY KEY AUTO_INCREMENT,
    created_at DATE NOT NULL,
    user_id INT NOT NULL,
    tv_show_id INT NOT NULL,

    CONSTRAINT fk_user_favorite_tv FOREIGN KEY (user_id) REFERENCES user(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE ,
    CONSTRAINT fk_tv_favorite FOREIGN KEY  (tv_show_id) REFERENCES tv_show(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)