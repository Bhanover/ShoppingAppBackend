package com.project.ShoppingAppBackend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubCategoryRequest {
  private String name;
  private String description;
  private String subCategoryImage;
  private Long categoryId;

  // Getters y setters
}
