package org.mmeci.repository;

import org.mmeci.entity.Hotel;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HotelRepository {

    private final EntityManager entityManager;

    public HotelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Hotel> getAllHotels() {
        return entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
    }


    public List<Hotel> findByName(String name) {
        return entityManager.createQuery("SELECT h FROM Hotel h WHERE h.name = :name", Hotel.class)
                .setParameter("name", name)
                .getResultList();
    }


    public List<Hotel> findByLocation(String location) {
        return entityManager.createQuery("SELECT h FROM Hotel h WHERE h.location = :location", Hotel.class)
                .setParameter("location", location)
                .getResultList();
    }


    public void addHotel(Hotel hotel) {
        entityManager.getTransaction().begin();
        entityManager.persist(hotel);
        entityManager.getTransaction().commit();
    }
}
