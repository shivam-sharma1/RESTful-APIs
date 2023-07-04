package com.example.projectManagement.RESTful.controller;

import com.example.projectManagement.RESTful.entity.Product;
import com.example.projectManagement.RESTful.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

    @PostMapping("/add_review")
    public ResponseEntity<Product> addReview(@RequestBody Product product){
        return new ResponseEntity<>(service.addReview(product), HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateOneProduct(@PathVariable("id") long id, @RequestBody Product product) {
        return  service.updateOne(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteOneProduct(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProducts() {
        service.deleteAll();
    }

}
