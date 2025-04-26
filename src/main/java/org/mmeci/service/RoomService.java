package org.mmeci.service;

import jakarta.persistence.EntityManager;
import org.mmeci.entity.Room;
import org.mmeci.repository.RoomRepository;


public class RoomService {

    private final RoomRepository roomRepository;
    public RoomService( EntityManager entityManager ) {
        this.roomRepository = new RoomRepository(entityManager);
    }
    public Room findByAvailableTrue(boolean available) {
        return roomRepository.findByAvailableTrue(available);
    }

    public Room findByType(String typeOfRoom) {
        return roomRepository.findByType(typeOfRoom);
    }
}
