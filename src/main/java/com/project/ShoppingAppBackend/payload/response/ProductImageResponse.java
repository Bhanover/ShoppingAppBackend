package com.project.ShoppingAppBackend.payload.response;

import com.project.ShoppingAppBackend.models.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponse {
  private String name;
  private String imageUrl;
  private int order_index;
  private String type;
}
