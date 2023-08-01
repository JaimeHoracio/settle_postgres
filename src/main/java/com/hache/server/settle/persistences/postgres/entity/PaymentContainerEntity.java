package com.hache.server.settle.persistences.postgres.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "paymentsContainers")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentContainerEntity {
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "payment_container_seq")
    @Basic(optional = false)
    @Column(name = "id_payment_container", unique = true, nullable = false)
    */
    @Id
    private String idPaymentContainer;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_payment")
    private PaymentEntity userPaid;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_payment_debt")
    private Set<PaymentEntity> listUsersDebt = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentContainerEntity that)) return false;
        return Objects.equals(idPaymentContainer, that.idPaymentContainer);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
