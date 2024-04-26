package com.project.ShoppingAppBackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantResponse {
  private Long id;
  private String sizeLabel;
  private String clothingSize;
  private String shoeSize;
  private Integer stock;
}
