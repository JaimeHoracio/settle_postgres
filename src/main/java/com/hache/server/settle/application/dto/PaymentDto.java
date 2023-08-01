package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto implements Serializable {

    @JsonProperty("idPayment")
    private String idPayment;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("user")
    private UserSimpleDto user;
}
