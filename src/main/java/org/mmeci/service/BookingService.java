package org.mmeci.service;

import jakarta.persistence.EntityManager;
import org.mmeci.entity.Booking;
import org.mmeci.repository.BookingRepository;

import java.util.Date;
import java.util.List;

public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(EntityManager entityManager) {
        this.bookingRepository = new BookingRepository(entityManager);
    }


    public void saveBooking(Long clientId, Long roomId, String roomType, Long hotelId, int numberOfPersons,
                            int numberOfRooms, int checkIn, int checkOut, String paymentMethod) {

        Booking booking = new Booking(clientId,roomId,roomType,hotelId,numberOfPersons,numberOfRooms,checkIn
                ,checkOut,paymentMethod);
        bookingRepository.saveBooking(booking);

    }


    public Booking findBookingById(long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    public void deleteBooking(int bookingId) {
        bookingRepository.deleteBooking(bookingId);
    }

}