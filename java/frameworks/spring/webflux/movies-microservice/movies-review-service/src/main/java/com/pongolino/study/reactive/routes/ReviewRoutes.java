package com.pongolino.study.reactive.routes;

import com.pongolino.study.reactive.routesHandlers.ReviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class ReviewRoutes {

    @Bean
    public RouterFunction<ServerResponse> reviewRouter(ReviewHandler handler) {
        return route()
                .nest(path("/1/reviews"), builder -> {
                    builder
                            .POST("", handler::createReview)
                            .GET("", handler::getAllReviews);
                })
                .build();
    }

}
