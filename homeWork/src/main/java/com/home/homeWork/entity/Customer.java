package com.home.homeWork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Size(max = 20,min = 4)
    private String password;

    @Column(unique = true)
    private String phone;

    private String image;


    private String address;
    private String gender;
    private Date dob;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
