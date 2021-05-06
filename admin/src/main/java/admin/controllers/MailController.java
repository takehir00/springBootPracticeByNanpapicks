package admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {

    @Autowired
    public JavaMailSender mailSender;

    @GetMapping("/mail")
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("0kutakehiro15@gmail.com");
        message.setTo("0kutakehiro15@gmail.com");
        message.setSubject("テスト");
        message.setText("テスト");
        mailSender.send(message);
    }
}
