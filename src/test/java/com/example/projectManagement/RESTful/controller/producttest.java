package com.example.projectManagement.RESTful.controller;


import com.example.projectManagement.RESTful.entity.Product;
import com.example.projectManagement.RESTful.entity.ProductCategory;
import com.example.projectManagement.RESTful.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class producttest {
    @Autowired
    MockMvc mock;

    @MockBean
    ProductService ser;

    ObjectMapper Obj = new ObjectMapper();

    @Test
    public void CreateProductTest() throws Exception
    {
        Product pro=new Product();
        pro.setP_id(1000);
        pro.setCategory(ProductCategory.valueOf("Books"));
        pro.setDescription("Comedy Book");
        pro.setPrice(100);
        pro.getProductReviewList();
        when(ser.create(pro));
        mock.perform(MockMvcRequestBuilders.post("/create").contentType(MediaType.APPLICATION_JSON).content(Obj.writeValueAsString(pro))).andExpect(status().isCreated());
    }
}
