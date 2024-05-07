package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p FROM Product p JOIN FETCH p.images WHERE p.subCategory.id = :subCategoryId")
  List<Product> findAllBySubCategoryId(Long subCategoryId);

  List<Product> findAllByCategoryId(Long categoryId);
}
