package com.example.projectManagement.RESTful.entity;

import jakarta.persistence.*;
import lombok.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_table")

public class Product extends BaseEntity {
    @Id
//    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long p_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    @NotNull(message = "Category must be specified.")
    private ProductCategory category;

    @Column(name = "description")
    private String description;

    @Min(0)
    @Column(name = "price", columnDefinition = "decimal (10,2)")
    private int price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "p_id")
    private List<ProductReview> productReviewList;

    public Product(int id, String description, String initialCategory, int price) {
        super();
    }

}

