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

@Table(name = "meets")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MeetEntity {

    @Id
    private String idMeet;

    private Boolean active;
    private String name;
    private Date created;
    private Date updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "meet", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private Set<BillEntity> listBill = new HashSet<>();

    public void addBill(final BillEntity bill) {
        listBill.add(bill);
        bill.setMeet(this);
    }

    public void removeBill(final BillEntity bill) {
        listBill.remove(bill);
        bill.setMeet(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetEntity that)) return false;
        return Objects.equals(idMeet, that.idMeet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMeet);
    }
}
