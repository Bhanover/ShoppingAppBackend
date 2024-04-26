package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.ProductImage;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
  // Métodos para manejar imágenes

  // Asegúrate de que tu método en el repositorio soporte también la paginación
  /* @Query(
      "SELECT new map(pi.id as id, pi.imageUrl as imageUrl) FROM ProductImage pi ORDER BY pi.createdAt DESC")
  Page<Map<String, Object>> findAllImagesAsMap(Pageable pageable);

  /*@Query("SELECT new map(pi.id as id, pi.imageUrl as imageUrl) FROM ProductImage pi")
  List<Map<String, Object>> findAllImagesAsMap();*/
  // Nuevo método para imágenes asociadas
  /*@Query(
      "SELECT new map(pi.id as id, pi.imageUrl as imageUrl) FROM ProductImage pi WHERE pi.isAssociated = true ORDER BY pi.createdAt DESC")
  Page<Map<String, Object>> findAllAssociatedImagesAsMap(Pageable pageable);

  // Nuevo método para imágenes no asociadas
  @Query(
      "SELECT new map(pi.id as id, pi.imageUrl as imageUrl) FROM ProductImage pi WHERE pi.isAssociated = false ORDER BY pi.createdAt DESC")
  Page<Map<String, Object>> findAllNonAssociatedImagesAsMap(Pageable pageable);

  @Modifying
  @Transactional
  @Query("DELETE FROM ProductImage p WHERE p.id IN :ids")
  void deleteAllByIdIn(@Param("ids") List<String> ids);*/
}
