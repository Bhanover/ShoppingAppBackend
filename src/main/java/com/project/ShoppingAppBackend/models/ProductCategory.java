package com.project.ShoppingAppBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itemCategory")
public class ItemCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String name;
  private String description;

  // Relación con ClothingItem
  private String imageCategory;

  @ManyToMany(mappedBy = "categories")
  @JsonIgnore
  private Set<ClothingItem> clothingItems = new HashSet<>();

  // Constructores, Getters y Setters

}
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private String categoryImage;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // Relación inversa con Product
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductSubCategory> subCategories = new ArrayList<>();

  @OneToMany(mappedBy = "category")
  private List<Product> products = new ArrayList<>();

  // Getters y Setters...
}
