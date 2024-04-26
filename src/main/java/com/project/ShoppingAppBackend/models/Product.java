package com.project.ShoppingAppBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String type;
  private BigDecimal price;
  private Integer stock;
  private String description;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @OneToMany(
      mappedBy = "product",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<ProductVariant> variants = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = true)
  private ProductCategory category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sub_category_id", nullable = true)
  private ProductSubCategory subCategory;

  // Relación con ProductImage
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("order_index ASC")
  private List<ProductImage> images = new ArrayList<>();

  // Getters y Setters...
  public void addImage(ProductImage image) {
    this.images.add(image);
    image.setProduct(this);
  }

  public void removeImage(ProductImage image) {
    this.images.remove(image);
    image.setProduct(null);
  }
}
