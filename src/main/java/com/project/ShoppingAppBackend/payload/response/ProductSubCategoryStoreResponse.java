package com.project.ShoppingAppBackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubCategoryStoreResponse {
  private Long id;
  private String name;
  private String description;
  private String subCategoryImage;
  private Long categoryId;
  private String categoryName;
  private String categoryImage;
}
