package com.project.ShoppingAppBackend.payload.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStoreResponse {
  private Long id;
  private String name;
  private BigDecimal price;
  private String mainImageUrl;
  private String secondaryImageUrl;
  private Long categoryId;
  private Long subCategoryId;
  private String categoryName;
  private String subCategoryName;
  private List<ProductVariantResponse> variants;
}
