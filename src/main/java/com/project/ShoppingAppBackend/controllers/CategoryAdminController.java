package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.models.ProductCategory;
import com.project.ShoppingAppBackend.models.ProductSubCategory;
import com.project.ShoppingAppBackend.payload.request.ProductCategoryRequest;
import com.project.ShoppingAppBackend.payload.request.ProductSubCategoryRequest;
import com.project.ShoppingAppBackend.payload.response.ProductCategoryResponse;
import com.project.ShoppingAppBackend.payload.response.ProductCategoryWithSubCategoryResponse;
import com.project.ShoppingAppBackend.payload.response.ProductSubCategoryResponse;
import com.project.ShoppingAppBackend.payload.response.SimpleCategoryResponse;
import com.project.ShoppingAppBackend.repositories.ProductCategoryRepository;
import com.project.ShoppingAppBackend.repositories.ProductSubCategoryRepository;
import com.project.ShoppingAppBackend.service.CategoryService;
import com.project.ShoppingAppBackend.service.FileService;
import com.project.ShoppingAppBackend.service.ImageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/admin")
public class CategoryAdminController {
  @Autowired private ImageService imageService;
  @Autowired private FileService fileService;
  @Autowired private CategoryService categoryService;
  @Autowired private ProductCategoryRepository productCategoryRepository;
  @Autowired private ProductSubCategoryRepository productSubCategoryRepository;

  @PostMapping("/categories")
  // @CacheEvict(value = "categoriesCache", allEntries = true)
  public ResponseEntity<?> addCategory(
      @RequestParam("name") String name,
      @RequestParam("description") String description,
      @RequestParam("file") MultipartFile file) {
    try {
      byte[] resizedImage = imageService.resizeImage(file, 1080, 1080);
      String categoryImage =
          fileService.uploadCategoryImage(resizedImage, file.getOriginalFilename(), name);

      ProductCategoryRequest productCategoryRequest =
          new ProductCategoryRequest(name, description, categoryImage);
      ProductCategory productCategory = categoryService.addCategory(productCategoryRequest);
      ProductCategoryResponse productCategoryResponse =
          categoryService.convertToCategoryResponse(productCategory);

      return new ResponseEntity<>(productCategoryResponse, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al crear la categoría: " + e.getMessage());
    }
  }

  @PostMapping("/subcategories")
  public ResponseEntity<?> addSubCategory(
      @RequestParam("name") String name,
      @RequestParam("description") String description,
      @RequestParam("file") MultipartFile file,
      @RequestParam("categoryId") Long categoryId) {
    try {
      byte[] resizedImage = imageService.resizeImage(file, 1080, 1080);
      String subCategoryImage =
          fileService.uploadCategoryImage(resizedImage, file.getOriginalFilename(), name);

      ProductSubCategoryRequest subCategoryRequest =
          new ProductSubCategoryRequest(name, description, subCategoryImage, categoryId);
      ProductSubCategory subCategory = categoryService.addSubCategory(subCategoryRequest);

      ProductSubCategoryResponse subCategoryResponse =
          categoryService.convertToSubCategoryResponse(subCategory);

      return new ResponseEntity<>(subCategoryResponse, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al crear la subcategoría: " + e.getMessage());
    }
  }

  @GetMapping("/categories")
  public ResponseEntity<List<ProductCategoryWithSubCategoryResponse>> getAllCategories() {
    List<ProductCategory> categories = productCategoryRepository.findAllWithSubCategories();
    List<ProductCategoryWithSubCategoryResponse> categoryResponses =
        categories.stream()
            .map(categoryService::convertToCategoryWithSubCategoryResponse)
            .collect(Collectors.toList());

    return ResponseEntity.ok(categoryResponses);
  }

  @GetMapping("/simple-categories")
  public ResponseEntity<List<ProductCategoryResponse>> getSimpleCategories() {
    List<ProductCategory> categories = productCategoryRepository.findAll();
    List<ProductCategoryResponse> categoryResponses =
        categories.stream()
            .map(categoryService::convertToCategoryResponse)
            .collect(Collectors.toList());
    return ResponseEntity.ok(categoryResponses);
  }

  @GetMapping("/subcategories")
  public ResponseEntity<List<ProductSubCategoryResponse>> getAllSubCategories() {
    List<ProductSubCategory> subCategories = productSubCategoryRepository.findAllWithCategoryName();
    List<ProductSubCategoryResponse> subCategoryResponses =
        subCategories.stream()
            .map(categoryService::convertToSubCategoryResponse)
            .collect(Collectors.toList());

    return ResponseEntity.ok(subCategoryResponses);
  }

  @DeleteMapping("/categories/{id}")
  // @CacheEvict(value = "categoriesCache", allEntries = true)
  // @Transactional
  public void deleteCategory(@PathVariable Long id) {
    ProductCategory productCategory = productCategoryRepository.findById(id).orElse(null);
    if (productCategory == null) {
      throw new EntityNotFoundException("Category not found");
    }
    productCategoryRepository.delete(productCategory);
  }

  @DeleteMapping("/subcategories/{id}")
  // @CacheEvict(value = "categoriesCache", allEntries = true)
  // @Transactional
  public void deleteSubCategory(@PathVariable Long id) {
    ProductSubCategory productSubCategory = productSubCategoryRepository.findById(id).orElse(null);
    if (productSubCategory == null) {
      throw new EntityNotFoundException("Category not found");
    }
    productSubCategoryRepository.delete(productSubCategory);
  }

  @GetMapping("/name-categories")
  public List<SimpleCategoryResponse> getAllCategoriesWithSubcategoriesNames() {
    List<ProductCategory> categories = productCategoryRepository.findAllWithSubCategories();
    return categories.stream()
        .map(categoryService::convertToCategoryWithSubCategoryNameResponse)
        .collect(Collectors.toList());
  }
}
