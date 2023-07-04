package com.example.projectManagement.RESTful.repository;

import com.example.projectManagement.RESTful.entity.Product;
import com.example.projectManagement.RESTful.entity.ProductReview;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

}
