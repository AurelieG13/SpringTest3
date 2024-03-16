package com.aguichardon.springtest3.controller;

import com.aguichardon.springtest3.model.EmailRequest;
import com.aguichardon.springtest3.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<HttpStatus> sendEmail(@RequestBody EmailRequest emailRequest) {
        // Vous pouvez ajouter une logique de validation ici

        // Envoi de l'email
        emailService.sendEmail("ap71000@gmail.com", emailRequest.getSubject(), emailRequest.getText());

        // Réponse réussie
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
