package com.example.springdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DbIntegrationTest
public class BookingRepositoryIT {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void saveSucceeds() {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setColumn(1l);
        bookingEntity.setReference("reference");
        bookingEntity.setRow(2l);
        bookingEntity.setName("name");

        // save the entity the first time
        bookingEntity = bookingRepository.save(bookingEntity);
        BookingEntity retrievedBookingEntity = bookingRepository.findById(bookingEntity.getId()).get();

        // so that we can get DB generated fields
        bookingRepository.flush();

        // assert that we read in all the fields we tried to save
        assertEquals(bookingEntity.getColumn(), retrievedBookingEntity.getColumn());
        assertEquals(bookingEntity.getReference(), retrievedBookingEntity.getReference());
        assertEquals(bookingEntity.getRow(), retrievedBookingEntity.getRow());
        assertEquals(bookingEntity.getName(), retrievedBookingEntity.getName());

        // assert that DB generated fields are correct
        assertNotNull(retrievedBookingEntity.getTimeCreated());
        assertNull(retrievedBookingEntity.getTimeUpdated());

        // assert the time_updated is changed on update via DB
        bookingEntity.setName("updated");
        bookingRepository.save(bookingEntity);
        bookingRepository.flush();

        assertNotNull(bookingEntity.getTimeUpdated());
    }
}
