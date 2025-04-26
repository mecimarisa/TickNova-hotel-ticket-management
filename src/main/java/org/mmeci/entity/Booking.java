package org.mmeci.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Room room;

    private Date chekInDate;
    private Date checkInDate;






}
