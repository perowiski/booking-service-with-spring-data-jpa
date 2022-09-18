package com.example.springdatajpa.mapper;

import com.example.springdatajpa.dto.BookingDto;
import com.example.springdatajpa.repository.BookingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto toDto(BookingEntity bookingEntity);
}
