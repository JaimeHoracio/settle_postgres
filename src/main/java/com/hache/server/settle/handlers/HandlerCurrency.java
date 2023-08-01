package com.hache.server.settle.handlers;

import com.hache.server.settle.persistences.postgres.repository.CurrencyRepository;
import com.hache.server.settle.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.hache.server.settle.handlers.HandlerUtils.createErrorResponse;
import static com.hache.server.settle.handlers.HandlerUtils.createSuccessResponse;

@Component
@RequiredArgsConstructor
public class HandlerCurrency {

    private final CurrencyRepository currencyRepository;
    private final CurrencyService currencyService;

    public Mono<ServerResponse> saveCurrencyIso(final String code, final String name, final Integer num, final String country) {
        return currencyService.saveCurrency(code, name, num, country, currencyRepository)
                .flatMap(currency -> createSuccessResponse(currency))
                .onErrorResume((error) -> createErrorResponse("Error al guardar la moneda" + error.getMessage()));
    }

    public Mono<ServerResponse> getAllCurrency(ServerRequest request) {
        System.out.println("En Currency All");
        return currencyService.getAllCurrency(currencyRepository)
                .collectList()
                .flatMap(currency -> createSuccessResponse(currency))
                .onErrorResume((error) -> createErrorResponse("Error al obtener la moneda" + error.getMessage()));
    }
}
