package com.haui.bookshopwebsite.service;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
