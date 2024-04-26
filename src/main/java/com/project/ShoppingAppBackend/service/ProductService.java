package com.project.ShoppingAppBackend.service;

import com.project.ShoppingAppBackend.models.*;
import com.project.ShoppingAppBackend.payload.request.ProductImageRequest;
import com.project.ShoppingAppBackend.payload.request.ProductRequest;
import com.project.ShoppingAppBackend.payload.request.ProductVariantRequest;
import com.project.ShoppingAppBackend.payload.response.ProductImageResponse;
import com.project.ShoppingAppBackend.payload.response.ProductResponse;
import com.project.ShoppingAppBackend.payload.response.ProductVariantResponse;
import com.project.ShoppingAppBackend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
  @Autowired ProductRepository productRepository;
  @Autowired ProductCategoryRepository productCategoryRepository;
  @Autowired ProductSubCategoryRepository productSubCategoryRepository;
  @Autowired FileService fileService;
  @Autowired ImageService imageService;
  @Autowired ProductImageRepository productImageRepository;
  @Autowired ProductSizeRepository productSizeRepository;
  @Autowired ProductVariantRepository productVariantRepository;

  private Product addProduct(ProductRequest productRequest) {
    Product product = new Product();
    product.setName(productRequest.getName());
    product.setType(productRequest.getType());
    product.setPrice(productRequest.getPrice());
    product.setStock(productRequest.getStock());
    product.setDescription(productRequest.getDescription());

    product.setCategory(
        productCategoryRepository
            .findById(productRequest.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("Category not found")));

    product.setSubCategory(
        productSubCategoryRepository
            .findById(productRequest.getSubCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("SubCategory not found")));

    return productRepository.save(product);
  }

  public Long createProductWithImages(ProductRequest productRequest) {
    try {
      Product product = addProduct(productRequest);
      addProductImages(product, productRequest.getImages());
      addProductVariants(product, productRequest.getVariants());
      return product.getId();
    } catch (EntityNotFoundException e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Category or SubCategory not found", e);
    } catch (IOException e) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "Error processing images", e);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "Error creating product", e);
    }
  }

  private void addProductImages(Product product, List<ProductImageRequest> imageRequests)
      throws IOException {
    String folderName =
        "Shopping/product/" + product.getName().trim().replace(" ", "_").toLowerCase();

    for (ProductImageRequest imageRequest : imageRequests) {
      String resizedImage =
          imageService.resizeImageBase64(imageRequest.getImageUrl(), 1080, 1080, "jpg");
      String imageUrl = imageService.uploadImage(resizedImage, folderName);

      ProductImage productImage = new ProductImage();
      productImage.setName(imageRequest.getName());
      productImage.setImageUrl(imageUrl);
      productImage.setOrder_index(imageRequest.getOrderIndex());
      productImage.setType(imageRequest.getType());
      productImage.setProduct(product);
      // product.getImages().add(productImage);
      productImageRepository.save(productImage);
      product.addImage(productImage);
    }
  }

  private void addProductVariants(Product product, List<ProductVariantRequest> variantRequests) {
    for (ProductVariantRequest variantRequest : variantRequests) {
      ProductSize size =
          productSizeRepository
              .findById(variantRequest.getSizeId())
              .orElseThrow(() -> new EntityNotFoundException("ProductSize not found"));

      ProductVariant variant = new ProductVariant();
      variant.setProduct(product);
      variant.setSize(size);
      variant.setStock(variantRequest.getStock());
      product.getVariants().add(variant);
      productVariantRepository.save(variant);
    }
  }

  public ProductResponse convertProductResponse(Product product) {
    List<ProductVariantResponse> variantResponses =
        product.getVariants().stream()
            .map(
                variant ->
                    new ProductVariantResponse(
                        variant.getId(),
                        variant.getSize().getLabel(),
                        variant
                            .getSize()
                            .getClothingSize()
                            .name(), // Asegúrate de manejar nulls aquí
                        variant.getSize().getShoeSize() != null
                            ? variant.getSize().getShoeSize().name()
                            : null,
                        variant.getStock()))
            .collect(Collectors.toList());

    return new ProductResponse(
        product.getName(),
        product.getType(),
        product.getPrice(),
        product.getStock(),
        product.getDescription(),
        product.getImages().stream()
            .map(
                img ->
                    new ProductImageResponse(
                        img.getName(), img.getImageUrl(), img.getOrder_index(), img.getType()))
            .collect(Collectors.toList()),
        product.getCategory() != null ? product.getCategory().getId() : null,
        product.getSubCategory() != null ? product.getSubCategory().getId() : null,
        variantResponses);
  }
}