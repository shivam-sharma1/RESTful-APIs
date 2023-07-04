package com.example.projectManagement.RESTful.controller;
import com.example.projectManagement.RESTful.entity.User;
import com.example.projectManagement.RESTful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @GetMapping("get_all_users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("getUser/{u_id}")
    public ResponseEntity<User> getUserById(@PathVariable("u_id") long u_id){
        return new ResponseEntity<User>(userService.getUserById(u_id), HttpStatus.OK);
    }

    @PutMapping("/editUserDetails/{u_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> editUserDetails(@PathVariable("u_id") long u_id, @RequestBody User user) {
        return userService.editUserDetails(u_id, user);
    }

    @DeleteMapping("deleteUser/{u_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteOneProduct(@PathVariable("u_id") int u_id) {
        userService.deleteById(u_id);
    }

}
