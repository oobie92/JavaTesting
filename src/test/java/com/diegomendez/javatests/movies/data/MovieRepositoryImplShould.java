package com.diegomendez.javatests.movies.data;

import com.diegomendez.javatests.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static com.diegomendez.javatests.movies.model.Genre.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryImplShould {

    @InjectMocks
    MovieRepositoryImpl movieRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    private DriverManagerDataSource driverManagerDataSource;

    @Before
    public void setUp() throws Exception {
        // Database in-memory
        driverManagerDataSource = new DriverManagerDataSource(
                "jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa"
        );
        jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
        movieRepository = new MovieRepositoryImpl(jdbcTemplate);

        // Insert test data into the database
        ScriptUtils.executeSqlScript(
                driverManagerDataSource.getConnection(),
                new ClassPathResource("sql-scripts/test-data.sql")
        );
    }

    @After
    public void tearDown() throws Exception {
        final Statement s = driverManagerDataSource.getConnection().createStatement();
        s.execute("DROP ALL objects DELETE files");
    }

    @Test
    public void load_all_movies() {
        Collection<Movie> movies = movieRepository.findAll();
        assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, ACTION),
                new Movie(2, "Memento", 113, THRILLER),
                new Movie(3, "Matrix", 136, ACTION)
        )));
    }

    @Test
    public void find_movie_by_id() {

        Movie byId = movieRepository.findById(2);

        assertThat(byId, is(new Movie(2, "Memento", 113, THRILLER)));
    }

    @Test
    public void insert_movie() {
        Movie movie = new Movie("Prisoners", 180, THRILLER);

        movieRepository.saveOrUpdate(movie);

        Movie fromDb = movieRepository.findById(4);

        assertThat(fromDb, is(new Movie(4,"Prisoners", 180, THRILLER)));
    }
}