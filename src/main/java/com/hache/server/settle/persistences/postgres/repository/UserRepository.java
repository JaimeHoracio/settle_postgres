package com.hache.server.settle.persistences.postgres.repository;

import com.hache.server.settle.persistences.postgres.entity.UserEntity;
import com.hache.server.settle.persistences.postgres.pojo.UserBasicPojo;
import com.hache.server.settle.persistences.postgres.record.UserBasicRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    // NOTA: Para realizar la proyeccion debemos escribir la ruta absoluta del POJO o Record.
    // Proyeccion DTO de user usando un POJO.
    @Query("""
             SELECT new com.hache.server.settle.persistences.postgres.pojo.UserBasicPojo(u.email as email, u.name as name, u.password as password)
             FROM UserEntity u
             WHERE u.email = :email
            """)
    Optional<UserBasicPojo> findBasicUser(final String email);

    // Proyeccion DTO de user usando un record.
    @Query("""
             SELECT new com.hache.server.settle.persistences.postgres.record.UserBasicRecord(u.email as email, u.name as name, u.password as password)
             FROM UserEntity u
             WHERE u.email = :email
            """)
    Optional<UserBasicRecord> findBasicUserRecord(final String email);

    @Query("""
            SELECT u FROM UserEntity u
            LEFT JOIN FETCH u.listMeet meet
            WHERE u.email = :email
            """)
    Optional<UserEntity> findMeetsUser(final String email);

    @Query("""
            SELECT u
            FROM UserEntity u
            LEFT JOIN FETCH u.listMeet meet
            LEFT JOIN FETCH meet.listBill listBill
            LEFT JOIN FETCH listBill.receipt receipt
            LEFT JOIN FETCH listBill.listUsersPaid listUsersPaid
            LEFT JOIN FETCH listUsersPaid.userPaid userPaid
            LEFT JOIN FETCH listUsersPaid.listUsersDebt listUsersDebt
            LEFT JOIN FETCH listUsersDebt.user userSimpleDebt
            LEFT JOIN FETCH receipt.currency currency
            LEFT JOIN FETCH userPaid.user userSimplePaid
            WHERE u.email = :email
            """)
    Optional<UserEntity> findFullUser(final String email);
}
