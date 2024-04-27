package com.project.ShoppingAppBackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSubCategoryResponse {
  private Long id;
  private String name;
}
