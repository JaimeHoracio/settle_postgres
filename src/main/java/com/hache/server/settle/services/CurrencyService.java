package com.hache.server.settle.services;

import com.hache.server.settle.application.mapper.postgres.CurrencyMapper;
import com.hache.server.settle.persistences.ICurrencyPersist;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.hache.server.settle.application.dto.CurrencyDto;

@Service
public class CurrencyService {

    public Mono<CurrencyDto> saveCurrency(final String code, final String name, final Integer num,
                                          final String country, final ICurrencyPersist currencyRepository) {
        return currencyRepository.saveCurrency(code, name, num, country);
    }

    public Flux<CurrencyDto> getAllCurrency(final ICurrencyPersist currencyRepository) {
        return currencyRepository.getAllCurrency();
    }

}
