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

  public String resizeImageBase64(String base64Image, int width, int height, String outputFormat)
      throws IOException {
    byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);

    ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
    BufferedImage originalImage = ImageIO.read(inputStream);

    BufferedImage resizedImage = Thumbnails.of(originalImage).size(width, height).asBufferedImage();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(resizedImage, outputFormat, outputStream);
    byte[] outputBytes = outputStream.toByteArray();

    return "data:image/"
        + outputFormat
        + ";base64,"
        + Base64.getEncoder().encodeToString(outputBytes);
  }

  public String uploadImage(String base64Image, String folderName) throws IOException {
    Map uploadResult =
        cloudinary
            .uploader()
            .upload(base64Image, ObjectUtils.asMap("folder", folderName, "resource_type", "image"));
    return uploadResult.get("url").toString();
  }
}

 /*
   public byte[] resizeImage(MultipartFile file, int width, int height, String outputFormat, float quality) throws IOException {
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

     // Using Thumbnails to resize the image
     Thumbnails.of(file.getInputStream())
         .size(width, height)  // Use size instead of forceSize to keep aspect ratio if necessary
         .outputFormat(outputFormat)
         .outputQuality(quality)  // Control the quality of the output image
         .toOutputStream(outputStream);

     return outputStream.toByteArray();
 }
    */
