package com.outsourcing.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name = "review")
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double rating;

    @ColumnDefault("0")
    @Column(columnDefinition = "boolean", nullable = false)
    private boolean modify;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;

    public Review(){
    }

    public Review(double rating ,boolean modify){
        this.rating = rating;
        this.modify = modify;
    }
}