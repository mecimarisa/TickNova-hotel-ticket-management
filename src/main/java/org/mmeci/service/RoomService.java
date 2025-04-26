package org.mmeci.service;

import jakarta.persistence.EntityManager;
import org.mmeci.repository.RoomRepository;
import org.mmeci.entity.Room;
import java.util.List;

public class RoomService {
    private final RoomRepository roomRepository;


    public RoomService(EntityManager entityManager) {
        this.roomRepository = new RoomRepository(entityManager);
    }


    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findAvailableRooms(true);
    }

    public List<Room> getRoomsByType(String type) {
        return roomRepository.findRoomsByType(type);
    }
}
