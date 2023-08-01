package com.hache.server.settle.persistences.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "bills")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {

    @Id
    private String idBill;
    private String reference;
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_meet")
    private MeetEntity meet = new MeetEntity();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "id_receipt")
    private ReceiptContainerEntity receipt;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "id_payment_user_paid")
    private Set<PaymentContainerEntity> listUsersPaid = new HashSet<>();

    /*
    public void addUserPaid(final PaymentContainerEntity paymentContainer) {
        if(listUsersPaid.isEmpty()){
            listUsersPaid = new HashSet<>();
        }
        listUsersPaid.add(paymentContainer);
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillEntity that)) return false;
        return Objects.equals(idBill, that.idBill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBill);
    }
}
