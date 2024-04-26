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
}
