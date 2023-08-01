package com.hache.server.settle.application.mapper.postgres;

import com.hache.server.settle.application.dto.MeetDto;
import com.hache.server.settle.persistences.postgres.entity.MeetEntity;
import com.hache.server.settle.persistences.postgres.pojo.MeetPojo;
import com.hache.server.settle.persistences.postgres.record.MeetRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetMapper {

    MeetMapper INSTANCE = Mappers.getMapper(MeetMapper.class);

    MeetEntity convertDtoToEntity(final MeetDto dto);

    MeetDto convertEntityToDto(final MeetEntity entity);


}
