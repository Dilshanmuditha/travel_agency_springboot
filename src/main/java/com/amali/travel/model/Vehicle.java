package com.amali.travel.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
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
    private String vehicle_color;
    private String vehicle_type;
    private String vehicle_number;
    private String vehicle_image;
}
