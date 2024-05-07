package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.models.ProductCategory;
import com.project.ShoppingAppBackend.models.ProductSubCategory;
import com.project.ShoppingAppBackend.payload.response.ProductSubCategoryStoreResponse;
import com.project.ShoppingAppBackend.payload.response.SimpleCategoryResponse;
import com.project.ShoppingAppBackend.payload.response.SimpleNameCategoryResponse;
import com.project.ShoppingAppBackend.repositories.ProductCategoryRepository;
import com.project.ShoppingAppBackend.repositories.ProductSubCategoryRepository;
import com.project.ShoppingAppBackend.service.CategoryService;
import com.project.ShoppingAppBackend.service.FileService;
import com.project.ShoppingAppBackend.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryStoreController {
  @Autowired private ImageService imageService;
  @Autowired private FileService fileService;
  @Autowired private CategoryService categoryService;
  @Autowired private ProductCategoryRepository productCategoryRepository;
  @Autowired private ProductSubCategoryRepository productSubCategoryRepository;

  @Transactional
  @GetMapping("/categories/{categoryId}/subcategories")
  public ResponseEntity<List<ProductSubCategoryStoreResponse>> getSubCategoriesByCategoryId(
      @PathVariable Long categoryId) {
    List<ProductSubCategory> subCategories =
        productSubCategoryRepository.findAllByCategoryId(categoryId);

    List<ProductSubCategoryStoreResponse> subCategoryResponses =
        subCategories.stream()
            .map(categoryService::convertToSubCategoryStoreResponse)
            .collect(Collectors.toList());

    if (!subCategoryResponses.isEmpty()) {
      // Utiliza la categoría de la primera subcategoría como representante para la opción "Ver
      // todo"
      ProductSubCategoryStoreResponse firstSubCategory = subCategoryResponses.get(0);

      ProductSubCategoryStoreResponse allItemsOption =
          new ProductSubCategoryStoreResponse(
              0L, // ID especial para 'Ver todo'
              "Ver todo",
              "Muestra todos los productos de esta categoría",
              firstSubCategory.getCategoryImage(), // Imagen de la categoría principal
              firstSubCategory.getCategoryId(), // ID de la categoría principal
              firstSubCategory.getCategoryName(), // Nombre de la categoría principal
              firstSubCategory
                  .getCategoryImage() // Usamos la misma imagen para mantener la coherencia
              );

      // Agrega "Ver todo" al inicio de la lista de respuestas
      subCategoryResponses.add(0, allItemsOption);
    }

    return ResponseEntity.ok(subCategoryResponses);
  }

  @GetMapping("/simple-name-categories")
  public List<SimpleNameCategoryResponse> getAllCategoriesWithSubcategoriesNames() {
    List<ProductCategory> categories = productCategoryRepository.findAll();
    return categories.stream()
        .map(categoryService::convertToCategorySimpleNameResponse)
        .collect(Collectors.toList());
  }
}
