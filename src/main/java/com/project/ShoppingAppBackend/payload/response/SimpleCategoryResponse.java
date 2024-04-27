package com.project.ShoppingAppBackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleCategoryResponse {
  private Long id;
  private String name;
  private List<SimpleSubCategoryResponse> subCategories;
}
