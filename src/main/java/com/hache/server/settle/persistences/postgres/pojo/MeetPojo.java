package com.hache.server.settle.persistences.postgres.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetPojo {

    private String idMeet;

    private Boolean active;
    private String name;
    private Date created;
    private Date updated;

    private Set<BillPojo> listBill = new HashSet<>();
}
