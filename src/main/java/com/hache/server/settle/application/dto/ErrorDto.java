package com.hache.server.settle.application.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable {

    private String message;
    private Integer codeError;
}
