package com.example.springdatajpa.controller;

import com.example.springdatajpa.dto.BookingDto;
import com.example.springdatajpa.dto.RequestBookingDto;
import com.example.springdatajpa.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/api")
@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/v1/booking")
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody RequestBookingDto requestBooking){
        BookingDto bookingDto = bookingService.createBooking(requestBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }
}
