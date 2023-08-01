package com.hache.server.settle.persistences.postgres.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String password;

    private Boolean guest;

    @Transient
    private String token;

    @Enumerated(EnumType.STRING)
    private List<EnumRol> roles;

    //Para @OneToMany or defecto fetch = FetchType.LAZY
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default // Lombok inicializa por defecto con una lista vacia.
    private Set<MeetEntity> listMeet = new HashSet<>();

    public void addMeet(final MeetEntity meet) {
        listMeet.add(meet);
        meet.setUser(this);
    }

    public void removeMeet(final MeetEntity meet){
        listMeet.remove(meet);
        meet.setUser(null);
    }

}
