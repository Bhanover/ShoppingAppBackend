package com.project.ShoppingAppBackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
public class ImageService {
  @Autowired Cloudinary cloudinary;

  public byte[] resizeImage(MultipartFile file, int width, int height) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    Thumbnails.of(file.getInputStream())
        .forceSize(width, height)
        .outputFormat("jpeg")
        .toOutputStream(outputStream);

    return outputStream.toByteArray();
  }

  public String resizeImageBase64(String base64Image, int width, int height) throws IOException {

    String[] parts = base64Image.split(",");
    String imageDataBytes = parts[1];
    String imageType =
        parts[0].split("/")[1].split(";")[0];

    byte[] imageBytes = Base64.getDecoder().decode(imageDataBytes);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
    BufferedImage originalImage = ImageIO.read(inputStream);

    BufferedImage resizedImage = Thumbnails.of(originalImage).size(width, height).asBufferedImage();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(resizedImage, imageType, outputStream);
    byte[] outputBytes = outputStream.toByteArray();

    return "data:image/" + imageType + ";base64," + Base64.getEncoder().encodeToString(outputBytes);
  }

  public String uploadImage(String base64Image, String folderName) throws IOException {
    Map uploadResult =
        cloudinary
            .uploader()
            .upload(base64Image, ObjectUtils.asMap("folder", folderName, "resource_type", "image"));
    return uploadResult.get("url").toString();
  }
}

