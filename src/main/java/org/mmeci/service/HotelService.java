package org.mmeci.service;


import jakarta.persistence.EntityManager;
import org.mmeci.entity.Hotel;
import org.mmeci.repository.HotelRepository;

import java.util.List;


public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(EntityManager entityManager) {
        this.hotelRepository = new HotelRepository(entityManager);
    }

    public Hotel getHotelByName(String name) {
        return hotelRepository.findByName(name);
    }

    public List<Hotel> getHotelByLocation(String location) {
        return hotelRepository.findByLocation(location);
    }
  }
