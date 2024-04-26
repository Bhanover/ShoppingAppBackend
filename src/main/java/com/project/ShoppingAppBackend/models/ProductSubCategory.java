package com.project.ShoppingAppBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itemSubCategory")
public class ItemSubCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String name;
  private String description;
  private String imageSubCategory;

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
public class ProductSubCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private String subCategoryImage;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // Relación con ProductCategory
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private ProductCategory category;

  @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products = new ArrayList<>();
  // Getters y Setters...

}
