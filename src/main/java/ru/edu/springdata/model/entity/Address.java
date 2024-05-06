package ru.edu.springdata.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;

    @OneToOne(mappedBy = "address")
    private Author author;
}