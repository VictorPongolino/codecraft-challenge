import com.pongolino.study.reactive.domain.entity.Movie;
import com.pongolino.study.reactive.domain.repository.MovieInfoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ActiveProfiles("test")
public class MovieRepositoryTest {

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @BeforeEach
    public void beforeEach() {
        var standard = List.of(
                new Movie(null, "Avatar 2", 2023, Arrays.asList("Someone"), LocalDate.of(2022,11,0)),
                new Movie(null, "Avatar 1", 2012, Arrays.asList("Again Someone"), LocalDate.of(2012,11,0)),
                new Movie(null, "Super Mario", 2023, Arrays.asList("Nitendo"), LocalDate.of(2023,1,0))
        );

        movieInfoRepository.saveAll(standard).blockLast();
    }

    @AfterEach
    public void afterEach() {
        movieInfoRepository.deleteAll().block();
    }

    @Test
    @DisplayName("Reinforcing usage of StepVerifier, just learning purpose.")
    public void checkFindAll() {
        var movies = movieInfoRepository.findAll().log();
        StepVerifier.create(movies)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    @DisplayName("Reinforcing usage of StepVerifier, findById.")
    public void checkForMonoById() {
        var movies = movieInfoRepository.findById("abc");
        StepVerifier.create(movies)
            .assertNext(data -> {
                assertEquals("Avatar 1", data.getName());
            })
            .verifyComplete();
    }

    @Test
    public void insertTest() {
        Movie movie = new Movie(null, "Hello", 2023, List.of("Victor H.P"), LocalDate.of(2023, 11, 22));
        Mono<Movie> newMono = movieInfoRepository.save(movie).log();
        StepVerifier.create(newMono)
            .assertNext(data -> {
                assertNotNull(data.getMovieInfoId());
                assertEquals("Hello", data.getName());
            })
            .verifyComplete();
    }

}
