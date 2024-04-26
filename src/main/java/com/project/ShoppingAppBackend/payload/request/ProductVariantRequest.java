package com.project.ShoppingAppBackend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantRequest {
  private Long sizeId;
  private Integer stock;
  // Getters y Setters...
}
