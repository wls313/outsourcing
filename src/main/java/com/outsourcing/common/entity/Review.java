package com.outsourcing.common.entity;

import jakarta.persistence.*;

public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private boolean modify = false;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;


    public Review(){
    }

}
