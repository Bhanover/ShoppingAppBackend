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
    if (userRepository.findByUsername("alison").isEmpty()) {
      Role adminRole =
          roleRepository
              .findByName(RoleName.ROLE_ADMIN)
              .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_ADMIN)));
      User admin = new User();
      admin.setUsername("alison");
      admin.setPassword(passwordEncoder.encode("alisonmijaelvaleriA.1"));
      admin.setRoles(Collections.singleton(adminRole));
      userRepository.save(admin);
    }
  }

  @PostConstruct
  public void initSizes() {
    initClothingSizes();
    initShoeSizes();
  }

  private void initClothingSizes() {
    for (ClothingSize size : ClothingSize.values()) {
      String label = size.name();
      productSizeRepository
          .findByLabel(label)
          .orElseGet(
              () -> {
                ProductSize newSize = new ProductSize();
                newSize.setLabel(label);
                newSize.setSizeType(SizeType.CLOTHING);
                newSize.setClothingSize(size);
                return productSizeRepository.save(newSize);
              });
    }
  }

  private void initShoeSizes() {
    for (ShoeSize size : ShoeSize.values()) {
      String label = size.name();
      productSizeRepository
          .findByLabel(label)
          .orElseGet(
              () -> {
                ProductSize newSize = new ProductSize();
                newSize.setLabel(label);
                newSize.setSizeType(SizeType.SHOES);
                newSize.setShoeSize(size);
                return productSizeRepository.save(newSize);
              });
    }
  }
}
