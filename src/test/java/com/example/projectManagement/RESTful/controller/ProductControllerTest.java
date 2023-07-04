package com.example.projectManagement.RESTful.controller;

import com.example.projectManagement.RESTful.entity.Product;
import com.example.projectManagement.RESTful.entity.ProductCategory;
import com.example.projectManagement.RESTful.entity.ProductRating;
import com.example.projectManagement.RESTful.entity.ProductReview;
import com.example.projectManagement.RESTful.service.ProductService;
import com.example.projectManagement.RESTful.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mvc;
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Product prod;

    @BeforeEach
    void setup(){
        ProductReview prdReview = new ProductReview();
        prdReview.setId(1);
        prdReview.setProduct(new Product());
        prdReview.setReview("This product is amazing!");
        prdReview.setRating(ProductRating.FIVE);
        List<ProductReview> productReviewList = new ArrayList<>();
        productReviewList.add(prdReview);
        prod= new Product(1, ProductCategory.BOOKS, "BOOKS", 100, productReviewList);
    }

    @Test
    void createProduct() throws Exception {
        when(productService.create(prod)).thenReturn(prod);
        ResponseEntity<Product>prodResponse=null;
        prodResponse=productController.createProduct(prod);
        Assert.notNull(prodResponse,"Okay Response");

//        mvc.perform( MockMvcRequestBuilders
//                        .post("/create")
//                        .contentType("application/json")
//                        .content(asJsonString(prod))
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void addReview() throws Exception {
        ProductReview prdReview = new ProductReview();
        prdReview.setId(1);
        prdReview.setProduct(new Product());
        prdReview.setReview("This product is not good!");
        prdReview.setRating(ProductRating.FOUR);
        prod.getProductReviewList().add(prdReview);
        when(productService.addReview(prod)).thenReturn(prod);
        ResponseEntity<Product>prodResponse=null;
        prodResponse=productController.addReview(prod);
        Assert.isTrue(prodResponse.getBody().getProductReviewList().size()==2,"True");

    }

    @Test
    void getAllProducts() throws Exception {
        //mock service.create(dto).then return()
        List<Product> productList = new ArrayList<>();
        productList.add(prod);
        when(productService.findAll()).thenReturn(productList);
        ResponseEntity<List<Product>>prodResponse=null;
        prodResponse=productController.getAllProducts();
        Assert.notNull(prodResponse,"Okay Response");

    }

    @Test
    void getById() throws Exception{
        int id = 1;
        when(productService.findById(id)).thenReturn(prod);
        ResponseEntity<Product>prodResponse=null;
        prodResponse=productController.getById(id);
        Assert.notNull(prodResponse,"Okay Response");

//        mvc.perform( MockMvcRequestBuilders
//                .get("/{id}",id)
//                .contentType("application/json")
//                .content("")
//                .accept(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateOneProduct() throws Exception {
        long id = 4;

        ProductReview prdReview = new ProductReview();
        prdReview.setId(4);
        prdReview.setProduct(new Product());
        prdReview.setReview("This product is amazing!");
        prdReview.setRating(ProductRating.FOUR);
        List<ProductReview> productReviewList = new ArrayList<>();
        productReviewList.add(prdReview);

        Product product = new Product(id, ProductCategory.BOOKS, "BOOKS", 100, productReviewList);

        // Mocking the behavior of findById
//        when(productRepository.findById(id)).thenReturn(Optional.of(product));
//
//        // Mocking the behavior of updateById
//        doNothing().when(productRepository).updateById(
//                product.getDescription(),
//                product.getCategory().toString(),
//                product.getPrice(),
//                LocalDateTime.now(),
//                id
//        );

        when(productService.updateOne(id,product)).thenReturn(product);


        ResponseEntity<Product> response = productController.updateOneProduct(id, product);

//         Verifying that findById was called once
//        verify(productRepository, times(1)).findById(id);

//         Verifying that updateById was called once
//        verify(productRepository, times(1)).updateById(
//                product.getDescription(),
//                product.getCategory().toString(),
//                product.getPrice(),
//                LocalDateTime.now(),
//                id
//        );

//         Asserting the response
        assertEquals(product, response.getBody());
        assertEquals(200, response.getStatusCodeValue());


//        int id = 4;
//        ProductReview prdReview = new ProductReview();
//        prdReview.setId(4);
//        prdReview.setProduct(new Product());
//        prdReview.setReview("This product is amazing!");
//        prdReview.setRating(ProductRating.FOUR);
//        List<ProductReview> productReviewList = new ArrayList<>();
//        productReviewList.add(prdReview);
//
//        Product updatedProduct = new Product(4, ProductCategory.BOOKS, "BOOKS", 100, productReviewList);
//        Mockito.when(productService.updateOne(id,updatedProduct)).thenReturn();
//        ResponseEntity<Product>prodResponse=null;
//        prodResponse=productController.getById(id);
//        Assert.notNull(prodResponse,"Okay Response");


        // Perform the PUT request
//        mvc.perform(MockMvcRequestBuilders
//                        .put("/update/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(updatedProduct)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.p_id").value(id)); // Assuming the response JSON has an "id" field
    }

    @Test
    void deleteOneProduct() throws Exception {
        int id = 1; // Set the desired ID for testing

        // Perform the DELETE request
        mvc.perform(MockMvcRequestBuilders
                        .delete("/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void deleteAllProducts() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/deleteAll"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}