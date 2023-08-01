package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptContainerDto implements Serializable {
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("discount")
    private Double discount;
    @JsonProperty("currency")
    private CurrencyDto currency;
}
