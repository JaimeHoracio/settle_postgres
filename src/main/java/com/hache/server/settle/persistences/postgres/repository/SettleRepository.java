package com.hache.server.settle.persistences.postgres.repository;


import com.hache.server.settle.application.dto.BillDto;
import com.hache.server.settle.application.dto.MeetDto;
import com.hache.server.settle.application.dto.UserDto;
import com.hache.server.settle.application.mapper.postgres.BillMapper;
import com.hache.server.settle.application.mapper.postgres.MeetMapper;
import com.hache.server.settle.application.mapper.postgres.UserMapper;
import com.hache.server.settle.persistences.ISettlePersist;
import com.hache.server.settle.persistences.postgres.entity.BillEntity;
import com.hache.server.settle.persistences.postgres.entity.EnumRol;
import com.hache.server.settle.persistences.postgres.entity.MeetEntity;
import com.hache.server.settle.persistences.postgres.entity.UserEntity;
import com.hache.server.settle.persistences.postgres.record.UserBasicRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SettleRepository implements ISettlePersist {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MeetRepository meetRepository;
    @Autowired
    private BillRepository billRepository;

    @Override
    public Mono<UserDto> findUser(String email) {
        Optional<UserBasicRecord> userPojo = userRepository.findBasicUserRecord(email);
        if (userPojo.isPresent()) {
            UserEntity user = UserEntity.builder()
                    .email(userPojo.get().email())
                    .name(userPojo.get().name())
                    .password(userPojo.get().password())
                    .build();
            return Mono.just(user).map(UserMapper.INSTANCE::convertEntityToDto);
        }
        return Mono.empty();
    }

    @Override
    public Mono<UserDto> findUserAndMeets(String email) {
        Optional<UserEntity> user = userRepository.findFullUser(email);
        if (user.isPresent()) {
            return Mono.just(user.get()).map(UserMapper.INSTANCE::convertEntityToDto);
        }
        return Mono.empty();
    }

    @Override
    public Mono<UserDto> createUser(final String email, final String name, final String password, final Boolean guest, List<String> roles) {
        List<EnumRol> rolesEnum = roles.stream().map(r -> EnumRol.valueOf(r)).collect(Collectors.toList());
        UserEntity user = UserEntity.builder().email(email).name(name).password(password).guest(guest).roles(rolesEnum).build();
        return Mono.just(userRepository.save(user)).map(UserMapper.INSTANCE::convertEntityToDto);
    }

    @Override
    public Mono<MeetDto> selectMeetSettle(String idMeet) {
        Optional<MeetEntity> meet = meetRepository.findById(idMeet);
        if (meet.isPresent()) {
            return Mono.just(meet.get()).map(MeetMapper.INSTANCE::convertEntityToDto);
        }
        return Mono.empty();
    }

    @Override
    public Mono<MeetDto> addMeetSettle(String email, MeetDto meetDto) {
        Optional<UserEntity> userOpt = userRepository.findMeetsUser(email);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();

            user.addMeet(MeetMapper.INSTANCE.convertDtoToEntity(meetDto));
            userRepository.save(user);
            return Mono.just(meetDto);
        }
        return Mono.empty();
    }

    @Override
    public Mono<MeetDto> updateMeetSettle(String email, MeetDto meetDto) {
        //Actualizo directamente el meet desde la tabla meets.
        Optional<MeetEntity> meetDB = meetRepository.findByIdMeetAndIdUser(meetDto.getIdMeet(), email);
        if (meetDB.isPresent()) {
            MeetEntity meet = meetDB.get();
            meet.setName(meetDto.getName());
            meet.setCreated(meetDto.getCreated());
            meet.setUpdated(meetDto.getUpdated());

            meetRepository.save(meet);
            return Mono.just(meetDto);
        }
        return Mono.empty();
    }

    @Override
    public Mono<MeetDto> closeMeetSettle(String email, String idMeet) {
        Optional<MeetEntity> meetDB = meetRepository.findByIdMeetAndIdUser(idMeet, email);
        if (meetDB.isPresent()) {
            MeetEntity meet = meetDB.get();
            meet.setActive(false);
            return Mono.just(meetRepository.save(meet)).map(MeetMapper.INSTANCE::convertEntityToDto);
        }
        return Mono.empty();
    }

    @Override
    public Mono<MeetDto> removeMeetSettle(String email, String idMeet) {
        Optional<UserEntity> meetDB = userRepository.findMeetsUser(email);
        if (meetDB.isPresent()) {
            UserEntity user = meetDB.get();

            MeetEntity meet = MeetEntity.builder().idMeet(idMeet).build();
            user.removeMeet(meet);
            userRepository.save(user);
            return Mono.just(MeetDto.builder().idMeet(idMeet).build());
        }
        return Mono.empty();
    }

    @Override
    public Mono<BillDto> addBillListMeetSettle(String email, String idMeet, BillDto billDto) {
        Optional<MeetEntity> meetOpt = meetRepository.findBillsByIdMeetAndIdUser(idMeet, email);
        if (meetOpt.isPresent()) {
            MeetEntity meet = meetOpt.get();
            BillEntity bill = preparedIdsNotRepeted4SameEntities(billDto);
            // Metodo que agrega bidireccional, aunque para las especificaciones no sería necesario.
            meet.addBill(bill);
            meetRepository.save(meet);
            return Mono.just(billDto);
        }
        return Mono.empty();
    }

    @Override
    public Mono<BillDto> updateBillListMeetSettle(String email, String idMeet, BillDto billDto) {
        BillEntity bill = preparedIdsNotRepeted4SameEntities(billDto);
        bill.setMeet(MeetEntity.builder().idMeet(idMeet).build());
        return Mono.just(billRepository.save(bill)).map(BillMapper.INSTANCE::convertEntityToDto);
    }

    @Override
    public Mono<BillDto> removeBillListMeetSettle(String email, String idMeet, String idBill) {
        Optional<MeetEntity> meetOpt = meetRepository.findBillsByIdMeetAndIdUser(idMeet, email);
        if (meetOpt.isPresent()) {
            MeetEntity meet = meetOpt.get();
            meet.removeBill(BillEntity.builder().idBill(idBill).build());
            meetRepository.save(meet);
            return Mono.just(new BillDto());
        }
        return Mono.empty();
    }

    private BillEntity preparedIdsNotRepeted4SameEntities(final BillDto billDto) {
        /**
         * Dado que vienen objetos (PaymentEntity y UserSimple) con identificador null y al guardarlos se generan con el mismo valor.
         * Es necesario asignarles a mano un identificador único para no tener error de PK constraint.
         */
        BillEntity bill = BillMapper.INSTANCE.convertDtoToEntity(billDto);
        bill.getListUsersPaid().forEach(userPaid -> {
            userPaid.getUserPaid().setIdPayment(UUID.randomUUID().toString());
            userPaid.getUserPaid().getUser().setId(UUID.randomUUID().toString());

            userPaid.getListUsersDebt().forEach(userDebt -> {
                userDebt.setIdPayment(UUID.randomUUID().toString());
                userDebt.getUser().setId(UUID.randomUUID().toString());
            });
        });
        return bill;
    }
}
