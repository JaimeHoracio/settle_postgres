package com.hache.server.settle.persistences.postgres.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "currenciesISO")
@Entity
@Data
@NoArgsConstructor
public class CurrencyISOEntity {

    @Id
    private String code;
    private String name;
    private Integer num;
    private String country;
}
