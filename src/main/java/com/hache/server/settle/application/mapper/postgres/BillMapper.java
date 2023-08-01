package com.hache.server.settle.application.mapper.postgres;

import com.hache.server.settle.application.dto.BillDto;
import com.hache.server.settle.application.dto.PaymentContainerDto;
import com.hache.server.settle.persistences.postgres.entity.BillEntity;
import com.hache.server.settle.persistences.postgres.entity.PaymentContainerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

//@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
@Mapper(uses = {PaymentContainerMapper.class})
public interface BillMapper {

    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

    @Mapping(target = "listUsersPaid", qualifiedByName = "mapUsersPaidDtoToEntity")
    BillEntity convertDtoToEntity(BillDto dto);

    @Mapping(target = "listUsersPaid", source = "listUsersPaid")
    BillDto convertEntityToDto(BillEntity entity);

    @Named("mapUsersPaidDtoToEntity")
    default Set<PaymentContainerEntity> mapUsersPaidDtoToEntity(Set<PaymentContainerDto> listUsersPaid) {
        return listUsersPaid.stream()
                .map(PaymentContainerMapper.INSTANCE::convertListUsersPaidDtoToEntity)
                .collect(Collectors.toSet());
    }


    //@IterableMapping(elementTargetType = PaymentContainerEntity.class /*, qualifiedByName = "convertPaymentContainerDtoToEntity"*/)
    //@Named("convertListUsersPaidDtoToEntity")
    /*
    Set<PaymentContainerEntity> mapListUsersPaidToEntity(Set<PaymentContainerDto> listUsersPaid);

    //@Named("convertPaymentContainerDtoToEntity")
    PaymentContainerEntity mapPaymentContainerToEntity(PaymentContainerDto dto);
    */
    /*
    @AfterMapping
    default void mapListUserPaidAfterMapping(BillDto dto, @MappingTarget BillEntity bill) {
        bill.setListUsersPaid(mapUsersPaidToEntity(dto.getListUsersPaid()));
    }
    */

            /*
        Set<PaymentContainerEntity> list = new HashSet<>();
        listUsersPaid.forEach(pc -> {
            list.add(PaymentContainerMapper.INSTANCE.convertListUsersPaidDtoToEntity(pc));
        });
        */


}
