package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;

import javax.persistence.*;

@Entity
@Table(name = "wasdapp_entry")
public class WasdappEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 2048)
    private String description;
    @Column(length = 96)
    private String street;
    @Column(length = 10)
    private String number;
    @Column(length=86)
    private String city;
    @Column
    private Double lat;
    @Column
    private Double lon;

}
