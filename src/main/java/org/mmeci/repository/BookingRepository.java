package org.mmeci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.mmeci.config.HibernateConfiguration;
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

    public List<Booking> getAllBookings() {
        return entityManager.createQuery("SELECT b FROM bookings b", Booking.class)
                .getResultList();

    }

    public void deleteBooking(Booking booking) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.remove(booking);
            entityTransaction.commit();
        }catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }






}
