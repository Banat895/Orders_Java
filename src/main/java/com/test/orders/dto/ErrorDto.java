package com.test.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {
    private String message;
    private String errorCode;
    private String fieldName;

}
