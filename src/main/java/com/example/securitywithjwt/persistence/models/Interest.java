package com.example.securitywithjwt.persistence.models;

import com.example.securitywithjwt.mapper.ListToStringConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "interests")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private String description;

    @Convert(converter = ListToStringConverter.class)
    private List<String> activities;

    @ManyToOne
    private User user;

}
