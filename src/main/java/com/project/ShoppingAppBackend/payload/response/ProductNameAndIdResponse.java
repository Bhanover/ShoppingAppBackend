package com.project.ShoppingAppBackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductNameAndIdResponse {
  private Long id;
  private String name;
}
