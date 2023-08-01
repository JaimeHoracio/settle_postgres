package com.hache.server.settle.persistences.postgres.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "payments")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaymentEntity {

    @Id
    private String idPayment;

    private Double amount;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "id_user_simple")
    private UserSimpleEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentEntity that)) return false;
        return Objects.equals(idPayment, that.idPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPayment);
    }
}
