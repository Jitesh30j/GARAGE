package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // ✅ Registration OTP
    @Override
    public void sendOtpEmail(String toEmail, String otp) {

        String subject = "OTP Verification";
        String message = "Your OTP is: " + otp + " (valid 10 min)";

        sendEmail(toEmail, subject, message); // reuse
    }

    // ✅ Forgot Password / Generic Email
    @Override
    public void sendEmail(String toEmail, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject(subject);
        msg.setText(message);

        mailSender.send(msg);
    }
}
