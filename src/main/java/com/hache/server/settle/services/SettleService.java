package com.hache.server.settle.services;

import com.hache.server.settle.application.dto.BillDto;
import com.hache.server.settle.application.dto.MeetDto;
import com.hache.server.settle.application.dto.UserDto;
import com.hache.server.settle.persistences.ISettlePersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SettleService {

    public Mono<MeetDto> selectMeetSettle(final String idMeet, final ISettlePersist userPesist) {
        return userPesist.selectMeetSettle(idMeet);
    }

    public Mono<MeetDto> addMeetSettle(final String email, final MeetDto meet, final ISettlePersist userPesist) {
        return userPesist.addMeetSettle(email, meet);
    }

    public Mono<MeetDto> updateMeetSettle(final String email, final MeetDto meet, final ISettlePersist userPesist) {
        return userPesist.updateMeetSettle(email, meet);
    }

    public Mono<MeetDto> closeMeetSettle(final String email, final String idMeet, final ISettlePersist userPesist) {
        return userPesist.closeMeetSettle(email, idMeet);
    }

    public Mono<MeetDto> removeMeetSettle(final String email, final String idMeet, final ISettlePersist userPesist) {
        return userPesist.removeMeetSettle(email, idMeet);
    }

    public Mono<BillDto> addBillListMeetSettle(final String email, final String idMeet, final BillDto bill, final ISettlePersist userPesist) {
        return userPesist.addBillListMeetSettle(email, idMeet, bill);
    }

    public Mono<BillDto> updateBillSettle(final String email, final String idMeet, final BillDto bill, final ISettlePersist userPesist) {
        return userPesist.updateBillListMeetSettle(email, idMeet, bill);
    }

    public Mono<BillDto> removeBillSettle(final String email, final String idMeet, final String idBill, final ISettlePersist userPesist) {
        return userPesist.removeBillListMeetSettle(email, idMeet, idBill);
    }

}
