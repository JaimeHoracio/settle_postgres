package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDto implements Serializable {

    @JsonProperty("idBill")
    private String idBill;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("created")
    private Date created;
    @JsonProperty("receipt")
    private ReceiptContainerDto receipt;
    @JsonProperty("listUsersPaid")
    private Set<PaymentContainerDto> listUsersPaid;

}
