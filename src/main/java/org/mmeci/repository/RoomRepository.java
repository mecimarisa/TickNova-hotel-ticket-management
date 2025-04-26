package org.mmeci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import org.mmeci.entity.Room;

import java.util.List;

@AllArgsConstructor
public class RoomRepository {

    private final EntityManager entityManager;

    public void save(Room room) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public Room findByAvailableTrue(boolean available) {
        return entityManager.createQuery("SELECT r FROM Room r WHERE r.available = :available", Room.class)
                .setParameter("available", available)
                .getSingleResult();
    }


    public Room findByType(String typeOfRoom) {
        return entityManager.createQuery("SELECT r FROM Room r WHERE r.typeOfRoom = :typeOfRoom", Room.class)
                .setParameter("typeOfRoom", typeOfRoom)
                .getSingleResult();
    }
}
