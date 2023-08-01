package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentContainerDto implements Serializable {

    @JsonProperty("idPaymentContainer")
    private String idPaymentContainer;
    @JsonProperty("userPaid")
    private PaymentDto userPaid;
    @JsonProperty("listUsersDebt")
    private Set<PaymentDto> listUsersDebt;
}
