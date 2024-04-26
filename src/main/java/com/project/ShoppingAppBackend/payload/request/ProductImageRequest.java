package com.project.ShoppingAppBackend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageRequest {
  private String name;
  private int orderIndex;
  private String type;
  private String imageUrl;
}
