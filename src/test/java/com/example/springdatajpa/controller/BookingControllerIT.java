package com.example.springdatajpa.controller;

import com.example.springdatajpa.dto.BookingDto;
import com.example.springdatajpa.dto.RequestBookingDto;
import com.example.springdatajpa.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(BookingController.class)
public class BookingControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookingService bookingService;

    @Test
    public void createBookingSucceeds() throws Exception {
        // given a valid booking request for an unconfirmed column and row
        RequestBookingDto requestBookingDto = RequestBookingDto.builder()
                .column("1")
                .row("2")
                .name("name")
                .build();

        // and the booking will be confirmed
        BookingDto confirmedBooking = BookingDto.builder()
                .reference(UUID.randomUUID().toString())
                .column(requestBookingDto.getColumn())
                .name(requestBookingDto.getName())
                .row(requestBookingDto.getRow())
                .build();
        when(bookingService.createBooking(requestBookingDto))
                .thenReturn(confirmedBooking);

        // when requesting a booking
        MvcResult result = mockMvc.perform(post("/api/v1/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBookingDto)))
                        .andExpect(status().isCreated())
                        .andReturn();

        // then expect booking confirmed
        BookingDto bookingResponse = objectMapper.readValue(result.getResponse().getContentAsString(), BookingDto.class);
        assertEquals(confirmedBooking, bookingResponse);
    }
}
