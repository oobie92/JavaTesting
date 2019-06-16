package com.diegomendez.javatests.movies.service;

import com.diegomendez.javatests.movies.data.MovieRepository;
import com.diegomendez.javatests.movies.model.Genre;
import com.diegomendez.javatests.movies.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {

        return movieRepository.findAll().stream().filter(movie -> movie.getGenre() == genre)
                .collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByDuration(int length) {
        return movieRepository.findAll().stream().filter(movie -> movie.getMinutes() <= length)
                .collect(Collectors.toList());
    }
}
