package com.project.ShoppingAppBackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubCategoryResponse {
  private Long id;
  private String name;
  private String description;
  private String subCategoryImage;
  private String categoryName;

  public ProductSubCategoryResponse(
      Long id, String name, String description, String subCategoryImage) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.subCategoryImage = subCategoryImage;
  }

  public ProductSubCategoryResponse(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  // Getters y setters
}
