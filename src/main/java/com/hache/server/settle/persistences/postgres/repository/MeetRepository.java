package com.hache.server.settle.persistences.postgres.repository;

import com.hache.server.settle.persistences.postgres.entity.MeetEntity;
import com.hache.server.settle.persistences.postgres.pojo.MeetPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetRepository extends JpaRepository<MeetEntity, String> {


    @Query("""
             SELECT m 
             FROM MeetEntity m
             WHERE m.idMeet = ?1
             and m.user.email = ?2
            """)
    Optional<MeetEntity> findByIdMeetAndIdUser(final String idMeet, final String idUser);

    /*
    @Query("SELECT new com.hache.server.settle.persistences.postgres.pojo.MeetPojo(m.idMeet, m.active, m.name," +
            " m.created, m.updated, new com.hache.server.settle.persistences.postgres.pojo.BillPojo(b.idBill," +
            " b.reference, b.created))" +
            " FROM MeetEntity m" +
            " LEFT JOIN BillEntity b ON b.meet.idMeet = m.idMeet" +
            " WHERE m.idMeet = ?1" +
            " and m.user.email = ?2")
    Optional<MeetPojo> findBillsByIdMeetAndIdUser(final String idMeet, final String idUser);
    */

    @Query("""
            SELECT DISTINCT m
            FROM MeetEntity m
            LEFT JOIN FETCH m.listBill b
            LEFT JOIN FETCH m.user u
            WHERE m.idMeet = ?1 and m.user.email = ?2
            """)
    Optional<MeetEntity> findBillsByIdMeetAndIdUser(final String idMeet, final String idUser);

}
