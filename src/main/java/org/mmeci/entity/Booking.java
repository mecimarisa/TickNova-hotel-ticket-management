package org.mmeci.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Payment payment;

    public Booking(Long id, Client client, Room room, Date chekInDate, Date checkInDate, Payment payment) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.chekInDate = chekInDate;
        this.checkInDate = checkInDate;
        this.payment = payment;
    }

}
