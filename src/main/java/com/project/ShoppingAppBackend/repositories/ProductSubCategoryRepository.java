package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.Product;
import com.project.ShoppingAppBackend.models.ProductSubCategory;
import com.project.ShoppingAppBackend.payload.response.ProductSubCategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {
  @Query("SELECT sc FROM ProductSubCategory sc JOIN FETCH sc.category")
  List<ProductSubCategory> findAllWithCategoryName();

  @Query("SELECT sc FROM ProductSubCategory sc WHERE sc.category.id = :categoryId")
  List<ProductSubCategory> findAllByCategoryId(@Param("categoryId") Long categoryId);
}
