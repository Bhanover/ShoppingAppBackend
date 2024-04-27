package com.project.ShoppingAppBackend.payload.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryWithSubCategoryResponse {
  private Long id;
  private String name;
  private String description;
  private String categoryImage;
  private List<ProductSubCategoryResponse> subCategories;

  public ProductCategoryWithSubCategoryResponse(
      Long id, String name, List<ProductSubCategoryResponse> subCategories) {
    this.id = id;
    this.name = name;
    this.subCategories = subCategories;
  }
}
