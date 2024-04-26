package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.ProductSize;
import com.project.ShoppingAppBackend.models.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
  Optional<ProductSize> findByLabel(String label);
}
