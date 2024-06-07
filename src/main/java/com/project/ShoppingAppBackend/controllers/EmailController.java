package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.service.EmailService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {

  @Autowired private EmailService emailService;
  private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

  // Enviar un email desde el formulario de contacto
  @PostMapping("/contact")
  public ResponseEntity<?> sendEmail(
      @RequestParam String nombre,
      @RequestParam @NotBlank String apellidos,
      @RequestParam @NotBlank @Pattern(regexp = "\\+\\d{1,3}\\d{9,15}") String telefono,
      @RequestParam @NotBlank @Email String email,
      @RequestParam @NotBlank String mercado,
      @RequestParam @NotBlank String asunto,
      @RequestParam @NotBlank String tema,
      @RequestParam @NotBlank String mensaje) {
    try {
      // Formatear el contenido del email
      String content =
          String.format(
              "Nombre: %s\nApellidos: %s\nTeléfono: %s\nEmail: %s\nMercado: %s\nAsunto: %s\nTema: %s\nMensaje: %s",
              nombre, apellidos, telefono, email, mercado, asunto, tema, mensaje);

      // Enviar el email
      emailService.sendEmail("bil@styleswype.shop", "Nuevo Mensaje de Contacto", content);

      // Responder con éxito
      return ResponseEntity.ok("Mensaje enviado");
    } catch (Exception e) {
      // Registrar el error y responder con un error interno del servidor
      logger.error("Error al procesar el formulario de contacto: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al enviar mensaje");
    }
  }
}
