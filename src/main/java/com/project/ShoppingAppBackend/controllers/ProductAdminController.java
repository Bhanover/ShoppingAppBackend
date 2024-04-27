package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.models.Product;
import com.project.ShoppingAppBackend.models.ProductImage;
import com.project.ShoppingAppBackend.payload.request.ProductRequest;
import com.project.ShoppingAppBackend.payload.response.ProductResponse;
import com.project.ShoppingAppBackend.payload.response.ProductSubCategoryResponse;
import com.project.ShoppingAppBackend.repositories.ProductImageRepository;
import com.project.ShoppingAppBackend.repositories.ProductRepository;
import com.project.ShoppingAppBackend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductAdminController {
  @Autowired ProductService productService;
  @Autowired ProductRepository productRepository;
  @Autowired ProductImageRepository productImageRepository;

  // @CacheEvict(value = "clothingCache", allEntries = true)
  @Transactional
  @PostMapping("/product")
  public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
    try {
      Long productId = productService.createProductWithImages(productRequest);
      Product product =
          productRepository
              .findById(productId)
              .orElseThrow(() -> new EntityNotFoundException("Product not found"));

      ProductResponse productResponse = productService.convertProductResponse(product);
      return ResponseEntity.ok(productResponse);
    } catch (ConstraintViolationException e) {
      System.out.println("Validation error: " + e.getMessage());
      return new ResponseEntity<>(
          Map.of("error", "Validation failed: " + e.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      System.out.println("Error during product creation: " + e.getMessage());
      return new ResponseEntity<>(
          Map.of("error", "Error creating product: " + e.getMessage()),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional
  @GetMapping("/product/{id}")
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
}
