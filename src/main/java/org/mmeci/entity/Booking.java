package org.mmeci.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "bookings")
@NoArgsConstructor
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;
    private Long clientId;

    @ManyToOne
    private Room room;
    private Long roomId;
    private String roomType;

    @ManyToOne
    private Hotel hotel;
    private Long hotelId;

    private int numberOfPersons;
    private int numberOfRooms;
    private int chekInDate;
    private int checkOutDate;


    private String paymentMethod;

    public Booking(Long clientId, Long roomId
            , String roomType, Long hotelId, int numberOfPersons
            , int numberOfRooms, int chekInDate
            , int checkOutDate, String paymentMethod) {

        this.clientId = clientId;
        this.roomId = roomId;
        this.roomType = roomType;
        this.hotelId = hotelId;
        this.numberOfPersons = numberOfPersons;
        this.numberOfRooms = numberOfRooms;
        this.chekInDate = chekInDate;
        this.checkOutDate = checkOutDate;
        this.paymentMethod = paymentMethod;
    }
}