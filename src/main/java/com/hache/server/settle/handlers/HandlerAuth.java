package com.hache.server.settle.handlers;

import com.hache.server.settle.application.dto.UserDto;
import com.hache.server.settle.persistences.postgres.repository.SettleRepository;
import com.hache.server.settle.security.authentication.JWTUtil;
import com.hache.server.settle.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.hache.server.settle.handlers.HandlerUtils.createErrorResponse;
import static com.hache.server.settle.handlers.HandlerUtils.createSuccessResponse;
import static com.hache.server.settle.security.authentication.JWTUtil.matchesPassword;

@Slf4j
@Component
public class HandlerAuth {

    @Autowired
    private SettleRepository postgresRepository;
    @Autowired
    private UserService userService;

    public Mono<ServerResponse> signUpSettle(final ServerRequest request) {
        System.out.println("En signUP");
        return request.bodyToMono(UserDto.class)
                .flatMap(
                        user -> userService.findUser(user.getEmail(), postgresRepository)
                                .flatMap(userServer -> createErrorResponse("Usuario ya existe."))
                                .switchIfEmpty(userService.createUser(user.getEmail(),
                                                user.getName(),
                                                user.getPassword(),
                                                user.getGuest(),
                                                postgresRepository)
                                        .flatMap(u -> {
                                            String token = JWTUtil.generateToken(u.getEmail(),
                                                    u.getName(),
                                                    u.getPassword(),
                                                    u.getRoles());
                                            u.setToken(token);
                                            return createSuccessResponse(u);
                                        })
                                )
                                .onErrorResume((error) -> {
                                    log.error(">>>>> Error: {}", error.getMessage());
                                    return createErrorResponse(error.getMessage());
                                })
                )
                .onErrorResume((error) -> {
                    log.error(">>>>> Error: {}", error.getMessage());
                    return createErrorResponse(error.getMessage());
                });
    }

    public Mono<ServerResponse> loginSettle(final ServerRequest request) {
        System.out.println("En signIN");
        return request.bodyToMono(UserDto.class)
                .flatMap(
                        user -> userService.findUserAndMeets(user.getEmail(), postgresRepository)
                                .flatMap(u -> {
                                    if (matchesPassword(user.getPassword(), u.getPassword())) {
                                        String token = JWTUtil.generateToken(u.getEmail(),
                                                u.getName(),
                                                u.getPassword(),
                                                u.getRoles());
                                        u.setToken(token);
                                        return createSuccessResponse(u);
                                    } else {
                                        return createErrorResponse("Invalid credencial.");
                                    }
                                })
                                .switchIfEmpty(createErrorResponse("Usuario no existe."))
                                .onErrorResume((error) -> {
                                    log.error(">>>>> Error: {}", error.getMessage());
                                    return createErrorResponse(error.getMessage());
                                })
                )
                .onErrorResume((error) -> {
                    log.error(">>>>> Error: {}", error.getMessage());
                    return createErrorResponse(error.getMessage());
                });
    }

    public Mono<ServerResponse> refreshSettle(final ServerRequest request) {
        return request.bodyToMono(UserDto.class).flatMap(user -> ServerResponse.ok().body(Mono.just("Login"), String.class));
    }


}
