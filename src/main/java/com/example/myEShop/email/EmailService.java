package com.example.myEShop.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service class for sending emails.
 * <p>
 * This service implements the {@link EmailSender} interface and provides
 * functionality to send emails asynchronously.
 * </p>
 */
@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    /**
     * Sends an email to the specified recipient.
     * <p>
     * This method sends an email asynchronously using {@link JavaMailSender}.
     * </p>
     *
     * @param to the email address of the recipient
     * @param email the content of the email to be sent
     * @throws IllegalStateException if there is a failure in sending the email
     */
    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email", e);
        }
    }
}
