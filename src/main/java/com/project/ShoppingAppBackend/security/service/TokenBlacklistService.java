package com.project.ShoppingAppBackend.security.service;

import com.project.ShoppingAppBackend.repositories.TokenBlacklistRepository;
import com.project.ShoppingAppBackend.models.TokenBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

  @Autowired private TokenBlacklistRepository tokenBlacklistRepository;

  public void blacklistToken(String token) {
    if (!tokenBlacklistRepository.existsByToken(token)) {
      tokenBlacklistRepository.save(new TokenBlacklist(token));
    }
  }

  public boolean isTokenBlacklisted(String token) {
    return tokenBlacklistRepository.existsByToken(token);
  }
}
