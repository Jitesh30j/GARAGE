package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.RegisterRequest;
import com.garage.project.Owner.Dto.VerifyOtpRequest;

import java.util.Map;

public interface GarageService {

    Map<String, Object> registerGarage(RegisterRequest request);   // ✅ changed
    Map<String, Object> verifyOtp(VerifyOtpRequest request);       // ✅ changed
}