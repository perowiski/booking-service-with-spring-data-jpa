package com.example.springdatajpa.service;

import com.example.springdatajpa.dto.BookingDto;
import com.example.springdatajpa.dto.RequestBookingDto;
import com.example.springdatajpa.mapper.BookingMapper;
import com.example.springdatajpa.repository.BookingEntity;
import com.example.springdatajpa.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void createBookingSucceeds() {
        // given a valid RequestBookingDto
        RequestBookingDto requestBookingDto = RequestBookingDto.builder()
                .name("name")
                .row("1")
                .column("2")
                .build();

        // and expected entity saved
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(bookingService.requestBookingDtoToEntity(requestBookingDto));

        // and map saved entity to dto
        when(bookingMapper.toDto(any(BookingEntity.class))).thenReturn(
                BookingDto.builder()
                        .name(requestBookingDto.getName())
                        .row(requestBookingDto.getRow())
                        .column(requestBookingDto.getColumn())
                        .build()
        );


        // when creating a booking
        BookingDto createdBooking = bookingService.createBooking(requestBookingDto);

        // then expect the booking saved correctly
        ArgumentCaptor<BookingEntity> bookingEntityArgumentCaptor = ArgumentCaptor.forClass(BookingEntity.class);
        verify(bookingRepository).save(bookingEntityArgumentCaptor.capture());

        BookingEntity savedBooking = bookingEntityArgumentCaptor.getValue();
        assertEquals(requestBookingDto.getColumn(), savedBooking.getColumn().toString());
        assertEquals(requestBookingDto.getRow(), savedBooking.getRow().toString());
        assertEquals(requestBookingDto.getName(), savedBooking.getName());
        assertNotNull(savedBooking.getReference());

        // and correct mapping between entity and dto
        assertEquals(savedBooking.getName(), createdBooking.getName());
        assertEquals(savedBooking.getRow().toString(), createdBooking.getRow());
        assertEquals(savedBooking.getColumn().toString(), createdBooking.getColumn());
        assertNotNull(savedBooking.getReference());
    }
}
