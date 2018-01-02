package com.ramon.teste.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/email")
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping
	public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setText("Hello from Spring Boot Application");
        message.setTo("kverrna@gmail.com");
        message.setFrom("kverrna@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
	
}