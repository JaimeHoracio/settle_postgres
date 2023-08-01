package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettleDto implements Serializable {

    @JsonProperty("listMeet")
    private List<MeetDto> listMeet;
}
