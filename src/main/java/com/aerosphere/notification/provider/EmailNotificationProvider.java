package com.aerosphere.notification.provider;

import com.aerosphere.notification.entity.Notification;
import com.aerosphere.notification.entity.NotificationStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Purpose:
 * Sends notifications using SMTP email.
 *
 * Responsibilities:
 * - Send notifications via JavaMailSender.
 * - Deliver email notifications.
 * - Return the updated Notification entity.
 *
 * Module:
 * Notification
 */
@Component
public class EmailNotificationProvider
        implements NotificationProvider {

    private final JavaMailSender mailSender;

    public EmailNotificationProvider(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Notification send(Notification notification) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, false, "UTF-8");

            helper.setTo(notification.getRecipient());
            helper.setSubject(notification.getSubject());
            helper.setText(notification.getMessage(), false);

            mailSender.send(mimeMessage);

            notification.setNotificationStatus(NotificationStatus.SENT);
            notification.setSentAt(java.time.LocalDateTime.now());
            notification.setErrorMessage(null);

            return notification;

        } catch (MessagingException | MailException exception) {

            notification.setNotificationStatus(NotificationStatus.FAILED);
            notification.setErrorMessage(exception.getMessage());

            return notification;
        }
    }
}