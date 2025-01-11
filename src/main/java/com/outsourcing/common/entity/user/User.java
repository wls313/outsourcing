package com.outsourcing.common.entity.user;

import com.outsourcing.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String role;

    private boolean status;



    public User(String name, String email, String password, String role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = true;
    }

    public User(){

    }

    public void updateUser(String name){
        this.name = name;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateUserStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
