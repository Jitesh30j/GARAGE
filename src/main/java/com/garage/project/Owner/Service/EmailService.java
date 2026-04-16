package com.garage.project.Owner.Service;

public interface EmailService {

    void sendOtpEmail(String toEmail, String otp);

    void sendEmail(String toEmail, String subject, String message); // ✅ ADD THIS
}
