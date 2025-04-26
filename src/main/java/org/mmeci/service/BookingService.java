package org.mmeci.service;

import jakarta.persistence.EntityManager;
import org.mmeci.entity.Booking;
import org.mmeci.entity.Payment;
import org.mmeci.entity.TypeOfRoom;
import org.mmeci.repository.BookingRepository;

import java.util.Date;
import java.util.List;

public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(EntityManager entityManager) {
        this.bookingRepository = new BookingRepository(entityManager);
    }

    public void saveBooking(Date checkIn, Date checkOut, int numberOfPersons) {
        Booking booking = new Booking(checkIn,checkOut,numberOfPersons);
        bookingRepository.saveBooking(booking);

    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    public void deleteBooking(Booking booking) {
        bookingRepository.deleteBooking(booking);
    }
}
