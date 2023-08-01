package com.hache.server.settle.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("guest")
    private Boolean guest;

    @JsonProperty("token")
    private String token;

    @JsonProperty("roles")
    private List<String> roles;

    @JsonProperty("settle")
    private SettleDto settle;

}