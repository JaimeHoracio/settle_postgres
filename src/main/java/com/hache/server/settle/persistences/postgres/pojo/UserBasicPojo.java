package com.hache.server.settle.persistences.postgres.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicPojo {

    private String email;

    private String name;

    private String password;

}
