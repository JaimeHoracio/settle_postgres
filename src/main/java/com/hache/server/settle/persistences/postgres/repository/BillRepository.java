package com.hache.server.settle.persistences.postgres.repository;

import com.hache.server.settle.persistences.postgres.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, String> {
}
