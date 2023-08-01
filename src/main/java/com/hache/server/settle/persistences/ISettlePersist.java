package com.hache.server.settle.persistences;

import com.hache.server.settle.application.dto.BillDto;
import com.hache.server.settle.application.dto.MeetDto;
import com.hache.server.settle.application.dto.UserDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ISettlePersist {

    Mono<UserDto> findUser(final String idUser);

    Mono<UserDto> findUserAndMeets(final String idUser);

    Mono<UserDto> createUser(final String email, final String name, final String password, final Boolean guest, final List<String> roles);

    Mono<MeetDto> selectMeetSettle(final String idMeet);

    Mono<MeetDto> addMeetSettle(final String email, final MeetDto meet);

    Mono<MeetDto> updateMeetSettle(final String email, final MeetDto meet);

    Mono<MeetDto> closeMeetSettle(final String email, final String idMeet);

    Mono<MeetDto> removeMeetSettle(final String email, final String idMeet);

    Mono<BillDto> addBillListMeetSettle(final String email, final String idMeet, final BillDto bill);

    Mono<BillDto> updateBillListMeetSettle(final String email, final String idMeet, final BillDto bill);

    Mono<BillDto> removeBillListMeetSettle(final String email, final String idMeet, final String idBill);


}
