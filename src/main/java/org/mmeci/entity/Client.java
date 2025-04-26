package org.mmeci.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name = "clients")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;
    private String email;

    public Client(Long id, String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
}