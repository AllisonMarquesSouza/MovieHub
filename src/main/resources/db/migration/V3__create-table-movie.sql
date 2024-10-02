CREATE TABLE movie
(
    id       INT UNIQUE NOT NULL ,
    title VARCHAR(255) NOT NULL ,
    original_title    VARCHAR(255) NOT NULL ,
    overview TEXT ,
    original_language VARCHAR(50),
    adult TINYINT(1) NOT NULL DEFAULT 0,
    popularity DECIMAL(10,2),
    `status` VARCHAR(100),
    release_date DATE ,
    vote_average DECIMAL(10,2),
    vote_count INT
);