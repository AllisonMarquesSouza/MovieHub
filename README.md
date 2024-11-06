# Movie Hub API

This API integrates with The Movie Database (TMDB) to provide movie information, allowing users to interact with movies, TV shows, watchlists, and lists of favorites.

## Technologies used

- **Java 21**: Language of Programming.
- **Spring Boot 3.3.4**: Framework to build Applications Java.
- **JWT Token**: Token for access.
- **Bcrypt**: Encrypt the password.
- **MySQL**: DataBase relational.
- **Flyway**: Management of migrations of database tables.
- **Lombok**: The Movie Hub will reduce the code in some parts.
- **OpenAPI (Swagger)**: To document the API.

## Main Features API
1. **Discover**: Filter movies and TV shows based on various criteria (language, region, genres, release Date, year, etc.).
2. **Search**: Search movies and tv shows from extern API TMDB based on(Top rated, popular, Upcoming, etc.) 
3. **Create user**: Create a user to manage API requests.
4. **Favorites | Watchlist**: Manage user-specific for (add, search, remove, update) movies or TV shows in the favorite list or watchlist.


## How to Run the API
## Requirements

#### Java 21 Installed.
#### Git Installed
#### Docker: Required to run MySQL database with Docker Compose.
#### Docker Compose: Required to orchestrate the database container.

## Steps to run
#### 1. Clone the repository**:
```sh
https://github.com/AllisonMarquesSouza/MovieHub.git
```

#### 2. Go to the directory project and open the Idea of your preference:
```sh
cd <DIRETORIO_DO_PROJETO>
```

#### 2. Rename the application-example.properties to application.properties and get your API Key of TMDB in:	
```sh
https://www.themoviedb.org/signup
```

#### 3. Edit the environment variable APIKEY in application.properties and put your API Key from TMDB:	
```sh
APIKEY=your-api-key-from-tmdb
```

#### 4. Run the docker-compose in the terminal inside the directory of the project, and wait to load the image of a database:
OBS: You can change the user, password, network, or anything. Remember if you change something, then uptade in the properties of the project.
```sh
docker compose up -d
```

#### 4. Run the application in Idea and go to URL:
```sh
http://localhost:8080/swagger-ui/index.html#/ 
```

#### From this, all endpoints are available there.


