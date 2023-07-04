package com.example.projectManagement.RESTful.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_review")
public class ProductReview {
    @Id
//    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "p_id")
    Product product;

    @Column(name = "review")
    private String review;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private ProductRating rating;

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", product=" + product +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
