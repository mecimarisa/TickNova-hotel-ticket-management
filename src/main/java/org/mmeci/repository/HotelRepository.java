package org.mmeci.repository;

import org.mmeci.entity.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;

public class HotelRepository {

    private final EntityManager entityManager;

    public HotelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Hotel findByName(String name) {
        try {
            return entityManager.createQuery("SELECT h FROM Hotel h WHERE h.name = :name", Hotel.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Hotel me emrin " + name + " nuk u gjet!");
        }
        return null;
    }


    public List<Hotel> findByLocation(String location) {
        try {
            return entityManager.createQuery("SELECT h FROM Hotel h WHERE h.location = :location", Hotel.class)
                    .setParameter("location", location)
                    .getResultList();
        } catch (NoResultException e) {
            System.out.println("Nuk ka hotele në vendndodhjen: " + location);
            return List.of();
        }
    }
}

