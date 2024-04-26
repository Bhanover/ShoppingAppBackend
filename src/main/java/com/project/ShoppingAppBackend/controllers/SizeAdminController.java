package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.models.ProductSize;
import com.project.ShoppingAppBackend.repositories.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
public class SizeAdminController {

  @Autowired private ProductSizeRepository sizeRepository;

  @GetMapping
  public List<ProductSize> getAllSizes() {
    return sizeRepository.findAll();
  }
}
