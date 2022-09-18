package com.example.springdatajpa.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class RequestBookingDto {

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "row can not be empty")
    private String row;

    @NotEmpty(message = "column can not be empty")
    private String column;
}
