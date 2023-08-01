package com.hache.server.settle.application.mapper.postgres;

import com.hache.server.settle.application.dto.CurrencyDto;
import com.hache.server.settle.persistences.postgres.entity.CurrencyISOEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {

    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    //@Mapping(source = "codeISO", target = "code")
    //@Mapping(source = "numISO", target = "num")
    CurrencyDto convertEntityToDto(final CurrencyISOEntity currencyDomain);


}
