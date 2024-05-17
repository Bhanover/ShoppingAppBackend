package com.project.ShoppingAppBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

  @Autowired private JavaMailSender mailSender;

  private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

  public void sendEmail(String to, String subject, String content) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("billydht6@gmail.com");
      message.setTo(to);
      message.setSubject(subject);
      message.setText(content);
      mailSender.send(message);
      logger.info("Correo enviado exitosamente a {}", to);
    } catch (Exception e) {
      logger.error("Error al enviar correo: {}", e.getMessage());
      throw new RuntimeException("Fallo al enviar el correo", e);
    }
  }
}
