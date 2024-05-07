package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.models.Product;
import com.project.ShoppingAppBackend.payload.response.ProductResponse;
import com.project.ShoppingAppBackend.payload.response.ProductStoreResponse;
import com.project.ShoppingAppBackend.repositories.ProductImageRepository;
import com.project.ShoppingAppBackend.repositories.ProductRepository;
import com.project.ShoppingAppBackend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductStoreController {
  @Autowired ProductService productService;
  @Autowired ProductRepository productRepository;
  @Autowired ProductImageRepository productImageRepository;

  @Transactional
  @GetMapping("/products/{id}")
  public ResponseEntity<?> getProduct(@PathVariable Long id) {
    try {
      Product product =
          productRepository
              .findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Product not found"));

      ProductResponse productResponse = productService.convertProductResponse(product);
      return ResponseEntity.ok(productResponse);
    } catch (EntityNotFoundException e) {
      System.out.println("Product not found: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "Product not found: " + e.getMessage()));
    } catch (Exception e) {
      System.out.println("Error retrieving product: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Map.of("error", "Error retrieving product: " + e.getMessage()));
    }
  }

  @Transactional
  @GetMapping("/subcategories/{subCategoryId}/products")
  public ResponseEntity<?> getProductsBySubCategoryId(@PathVariable Long subCategoryId) {
    try {
      List<Product> products = productRepository.findAllBySubCategoryId(subCategoryId);
      List<ProductStoreResponse> productResponses =
          products.stream()
              .map(productService::convertProductStoreResponse)
              .collect(Collectors.toList());
      return ResponseEntity.ok(productResponses);
    } catch (Exception e) {
      System.out.println("Error retrieving products: " + e.getMessage());
      // Proper error response with HTTP status code
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Map.of("error", "Error retrieving products: " + e.getMessage()));
    }
  }

  @Transactional
  @GetMapping("/products")
  public ResponseEntity<List<ProductStoreResponse>> getAllProducts() {
    try {
      List<Product> products = productRepository.findAll();
      List<ProductStoreResponse> productResponses =
          products.stream()
              .map(productService::convertProductStoreResponse)
              .collect(Collectors.toList());
      return ResponseEntity.ok(productResponses);
    } catch (Exception e) {
      System.out.println("Error retrieving products: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @Transactional
  @GetMapping("/categories/{categoryId}/products")
  public ResponseEntity<List<ProductStoreResponse>> getProductsByCategoryId(
      @PathVariable Long categoryId) {
    List<Product> products = productRepository.findAllByCategoryId(categoryId);
    List<ProductStoreResponse> productResponses =
        products.stream()
            .map(productService::convertProductStoreResponse)
            .collect(Collectors.toList());

    return ResponseEntity.ok(productResponses);
  }
}
