package com.outsourcing.common.entity.Review;

import com.outsourcing.common.entity.BaseEntity;
import com.outsourcing.common.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;

    private String contents;

    private String userName;

    private String menuName;

    private String storeName;

    private Long orderId;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean modify;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review(){
    }

    public static Review create(Double rating,String contents ,String userName,String storeName, String menuName,User user,Long orderId ){
        Review review = new Review();

        review.rating = rating;
        review.contents = contents;
        review.userName = userName;
        review.menuName = menuName;
        review.user = user;
        review.orderId = orderId;
        review.storeName = storeName;

        return review;
    }

    public void updateReview(Double rating, String contents) {
        if(rating != null){
            this.rating = rating;
        }
        if(contents != null && !contents.isEmpty()){
            this.contents = contents;
        }
    }
}