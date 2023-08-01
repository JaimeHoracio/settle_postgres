package com.hache.server.settle.handlers;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import com.hache.server.settle.application.dto.ErrorDto;

public class HandlerUtils {

    public static Mono<ServerResponse> createErrorResponse(final String msg) {
        return ServerResponse.badRequest()
                .body(Mono.just(ErrorDto.builder()
                                .message(msg)
                                .codeError(0).build())
                        , ErrorDto.class);
    }

    public static <T> Mono<ServerResponse> createSuccessResponse(final T msg) {
        return ServerResponse.ok().body(Mono.just(msg), msg.getClass());
    }
}
