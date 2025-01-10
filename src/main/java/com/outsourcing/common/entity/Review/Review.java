package com.outsourcing.common.entity.Review;

import com.outsourcing.common.entity.BaseEntity;
import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.store.Store;
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

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean modify;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name  = "store_id")
    private Store store;

    public Review(){
    }

    public static Review create(Double rating, String contents , User user, Order order ,Store store){
        Review review = new Review();

        review.rating = rating;
        review.contents = contents;
        review.user = user;
        review.order = order;
        review.store = store;

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