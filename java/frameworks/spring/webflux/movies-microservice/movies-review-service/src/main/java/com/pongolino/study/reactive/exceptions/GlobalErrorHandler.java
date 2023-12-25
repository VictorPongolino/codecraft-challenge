package com.pongolino.study.reactive.exceptions;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;

@Component
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (ex instanceof ConstraintViolationException) {
            overrideBodyMessageWithBadRequestStatusCode(ex, response);
        }

        if (ex instanceof NotFoundException) {
            overrideBodyMessageWithBadRequestStatusCode(ex, response);
        }

        return null;
    }

    private void overrideBodyMessageWithBadRequestStatusCode(Throwable ex, ServerHttpResponse response) {
        response.writeWith(Mono.just(getDataBufferWrappedExceptionMessage(ex.getMessage(), response)));
        response.setStatusCode(HttpStatus.BAD_REQUEST);
    }

    private DataBuffer getDataBufferWrappedExceptionMessage(String message, ServerHttpResponse response) {
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        return dataBufferFactory.wrap(message.getBytes());
    }
}
