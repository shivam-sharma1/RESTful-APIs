package com.example.projectManagement.RESTful.repository;

import com.example.projectManagement.RESTful.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE product_table SET description = ?1, category = ?2, price = ?3, updated_at = ?4 WHERE p_id = ?5", nativeQuery = true)
    void updateById(String description, String category, int price, LocalDateTime currTime, long id);

    @Query(value = "INSERT into product_review VALUES id = ?1, rating = ?2, review = ?3, p_id = ?4", nativeQuery = true)
    void addReview();
}

