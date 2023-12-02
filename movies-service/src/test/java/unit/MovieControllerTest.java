import com.pongolino.study.reactive.application.controller.MovieController;
import com.pongolino.study.reactive.domain.entity.Movie;
import com.pongolino.study.reactive.domain.service.MovieInfoService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = MovieController.class)
@AutoConfigureWebTestClient
class MovieControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private MovieInfoService movieInfoService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @DisplayName("Test for endpoint get all movies info with a mocked return")
    void getAllMoviesInfo() {
        var mocks = List.of(
                new Movie(null, "Movie 1", 2000, List.of("Cast"), LocalDate.of(2023, 11, 30)),
                new Movie(null, "Movie 2", 2021, List.of("Cast 2"), LocalDate.of(2022, 8, 28)),
                new Movie(null, "Movie 3", 1998, List.of("Cast 1", "Cast"), LocalDate.of(2020, 1, 15))
        );
        when(movieInfoService.listAll()).thenReturn(Flux.fromIterable(mocks));

        webTestClient
                .get()
                .uri("/1/movie")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Movie.class)
                .hasSize(3);

    }

    @Test
    @DisplayName("Create a movie info")
    void createNewMovieInfo() {
        var mocks = new Movie(null, "Movie 190", 2000, List.of("Cast"), LocalDate.of(2023, 11, 30));
        when(movieInfoService.save(ArgumentMatchers.isA(Movie.class))).thenReturn(Mono.just(
                // or thenAnswer with setters..
                new Movie("abcd", "Movie 190", 2000, List.of("Cast"), LocalDate.of(2023, 11, 30))
        ));

        webTestClient
                .post()
                .uri("/1/movie")
                .bodyValue(mocks)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Movie.class)
                .consumeWith(created -> {
                    Movie responseBody = created.getResponseBody();
                    Assertions.assertNotNull(responseBody);
                    Assertions.assertEquals("abcd", responseBody.getMovieInfoId());
                });

    }
}