package com.project.ShoppingAppBackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.project.ShoppingAppBackend.models.Product;
import com.project.ShoppingAppBackend.models.ProductImage;
import com.project.ShoppingAppBackend.payload.request.ProductRequest;
import com.project.ShoppingAppBackend.repositories.ProductCategoryRepository;
import com.project.ShoppingAppBackend.repositories.ProductImageRepository;
import com.project.ShoppingAppBackend.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {

  @Autowired private Cloudinary cloudinary;

  @Autowired private ProductRepository productRepository;

  @Autowired private ProductImageRepository productImageRepository;

  @Autowired private ProductCategoryRepository productCategoryRepository;
  @Autowired private ImageService imageService;

  public String uploadCategoryImage(byte[] resizedImage, String fileName, String categoryName)
      throws IOException {
    String originalFileName = StringUtils.cleanPath(fileName);
    String folderName =
        "Shopping/productCategory" + categoryName.trim().replace(" ", "_").toLowerCase();

    Map<?, ?> uploadResult =
        cloudinary
            .uploader()
            .upload(
                resizedImage,
                ObjectUtils.asMap(
                    "resource_type", "image",
                    "folder", folderName,
                    "public_id", originalFileName));

    return (String) uploadResult.get("url");
  }

  public void uploadProductImages(MultipartFile[] files, Long productId) throws IOException {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () -> new IllegalArgumentException("Producto no encontrado con ID: " + productId));

    for (MultipartFile file : files) {
      if (!file.getContentType().startsWith("image/")) {
        throw new IllegalArgumentException("El archivo no es una imagen válida.");
      }
      byte[] resizedImage = imageService.resizeImage(file, 1080, 1080);
      String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

      String folderName =
          "Shopping/product/" + product.getName().trim().replace(" ", "_").toLowerCase();

      Map<?, ?> uploadResult =
          cloudinary
              .uploader()
              .upload(
                  resizedImage,
                  ObjectUtils.asMap(
                      "resource_type", "image",
                      "folder", folderName,
                      "public_id", originalFileName));

      String imageUrl = (String) uploadResult.get("url");

      ProductImage productImage = new ProductImage();
      productImage.setName(originalFileName);
      productImage.setImageUrl(imageUrl);
      productImage.setProduct(product);
      productImageRepository.save(productImage);
    }
  }
}
