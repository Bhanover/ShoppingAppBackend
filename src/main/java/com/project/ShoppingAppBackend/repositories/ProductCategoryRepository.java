package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
  @Query("SELECT c FROM ProductCategory c LEFT JOIN FETCH c.subCategories")
  List<ProductCategory> findAllWithSubCategories();
}
