package com.hache.server.settle.persistences.postgres.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPojo {

    private String idBill;
    private String reference;
    private Date created;
}
