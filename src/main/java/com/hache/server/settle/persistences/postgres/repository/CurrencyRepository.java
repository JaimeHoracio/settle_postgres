package com.hache.server.settle.persistences.postgres.repository;

import com.hache.server.settle.application.dto.CurrencyDto;
import com.hache.server.settle.persistences.ICurrencyPersist;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CurrencyRepository implements ICurrencyPersist {
    @Override
    public Mono<CurrencyDto> saveCurrency(String code, String name, Integer num, String country) {
        return null;
    }

    @Override
    public Flux<CurrencyDto> getAllCurrency() {
        return null;
    }
}
