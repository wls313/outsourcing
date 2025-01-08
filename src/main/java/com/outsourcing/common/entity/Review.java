package com.outsourcing.common.entity;

import com.outsourcing.dto.review.ReviewDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name = "review")
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;

    private String contents;

    private String memberName;

    private String menuName;

    private Long orderId;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean modify;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review(){
    }

    public static Review create(Double rating,String contents ,String memberName, String menuName,User user,Long orderId ){
        Review review = new Review();

        review.rating = rating;
        review.contents = contents;
        review.memberName = memberName;
        review.menuName = menuName;
        review.user = user;
        review.orderId = orderId;

        return review;
    }
}