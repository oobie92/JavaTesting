package com.diegomendez.javatests.movies.service;

import com.diegomendez.javatests.movies.data.MovieRepository;
import com.diegomendez.javatests.movies.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static com.diegomendez.javatests.movies.model.Genre.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceShould {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Before
    public void setup(){
        movieService = new MovieService(movieRepository);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(
                new Movie(1, "Avengers", 160, ACTION),
                new Movie(2, "Prisoners", 180, THRILLER),
                new Movie(3, "21 Jump Street", 115, COMEDY),
                new Movie(4, "It", 130, HORROR),
                new Movie(5, "22 Jump Street", 122, COMEDY)
        ));
    }

    @Test
    public void return_movies_by_genre() {

        Collection<Movie> movies = movieService.findMoviesByGenre(COMEDY);

        assertThat(getMovieIds(movies), is(Arrays.asList(3,5)));
    }

    @Test
    public void return_movies_by_duration() {

        Collection<Movie> movies = movieService.findMoviesByDuration(130);

        assertThat(getMovieIds(movies), is(Arrays.asList(3, 4, 5)));
    }

    private List<Integer> getMovieIds(Collection<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }
}