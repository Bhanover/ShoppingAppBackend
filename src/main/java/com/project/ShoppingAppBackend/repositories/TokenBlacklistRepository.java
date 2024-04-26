package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {
  boolean existsByToken(String token);
}
