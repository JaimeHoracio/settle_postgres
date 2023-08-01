package com.hache.server.settle.application.mapper.postgres;

import com.hache.server.settle.application.dto.PaymentDto;
import com.hache.server.settle.persistences.postgres.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentEntity mapDtoToEntity(PaymentDto dto);
    PaymentDto mapEntityToDto(PaymentEntity entity);

}
