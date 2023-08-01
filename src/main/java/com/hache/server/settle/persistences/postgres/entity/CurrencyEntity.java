package com.hache.server.settle.persistences.postgres.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Table(name = "currencies")
@Entity
@Data
@NoArgsConstructor
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "currency_seq")
    @Basic(optional = false)
    @Column(name = "id_currency", unique = true, nullable = false)
    private Long idCurrency;

    private String name;
    private String code;
    private Integer num;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyEntity that)) return false;
        return Objects.equals(idCurrency, that.idCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurrency);
    }
}
