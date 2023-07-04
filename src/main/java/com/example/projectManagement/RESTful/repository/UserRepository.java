package com.example.projectManagement.RESTful.repository;

import com.example.projectManagement.RESTful.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE user_details SET name = ?1, email = ?2, phone = ?3 WHERE u_id = ?4", nativeQuery = true)
    void updateById(String name, String email, String phone, long u_id);

}
