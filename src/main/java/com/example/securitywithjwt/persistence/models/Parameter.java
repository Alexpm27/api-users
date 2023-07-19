package com.example.securitywithjwt.persistence.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "parameters")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private Float humidityAbove;

    private Float humidityBelow;

    private Float temperature;

    private Float lux;

    private String date;

    private String status;

    @OneToOne
    private Statistic statistic;

    @ManyToOne
    private User user;
}
