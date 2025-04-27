package org.mmeci.service;

import org.mmeci.entity.Hotel;
import org.mmeci.repository.HotelRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HotelService {

    private final HotelRepository hotelRepository;


    public HotelService(EntityManager entityManager) {
        this.hotelRepository = new HotelRepository(entityManager);
    }


    public List<Hotel> getAllHotels() {
        return hotelRepository.getAllHotels();
    }


    public List<Hotel> getHotelByName(String name) {
        return hotelRepository.findByName(name);
    }


    public List<Hotel> getHotelByLocation(String location) {
        return hotelRepository.findByLocation(location);
    }


    public void addHotel(Hotel hotel) {
        hotelRepository.addHotel(hotel);
    }
}