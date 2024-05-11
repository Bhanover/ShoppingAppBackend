package com.project.ShoppingAppBackend.security.service;

import com.project.ShoppingAppBackend.models.*;
import com.project.ShoppingAppBackend.repositories.ProductSizeRepository;
import com.project.ShoppingAppBackend.repositories.RoleRepository;
import com.project.ShoppingAppBackend.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired private UserRepository userRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private ProductSizeRepository productSizeRepository;

  @Override
  @Transactional
  public void run(String... args) {
    createRoleIfNotFound(RoleName.ROLE_ADMIN);
    createRoleIfNotFound(RoleName.ROLE_USER);
    createUserIfNotFound("admin", "balto123", RoleName.ROLE_ADMIN);
  }

  @PostConstruct
  public void initSizes() {
    initClothingSizes();
    initShoeSizes();
  }

  private void createUserIfNotFound(String username, String password, RoleName roleName) {
    userRepository
        .findByUsername(username)
        .orElseGet(
            () -> {
              Role adminRole =
                  roleRepository
                      .findByName(roleName)
                      .orElseThrow(() -> new IllegalStateException("Role must exist"));
              User user = new User();
              user.setUsername(username);
              user.setPassword(passwordEncoder.encode(password));
              user.setRoles(Collections.singleton(adminRole));
              return userRepository.save(user);
            });
  }

  private void createRoleIfNotFound(RoleName roleName) {
    roleRepository
        .findByName(roleName)
        .orElseGet(
            () -> {
              Role role = new Role(roleName);
              return roleRepository.save(role);
            });
  }

  private void initClothingSizes() {
    String[] clothingSizes = {"XS", "S", "M", "L", "XL"};
    for (String size : clothingSizes) {
      createSizeIfNotFound(size, SizeType.CLOTHING);
    }
  }

  private void initShoeSizes() {
    int[] shoeSizes = {35, 36, 37, 38, 39, 40, 41, 42};
    for (int size : shoeSizes) {
      createSizeIfNotFound(String.valueOf(size), SizeType.SHOES);
    }
  }

  private void createSizeIfNotFound(String label, SizeType sizeType) {
    productSizeRepository
        .findByLabel(label)
        .orElseGet(
            () -> {
              ProductSize newSize = new ProductSize();
              newSize.setLabel(label);
              newSize.setSizeType(sizeType);
              return productSizeRepository.save(newSize);
            });
  }
}
