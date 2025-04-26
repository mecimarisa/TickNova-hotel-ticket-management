package org.mmeci.service;

import org.mmeci.entity.Hotel;
import org.mmeci.repository.HotelRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HotelService {

    private final HotelRepository hotelRepository;

    // Konstruktori për inicializimin e HotelRepository
    public HotelService(EntityManager entityManager) {
        this.hotelRepository = new HotelRepository(entityManager);
    }

    // Get All Hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.getAllHotels(); // Kthejmë të gjithë hotelet
    }

    // Get Hotel by Name
    public List<Hotel> getHotelByName(String name) {
        return hotelRepository.findByName(name); // Kërkojmë hotelet që përputhen me emrin
    }

    // Get Hotel by Location
    public List<Hotel> getHotelByLocation(String location) {
        return hotelRepository.findByLocation(location); // Kërkojmë hotelet për një lokacion të caktuar
    }

    // Add Hotel to Database
    public void addHotel(Hotel hotel) {
        hotelRepository.addHotel(hotel); // Thërrasim metodën e addHotel në HotelRepository për ta shtuar hotelin në databazë
    }
}
