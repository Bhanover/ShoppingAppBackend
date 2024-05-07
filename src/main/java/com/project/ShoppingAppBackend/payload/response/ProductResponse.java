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
public class ProductResponse {
  private Long id;

  @NotBlank(message = "El nombre del producto es obligatorio")
  private String name;

  @NotBlank(message = "El tipo del producto es obligatorio")
  private String type;

  @NotNull(message = "El precio del producto no puede ser nulo")
  @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
  private BigDecimal price;

  @NotNull(message = "El stock no puede ser nulo")
  @Min(value = 0, message = "El stock no puede ser negativo")
  private Integer stock;

  @NotBlank(message = "La descripción del producto es obligatoria")
  private String description;

  @NotEmpty(message = "Debe incluir al menos una imagen")
  private List<ProductImageResponse> images;

  @NotNull(message = "La categoría del producto es obligatoria")
  private Long categoryId;

  @NotNull(message = "La subcategoría del producto es obligatoria")
  private Long subCategoryId;

  private List<ProductVariantResponse> variants;
}
