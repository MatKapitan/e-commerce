package com.matkap.ecommerce.mail;

public interface MailService {

    void sendSimpleMessage(String to, String subject, String text);
}
