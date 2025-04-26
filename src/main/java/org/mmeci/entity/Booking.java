package org.mmeci.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "bookings")
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Room room;

    private int numberOfPersons;
    private Date chekInDate;
    private Date checkOutDate;
    @Enumerated(EnumType.STRING)
    private Payment payment;

    public Booking(Date chekInDate, Date checkOutDate,int numberOfPersons) {
        this.chekInDate = chekInDate;
        this.checkOutDate = checkOutDate;
        this.payment = payment;
    }

}
