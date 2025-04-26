package org.mmeci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import org.mmeci.entity.Booking;

import java.util.List;

@AllArgsConstructor
public class BookingRepository {

    private final EntityManager entityManager;

    public void saveBooking(Booking booking) {
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(booking);
            entityTransaction.commit();

        }catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

    public Booking findById(long id) {
        return entityManager.find(Booking.class, id);
    }

    public List<Booking> getAllBookings() {
        return entityManager.createQuery("SELECT b FROM bookings b", Booking.class)
                .getResultList();

    }

    public void deleteBooking(int bookingid) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.remove(bookingid);
            entityTransaction.commit();
        }catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

}