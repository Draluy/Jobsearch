package fr.raluy.jobsearchback.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class EmailSender {

    @Autowired
    public JavaMailSender emailSender;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public Future<?> sendEmail (final String email, final String subject, final String body) {
         return executor.submit(() -> {
            SimpleMailMessage templateMessage = new SimpleMailMessage();
            templateMessage.setFrom("david@raluy.fr");
            templateMessage.setSubject(subject);
            SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
            msg.setTo(email);
            msg.setText(body);
            emailSender.send(msg);
        });
    }
}
