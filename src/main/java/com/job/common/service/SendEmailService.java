package com.job.common.service;


public interface SendEmailService {
    void sendEmail(String to, String subject, String text);

}
