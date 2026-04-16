package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.ApiResponse;
import com.garage.project.Owner.Dto.ForgotPasswordRequest;
import com.garage.project.Owner.Dto.ResetPasswordRequest;
import com.garage.project.Owner.Dto.VerifyOtpRequest;

public interface AuthService {
    ApiResponse forgotPassword(ForgotPasswordRequest request);
    ApiResponse verifyOtp(VerifyOtpRequest request);
    ApiResponse resetPassword(ResetPasswordRequest request);
}
