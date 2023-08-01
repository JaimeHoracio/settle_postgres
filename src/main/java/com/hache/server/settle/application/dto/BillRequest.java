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
public class BillRequest implements Serializable {

    @JsonProperty("email")
    private String email;
    @JsonProperty("idMeet")
    private String idMeet;
    @JsonProperty("bill")
    private BillDto bill;

}
