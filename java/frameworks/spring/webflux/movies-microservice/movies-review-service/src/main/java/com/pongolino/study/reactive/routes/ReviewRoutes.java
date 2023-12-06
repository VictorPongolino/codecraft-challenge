package com.pongolino.study.reactive.routes;

import com.pongolino.study.reactive.routesHandlers.ReviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class ReviewRoutes {

    @Bean
    public RouterFunction reviewRouter(ReviewHandler handler) {
        return route()
                .GET("/1/reviews/{id}", handler::createReview)
                .build();
    }

}
