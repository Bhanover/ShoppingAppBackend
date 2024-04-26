package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.ProductSubCategory;
import com.project.ShoppingAppBackend.models.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {}
