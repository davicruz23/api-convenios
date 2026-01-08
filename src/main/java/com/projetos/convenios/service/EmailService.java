package com.projetos.convenios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendPartnerAccessEmail(String to, String accessLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Acesso ao Portal do Parceiro");
        message.setText("""
            Olá!

            Clique no link abaixo para acessar o portal do parceiro:

            %s

            Esse link é válido por tempo limitado e pode ser usado apenas uma vez.
            """.formatted(accessLink));

        mailSender.send(message);
    }
}