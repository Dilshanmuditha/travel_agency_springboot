package com.amali.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String mobile_number;

    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String licence_number;
    private String nic_number;
}
