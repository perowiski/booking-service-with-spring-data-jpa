package com.example.springdatajpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDto {
    private String reference;
    private String row;
    private String column;
    private String name;
}