package org.mmeci.repository;

import org.mmeci.entity.Hotel;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HotelRepository {

    private final EntityManager entityManager;

    public HotelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Kthe hotelet nga databaza
    public List<Hotel> getAllHotels() {
        return entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
    }

    // Kërko hotelet me emër
    public List<Hotel> findByName(String name) {
        return entityManager.createQuery("SELECT h FROM Hotel h WHERE h.name = :name", Hotel.class)
                .setParameter("name", name)
                .getResultList();
    }

    // Kërko hotelet me lokacion
    public List<Hotel> findByLocation(String location) {
        return entityManager.createQuery("SELECT h FROM Hotel h WHERE h.location = :location", Hotel.class)
                .setParameter("location", location)
                .getResultList();
    }

    // Shto një hotel në databazë
    public void addHotel(Hotel hotel) {
        entityManager.getTransaction().begin(); // Fillim i transaksionit
        entityManager.persist(hotel); // Ruaj hotelin në databazë
        entityManager.getTransaction().commit(); // Mbyll transaksionin
    }
}
