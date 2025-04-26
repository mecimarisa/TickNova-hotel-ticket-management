package org.mmeci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoomRepository {

        private final EntityManager entityManager;

        public void save(Room room){
            EntityTransaction transaction = entityManager.getTransaction();

            try{
                transaction.begin();
                entityManager.persist(room);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                e.printStackTrace();
            }

        }

    public List<Room> findByAvailableTrue() {
        String jpql = "SELECT r FROM Room r WHERE r.available = true";
        TypedQuery<Room> query = entityManager.createQuery(jpql, Room.class);
        return query.getResultList();
    }

    public List<Room> findByType(String type) {
        String jpql = "SELECT r FROM Room r WHERE r.type = :type";
        TypedQuery<Room> query = entityManager.createQuery(jpql, Room.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

}
