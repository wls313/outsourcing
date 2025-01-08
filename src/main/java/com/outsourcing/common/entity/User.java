package com.outsourcing.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String userId;

    private String password;

    private String role;

    private boolean status;



    public User(String name, String userId, String password, String role){
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.status = true;
    }

    public User(){

    }

    public void updateUser(String name, String email){
        this.name = name;
        this.userId = email;
    }

    public void updatePassword(String password){
        this.password = password;
    }
}