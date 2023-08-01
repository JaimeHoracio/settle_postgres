package com.hache.server.settle.persistences.postgres.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "receipts")
@Data
@NoArgsConstructor
public class ReceiptContainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "receipt_seq")
    @Basic(optional = false)
    @Column(name = "id_receipt", unique = true, nullable = false)
    private Long idReceipt;

    private Double amount;
    private Double discount;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "id_currency")
    private CurrencyEntity currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceiptContainerEntity that)) return false;
        return Objects.equals(idReceipt, that.idReceipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceipt);
    }
}
