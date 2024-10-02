package com.br.moviehub.service;

import com.br.moviehub.dtos.TMDB.details.MovieDetailsDto;
import com.br.moviehub.dtos.TMDB.details.TvShowDetailsDto;
import com.br.moviehub.dtos.TMDB.filters.MovieResultDto;
import com.br.moviehub.dtos.TMDB.filters.GenresFilterDto;
import com.br.moviehub.dtos.TMDB.filters.TvShowResultDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TmdbService {
    @Value("${APIKEY}")
    private String apiKey;

    private <T> T doRequest(String url, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                responseType
        );

        return response.getBody();
    }

    public List<?> getAllLanguages() {
        String url = String.format("https://api.themoviedb.org/3/configuration/languages?api_key=%s", apiKey);
        return doRequest(url, List.class);
    }

    public GenresFilterDto getAllGenresMovie() {
        String url =String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=%s", apiKey);
        return doRequest(url, GenresFilterDto.class);
    }

    public GenresFilterDto getAllGenresTv() {
        String url =String.format("https://api.themoviedb.org/3/genre/tv/list?api_key=%s", apiKey);
        return doRequest(url, GenresFilterDto.class);
    }

    public MovieDetailsDto getMovieById(Long id){
        String url =String.format("https://api.themoviedb.org/3/movie/%d?api_key=%s", id ,apiKey);
        try{
            return doRequest(url, MovieDetailsDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found, check the ID.");

        } catch (HttpClientErrorException.BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request. Please check the input.");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching movie details.");
        }
    }

    public TvShowDetailsDto getTvById(Long id){
        String url =String.format("https://api.themoviedb.org/3/tv/%d?api_key=%s", id ,apiKey);
        try{
            return doRequest(url, TvShowDetailsDto.class);

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tv not found, check the ID.");

        } catch (HttpClientErrorException.BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request. Please check the input.");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching tv details.");
        }
    }

    public MovieResultDto getDiscoverMovie(Boolean adult, String language, Integer primary_release_year, String region,
                                           LocalDate release_date_gte, Float vote_average_gte, String with_genres,
                                           String with_keywords, String with_origin_country, String without_genres,
                                           String without_keywords, Integer year) {

        String url3 = String.format(
                "https://api.themoviedb.org/3/discover/movie?api_key=%s&include_adult=%s&include_video=false&language=%s&page=1&sort_by=popularity.desc" +
                        "&primary_release_year=%d&region=%s&release_date.gte=%tF&vote_average.gte=%.1f&with_genres=%s" +
                        "&with_keywords=%s&with_origin_country=%s&without_genres=%s&without_keywords=%s&year=%d",
                apiKey, adult, language, primary_release_year, region, release_date_gte, vote_average_gte, with_genres,
                with_keywords, with_origin_country, without_genres, without_keywords, year
        );

        return doRequest(url3, MovieResultDto.class);
    }

    public MovieResultDto getPopularMovies(String language) {
        String url = String.format("https://api.themoviedb.org/3/movie/popular?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, MovieResultDto.class);
    }

    public MovieResultDto getNowPlayingMovie(String language) {
        String url = String.format("https://api.themoviedb.org/3/movie/now_playing?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, MovieResultDto.class);
    }

    public MovieResultDto getTopRatedMovies(String language) {
        String url = String.format("https://api.themoviedb.org/3/movie/top_rated?api_key=%s&language=%s&page=1&page=1", apiKey, language);
        return doRequest(url, MovieResultDto.class);
    }

    public MovieResultDto getUpcomingMovies(String language) {
        String url = String.format("https://api.themoviedb.org/3/movie/upcoming?api_key=%s&language=%s&page=1&page=1", apiKey, language);
        return doRequest(url, MovieResultDto.class);
    }

    //Tv lists
    public TvShowResultDto getDiscoverTv(LocalDate air_date_gte, Integer first_air_date_year, LocalDate first_air_date_gte,
                                         Boolean include_adult, String language, Float vote_average_gte, Float vote_count_gte,
                                         String with_genres, String with_keywords, String with_origin_country, String with_original_language,
                                         String without_genres, String without_keywords) {
        String url = String.format(
                "https://api.themoviedb.org/3/discover/tv?api_key=%s&air_date.gte=%tF&include_adult=%s&language=%s&page=1&sort_by=popularity.desc" +
                        "&first_air_date_year=%d&first_air_date.gte=%tF&vote_average.gte=%.1f&vote_count.gte=%.1f&with_genres=%s" +
                        "&with_keywords=%s&with_origin_country=%s&with_original_language=%s&without_genres=%s&without_keywords=%s",
                apiKey, air_date_gte, include_adult, language, first_air_date_year, first_air_date_gte, vote_average_gte,
                vote_count_gte, with_genres, with_keywords, with_origin_country, with_original_language, without_genres,
                without_keywords
        );

        return doRequest(url, TvShowResultDto.class);
    }

    public TvShowResultDto getAiringTodayTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/airing_today?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvShowResultDto.class);
    }

    public TvShowResultDto getPopularTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/popular?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvShowResultDto.class);
    }

    public TvShowResultDto getTopRatedTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/top_rated?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvShowResultDto.class);
    }

    public TvShowResultDto getOnTheAirTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/on_the_air?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvShowResultDto.class);
    }
}


