package com.project.ShoppingAppBackend.service;

import com.project.ShoppingAppBackend.models.ProductCategory;
import com.project.ShoppingAppBackend.models.ProductSubCategory;
import com.project.ShoppingAppBackend.payload.request.ProductCategoryRequest;
import com.project.ShoppingAppBackend.payload.request.ProductSubCategoryRequest;
import com.project.ShoppingAppBackend.payload.response.ProductCategoryResponse;
import com.project.ShoppingAppBackend.payload.response.ProductCategoryWithSubCategoryResponse;
import com.project.ShoppingAppBackend.payload.response.ProductSubCategoryResponse;
import com.project.ShoppingAppBackend.repositories.ProductCategoryRepository;
import com.project.ShoppingAppBackend.repositories.ProductRepository;
import com.project.ShoppingAppBackend.repositories.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
  @Autowired private ProductRepository productRepository;
  @Autowired private ProductCategoryRepository productCategoryRepository;
  @Autowired private ProductSubCategoryRepository productSubCategoryRepository;

  public ProductCategory addCategory(ProductCategoryRequest productCategoryRequest) {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setName(productCategoryRequest.getName());
    productCategory.setDescription(productCategoryRequest.getDescription());
    productCategory.setCategoryImage(productCategoryRequest.getCategoryImage());
    return productCategoryRepository.save(productCategory);
  }

  public ProductCategoryResponse convertToCategoryResponse(ProductCategory productCategory) {
    return new ProductCategoryResponse(
        productCategory.getId(),
        productCategory.getName(),
        productCategory.getDescription(),
        productCategory.getCategoryImage());
  }

  public ProductSubCategoryResponse convertToSubCategoryResponse(
      ProductSubCategory productSubCategory) {
    return new ProductSubCategoryResponse(
        productSubCategory.getId(),
        productSubCategory.getName(),
        productSubCategory.getDescription(),
        productSubCategory.getSubCategoryImage(),
        productSubCategory.getCategory() != null
            ? productSubCategory.getCategory().getName()
            : "Sin categoría");
  }

  public ProductCategoryWithSubCategoryResponse convertToCategoryWithSubCategoryResponse(
      ProductCategory productCategory) {
    List<ProductSubCategoryResponse> subCategoryResponses =
        productCategory.getSubCategories().stream()
            .map(
                subCategory ->
                    new ProductSubCategoryResponse(
                        subCategory.getId(),
                        subCategory.getName(),
                        subCategory.getDescription(),
                        subCategory.getSubCategoryImage()))
            .collect(Collectors.toList());

    return new ProductCategoryWithSubCategoryResponse(
        productCategory.getId(),
        productCategory.getName(),
        productCategory.getDescription(),
        productCategory.getCategoryImage(),
        subCategoryResponses);
  }

  public ProductSubCategory addSubCategory(ProductSubCategoryRequest request) {
    ProductSubCategory subCategory = new ProductSubCategory();
    subCategory.setName(request.getName());
    subCategory.setDescription(request.getDescription());
    subCategory.setSubCategoryImage(request.getSubCategoryImage());
    ProductCategory category =
        productCategoryRepository
            .findById(request.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    subCategory.setCategory(category);
    return productSubCategoryRepository.save(subCategory);
  }

  /*


  public Category updateCategory(String id, CategoryRequest categoryRequest) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    category.setName(categoryRequest.getName());
    category.setDescription(categoryRequest.getDescription());

    // Actualiza la URL de la imagen solo si hay una nueva imagen
    if (categoryRequest.getImageUrl() != null) {
      category.setImageUrl(categoryRequest.getImageUrl());
    }

    return categoryRepository.save(category);
  }

  public void deleteCategory(String id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    categoryRepository.delete(category);
  }*/
}
