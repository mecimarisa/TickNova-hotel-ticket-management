package org.mmeci.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "rooms")

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private TypeOfRoom typeOfRoom;

    private double price;
    private boolean available;

    @ManyToOne
    private Hotel hotel;

}
