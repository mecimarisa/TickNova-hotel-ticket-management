package org.mmeci.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_room")
    private TypeOfRoom typeOfRoom;

    private double price;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Room() {}


}
