package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  /*
  @Query(
      "SELECT c FROM ClothingItem c JOIN FETCH c.images i WHERE i.position = 'principal' AND i.isAssociated = true")
  List<Product> findAllWithMainAssociatedImages();

  @EntityGraph(value = "clothingItem-graph", type = EntityGraph.EntityGraphType.LOAD)
  Page<Product> findByCategories_Id(String categoryId, Pageable pageable);

  @EntityGraph(value = "clothingItem-graph", type = EntityGraph.EntityGraphType.LOAD)
  Page<Product> findAll(Pageable pageable);*/
}
