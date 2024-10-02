CREATE TABLE tv_show
(
    id       INT UNIQUE NOT NULL ,
    original_name    VARCHAR(255) NOT NULL ,
    original_language VARCHAR(50) NOT NULL,
    overview TEXT ,
    adult TINYINT(1) NOT NULL,
    number_of_episodes INT ,
    number_of_seasons INT,
    backdrop_path VARCHAR(255),
    first_air_date DATE,
    popularity DECIMAL(10,2),
    poster_path VARCHAR(255),
    `status` VARCHAR(100),
    `type` VARCHAR(100),
    vote_average DECIMAL(10,2),
    vote_count INT
);