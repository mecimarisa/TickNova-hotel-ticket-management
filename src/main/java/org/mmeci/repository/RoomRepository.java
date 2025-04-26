package org.mmeci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.mmeci.entity.Room;
import java.util.List;

public class RoomRepository {
    private final EntityManager entityManager;


    public RoomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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


    public List<Room> findAvailableRooms(boolean available) {
        return entityManager.createQuery("SELECT r FROM Room r WHERE r.available = :available", Room.class)
                .setParameter("available", available)
                .getResultList();
    }


    public List<Room> findRoomsByType(String type) {
        return entityManager.createQuery("SELECT r FROM Room r WHERE r.typeOfRoom = :type", Room.class)
                .setParameter("type", type)
                .getResultList();
    }
}
