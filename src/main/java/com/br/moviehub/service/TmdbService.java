package com.br.moviehub.service;

import com.br.moviehub.dtos.tmdbDtosApi.MovieResultDto;
import com.br.moviehub.dtos.tmdbDtosApi.TMDbGenresDto;
import com.br.moviehub.dtos.tmdbDtosApi.TvResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        String url =String.format("https://api.themoviedb.org/3/configuration/languages?api_key=%s", apiKey);
        return doRequest(url, List.class);
    }

    public TMDbGenresDto getAllGenresMovie() {
        String url =String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=%s", apiKey);
        return doRequest(url, TMDbGenresDto.class);
    }

    public TMDbGenresDto getAllGenresTv() {
        String url =String.format("https://api.themoviedb.org/3/genre/tv/list?api_key=%s", apiKey);
        return doRequest(url, TMDbGenresDto.class);
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
    public TvResultDto getDiscoverTv(LocalDate air_date_gte, Integer first_air_date_year, LocalDate first_air_date_gte,
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

        return doRequest(url, TvResultDto.class);
    }

    public TvResultDto getAiringTodayTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/airing_today?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvResultDto.class);
    }

    public TvResultDto getPopularTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/popular?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvResultDto.class);
    }

    public TvResultDto getTopRatedTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/top_rated?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvResultDto.class);
    }

    public TvResultDto getOnTheAirTv(String language) {
        String url = String.format("https://api.themoviedb.org/3/tv/on_the_air?api_key=%s&language=%s&page=1", apiKey, language);
        return doRequest(url, TvResultDto.class);
    }
}


