package com.example.springdatajpa.service;

import com.example.springdatajpa.dto.RequestBookingDto;
import com.example.springdatajpa.mapper.BookingMapper;
import com.example.springdatajpa.repository.BookingEntity;
import com.example.springdatajpa.repository.BookingRepository;
import org.springframework.stereotype.Service;
import com.example.springdatajpa.dto.BookingDto;

import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    private BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper){
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    public BookingDto createBooking(RequestBookingDto requestBooking) {
        BookingEntity bookingEntity = requestBookingDtoToEntity(requestBooking);
        BookingEntity savedEntity = bookingRepository.save(bookingEntity);
        BookingDto bookingDto = bookingMapper.toDto(savedEntity);
        return bookingDto;
    }

    protected BookingEntity requestBookingDtoToEntity(RequestBookingDto requestBookingDto) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setName(requestBookingDto.getName());
        bookingEntity.setRow(Long.valueOf(requestBookingDto.getRow()));
        bookingEntity.setColumn(Long.valueOf(requestBookingDto.getColumn()));
        bookingEntity.setReference(UUID.randomUUID().toString());
        return bookingEntity;
    }
}
