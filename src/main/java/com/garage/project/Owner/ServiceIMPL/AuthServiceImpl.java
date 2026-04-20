package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.*;
import com.garage.project.Owner.Entity.Garage;
import com.garage.project.Owner.Repo.GarageRepository;
import com.garage.project.Owner.Service.AuthService;
import com.garage.project.Owner.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private EmailService emailService;

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    @Override
    public ApiResponse forgotPassword(ForgotPasswordRequest request) {

        Optional<Garage> optionalGarage =
                garageRepository.findByEmailOrPhone(request.getEmail(), request.getPhone());

        if (optionalGarage.isEmpty()) {
            return new ApiResponse("failed", 404, "User not found", null);
        }

        Garage garage = optionalGarage.get();

        String otp = generateOtp();

        garage.setForgotOtp(otp);
        garage.setForgotOtpVerified(0);   // OTP GENERATED → 0
        garage.setOtpExpiryTime(LocalDateTime.now().plusMinutes(10));

        garageRepository.save(garage);

        emailService.sendOtpEmail(garage.getEmail(), otp);

        return new ApiResponse("success", 200, "OTP sent successfully", null);
    }

    @Override
    public ApiResponse verifyOtp(VerifyOtpRequest request) {

        Optional<Garage> optionalGarage = garageRepository.findByEmail(request.getEmail());

        if (optionalGarage.isEmpty()) {
            return new ApiResponse("failed", 404, "User not found", null);
        }

        Garage garage = optionalGarage.get();

        if (garage.getForgotOtp() == null ||
                !garage.getForgotOtp().equals(request.getOtp())) {
            return new ApiResponse("failed", 400, "Invalid OTP", null);
        }

        if (garage.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            return new ApiResponse("failed", 400, "OTP expired", null);
        }

        garage.setForgotOtpVerified(1);   // OTP VERIFIED → 1
        garageRepository.save(garage);

        return new ApiResponse("success", 200, "OTP verified", null);
    }

    @Override
    public ApiResponse resetPassword(ResetPasswordRequest request) {

        Optional<Garage> optionalGarage = garageRepository.findByEmail(request.getEmail());

        if (optionalGarage.isEmpty()) {
            return new ApiResponse("failed", 404, "User not found", null);
        }

        Garage garage = optionalGarage.get();

        if (garage.getForgotOtpVerified() == null ||
                garage.getForgotOtpVerified() != 1) {
            return new ApiResponse("failed", 400, "OTP not verified", null);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(request.getNewpassword());

        garage.setPassword(encodedPassword);

        // Reset OTP state
        garage.setForgotOtp(null);
        garage.setForgotOtpVerified(0);

        garageRepository.save(garage);

        return new ApiResponse("success", 200, "Password reset successful", null);
    }
}