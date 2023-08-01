package com.hache.server.settle.application.mapper.postgres;

import com.hache.server.settle.application.dto.UserDto;
import com.hache.server.settle.persistences.postgres.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "token", ignore = true),
            //@Mapping(target = "settle", expression = "java(convertListMeet(entity.getListMeet()))")
            @Mapping(target = "settle.listMeet", source = "listMeet")
    })
    UserDto convertEntityToDto(final UserEntity entity);

    /*
    default SettleDto convertListMeet(final List<MeetEntity> listEntity) {
        SettleDto settle = new SettleDto();
        settle.setListMeet(listEntity));
        return settle;
    }
    */

}
