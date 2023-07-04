package com.example.projectManagement.RESTful.service;

import com.example.projectManagement.RESTful.entity.Product;
import com.example.projectManagement.RESTful.entity.User;
import com.example.projectManagement.RESTful.repository.ProductRepository;
import com.example.projectManagement.RESTful.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll().stream().sorted(Comparator.comparing(User::getU_id)).toList();
    }

    public ResponseEntity<User> editUserDetails(long u_id, User user) {
        if (userRepository.findById(u_id).isEmpty()) throw new EntityNotFoundException();
        userRepository.updateById(user.getName(), user.getEmail().toString(), user.getPhone(), u_id);
        return ResponseEntity.ok(user);
    }

    public User getUserById(long u_id) {
        return userRepository.findById(u_id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
