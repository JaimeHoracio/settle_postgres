package com.hache.server.settle.persistences;

import com.hache.server.settle.application.dto.CurrencyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICurrencyPersist {

    Mono<CurrencyDto> saveCurrency(String code, String name, Integer num, String country);

    Flux<CurrencyDto> getAllCurrency();
}
