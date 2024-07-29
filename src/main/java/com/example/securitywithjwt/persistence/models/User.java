package com.example.securitywithjwt.persistence.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    private Long phone;

    @Min(0)
    @Max(120)
    private int age;

    @NotBlank
    private String gender;

    private String imageUrl;

    private String frontPageUrl;

    private String description;

    @NotBlank
    @Size(min = 2, max = 100)
    private String city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Interest> interests;

}
