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
            return new ApiResponse("failed", "User not found");
        }

        Garage garage = optionalGarage.get();

        String otp = generateOtp();

        garage.setForgotOtp(otp);
        garage.setOtpExpiryTime(LocalDateTime.now().plusMinutes(10));

        garageRepository.save(garage);

        emailService.sendOtpEmail(garage.getEmail(), otp);

        return new ApiResponse("success", "OTP sent successfully");
    }

    @Override
    public ApiResponse verifyOtp(VerifyOtpRequest request) {

        Optional<Garage> optionalGarage = garageRepository.findByEmail(request.getEmail());

        if (optionalGarage.isEmpty()) {
            return new ApiResponse("failed", "User not found");
        }

        Garage garage = optionalGarage.get();

            return new ApiResponse("failed", "Invalid OTP");
        }

        if (garage.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            return new ApiResponse("failed", "OTP expired");
        }

        garageRepository.save(garage);

        return new ApiResponse("success", "OTP verified");
    }

    @Override
    public ApiResponse resetPassword(ResetPasswordRequest request) {

        Optional<Garage> optionalGarage = garageRepository.findByEmail(request.getEmail());

        if (optionalGarage.isEmpty()) {
            return new ApiResponse("failed", "User not found");
        }

        Garage garage = optionalGarage.get();

            return new ApiResponse("failed", "OTP not verified");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(request.getNewpassword());

        garage.setPassword(encodedPassword);

        garage.setForgotOtp(null);
        garage.setForgotOtpVerified(0);

        garageRepository.save(garage);

        return new ApiResponse("success", "Password reset successful");
    }
}