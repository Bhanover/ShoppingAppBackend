package com.project.ShoppingAppBackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageStoreResponse {
  private String imageUrl;
  private int order_index;
  private String type;
}
