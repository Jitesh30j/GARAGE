package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.*;
import com.garage.project.Owner.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Forgot Password - send OTP
    @PostMapping("/forgot-password")
    public ApiResponse forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return authService.forgotPassword(request);
    }

    // ✅ VERIFY FORGOT PASSWORD OTP (NEW)
    @PostMapping("/verify-forgot-otp")
    public ApiResponse verifyForgotOtp(@RequestBody VerifyOtpRequest request) {
        return authService.verifyOtp(request);
    }

    // Reset Password
    @PostMapping("/reset-password")
    public ApiResponse resetPassword(@RequestBody ResetPasswordRequest request) {
        return authService.resetPassword(request);
    }
}