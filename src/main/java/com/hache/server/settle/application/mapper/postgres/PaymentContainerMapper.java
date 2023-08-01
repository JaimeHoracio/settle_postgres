package com.hache.server.settle.application.mapper.postgres;

import com.hache.server.settle.application.dto.PaymentContainerDto;
import com.hache.server.settle.application.dto.PaymentDto;
import com.hache.server.settle.persistences.postgres.entity.PaymentContainerEntity;
import com.hache.server.settle.persistences.postgres.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(uses = {PaymentMapper.class})
public interface PaymentContainerMapper {

    PaymentContainerMapper INSTANCE = Mappers.getMapper(PaymentContainerMapper.class);

    @Mapping(target = "idPaymentContainer", source = "idPaymentContainer", defaultExpression = "java(PaymentContainerMapper.INSTANCE.mapPaymentIdNull(dto.getIdPaymentContainer()))")
    @Mapping(target = "listUsersDebt", qualifiedByName = "mapUsersDebtDtoToEntity")
    PaymentContainerEntity convertListUsersPaidDtoToEntity(PaymentContainerDto dto);

    PaymentContainerDto convertListUsersPaidEntityToDto(PaymentContainerEntity entity);

    Set<PaymentContainerEntity> convertPaymentContainerDtoToEntity(Set<PaymentContainerDto> list);

    Set<PaymentContainerDto> convertPaymentContainerEntityToDto(Set<PaymentContainerEntity> list);

    @Named("mapPaymentIdNull")
    default String mapPaymentIdNull(String idPaymentContainer) {
        return Objects.nonNull(idPaymentContainer) ? idPaymentContainer : UUID.randomUUID().toString();
    }

    @Named("mapUsersDebtDtoToEntity")
    default Set<PaymentEntity> mapUsersDebtDtoToEntity(Set<PaymentDto> listUsersDebt) {
        return listUsersDebt.stream()
                .map(PaymentMapper.INSTANCE::mapDtoToEntity)
                .collect(Collectors.toSet());
    }

}
