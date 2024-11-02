package com.br.moviehub.service;

import com.br.moviehub.dtos.TMDB.details.MovieDetailsDto;
import com.br.moviehub.dtos.TMDB.details.TvShowDetailsDto;
import com.br.moviehub.dtos.TMDB.filters.GenresFilterDto;
import com.br.moviehub.dtos.TMDB.filters.MovieResultDto;
import com.br.moviehub.dtos.TMDB.filters.TvShowResultDto;
import com.br.moviehub.exception.personalizedExceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

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
            throw new EntityNotFoundException("Movie not found, check the ID.");

        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Invalid request. Please check the input.");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching movie details.");
        }
    }

    public TvShowDetailsDto getTvById(Long id){
        String url =String.format("https://api.themoviedb.org/3/tv/%d?api_key=%s", id ,apiKey);
        try{
            return doRequest(url, TvShowDetailsDto.class);

        } catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException("Tv not found, check the ID.");

        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Invalid request. Please check the input.");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching tv details.");
        }
    }

    public MovieResultDto getDiscoverMovie(Boolean adult, String language, Integer primary_release_year, String region,
                                           LocalDate release_date_gte, Float vote_average_gte, String with_genres,
                                           String with_keywords, String with_origin_country, String without_genres,
                                           String without_keywords, Integer year) {

        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/discover/movie")
                .queryParam("api_key", apiKey)
                .queryParam("include_adult", adult)
                .queryParam("include_video", false)
                .queryParam("language", language)
                .queryParam("page", 1)
                .queryParam("sort_by", "popularity.desc")
                .queryParam("primary_release_year", primary_release_year)
                .queryParam("region", region)
                .queryParam("release_date.gte", release_date_gte)
                .queryParam("vote_average.gte", vote_average_gte)
                .queryParam("with_genres", with_genres)
                .queryParam("with_keywords", with_keywords)
                .queryParam("with_origin_country", with_origin_country)
                .queryParam("without_genres", without_genres)
                .queryParam("without_keywords", without_keywords)
                .queryParam("year", year)
                .toUriString();

        return doRequest(url, MovieResultDto.class);
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

        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/discover/tv")
                .queryParam("api_key", apiKey)
                .queryParam("air_date.gte", air_date_gte)
                .queryParam("include_adult", include_adult)
                .queryParam("language", language)
                .queryParam("page", 1)
                .queryParam("sort_by", "popularity.desc")
                .queryParam("first_air_date_year", first_air_date_year)
                .queryParam("first_air_date.gte", first_air_date_gte)
                .queryParam("vote_average.gte", vote_average_gte)
                .queryParam("vote_count.gte", vote_count_gte)
                .queryParam("with_genres", with_genres)
                .queryParam("with_keywords", with_keywords)
                .queryParam("with_origin_country", with_origin_country)
                .queryParam("with_original_language", with_original_language)
                .queryParam("without_genres", without_genres)
                .queryParam("without_keywords", without_keywords)
                .toUriString();

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


