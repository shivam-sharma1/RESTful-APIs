package com.example.projectManagement.RESTful.service;

import com.example.projectManagement.RESTful.entity.Product;
import com.example.projectManagement.RESTful.entity.ProductReview;
import com.example.projectManagement.RESTful.repository.ProductRepository;
import com.example.projectManagement.RESTful.repository.ProductReviewRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReviewRepository productReviewRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product addReview(Product product){
        Product productToSave = productRepository.findById(product.getP_id()).orElseThrow(EntityNotFoundException::new);
//        productToSave.getProductReviewList().add(product.getProductReviewList());
        System.out.println(productToSave.getP_id());

        System.out.println("-----------");

        List<ProductReview> tempList = product.getProductReviewList();
        tempList.forEach(System.out::println);

        productToSave.getProductReviewList().add(tempList.get(0));
        System.out.println(productToSave);
       return productRepository.save(productToSave);
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Product> findAll() {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getP_id)).toList();
    }

    public ResponseEntity<Product> updateOne(long id, Product product) {
       if (productRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
//        LocalDateTime currTime = LocalDateTime.from(LocalTime.now());
        LocalDateTime currTime = LocalDateTime.now();
        productRepository.updateById(product.getDescription(), product.getCategory().toString(), product.getPrice(),currTime, id);
        return ResponseEntity.ok(product);

    }

    public Product patchOne(long id, JsonPatch patch) {
        var product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        var productPatched = applyPatchToProduct(patch, product);
        return productRepository.save(productPatched);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    private Product applyPatchToProduct(JsonPatch patch, Product product) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(product, JsonNode.class));
            return objectMapper.treeToValue(patched, Product.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
