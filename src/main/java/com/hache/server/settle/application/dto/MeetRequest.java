package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetRequest implements Serializable {

    @JsonProperty("email")
    private String email;
    @JsonProperty("meet")
    private MeetDto meet;

}
