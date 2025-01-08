package com.shcedule.outsourcing.common;

import jakarta.persistence.*;
import lombok.Getter;

import javax.management.relation.Role;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    public enum Role {user, owner}

    private Role role;

    private boolean status;

    public User(String name, String email, String password, Role role, boolean status){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User(){

    }

}
