package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSimpleDto implements Serializable {

    @JsonProperty("name")
    private String name;
}
