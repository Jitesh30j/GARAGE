package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.RegisterRequest;
import com.garage.project.Owner.Dto.VerifyOtpRequest;
import com.garage.project.Owner.Entity.Garage;
import com.garage.project.Owner.Repo.GarageRepository;
import com.garage.project.Owner.Service.EmailService;
import com.garage.project.Owner.Service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private EmailService emailService;

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    @Override
    public Map<String, Object> registerGarage(RegisterRequest request) {

        Map<String, Object> res = new HashMap<>();

        if (garageRepository.findByEmail(request.getEmail()).isPresent()) {
            res.put("status", "error");
            res.put("message", "Email already registered");
            return res;
        }

        Garage g = new Garage();

        g.setGarageName(request.getGarageName());
        g.setOwnerName(request.getOwnerName());
        g.setEmail(request.getEmail());
        g.setPhone(request.getPhone());
        g.setAddress(request.getAddress());
        g.setCity(request.getCity());
        g.setState(request.getState());
        g.setPin(request.getPin());
        g.setOpeningTime(request.getOpeningTime());
        g.setClosingTime(request.getClosingTime());

        g.setTempPassword(request.getPassword());

        String otp = generateOtp();

        g.setRegOtp(otp);
        g.setRegOtpVerified(1);   // ✅ OTP GENERATED → 0
        g.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));

        g.setCreatedAt(LocalDateTime.now());
        g.setUpdatedAt(LocalDateTime.now());

        garageRepository.save(g);

        emailService.sendOtpEmail(g.getEmail(), otp);

        res.put("status", "success");
        res.put("message", "OTP sent");
        return res;
    }

    @Override
    public Map<String, Object> verifyOtp(VerifyOtpRequest request) {

        Map<String, Object> res = new HashMap<>();

        Garage g = garageRepository.findByEmail(request.getEmail()).orElse(null);

        if (g == null) {
            res.put("status", "error");
            res.put("message", "User not found");
            return res;
        }

        if (g.getRegOtp() == null || !g.getRegOtp().equals(request.getOtp())) {
            res.put("status", "error");
            res.put("message", "Invalid OTP");
            return res;
        }

        if (g.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            res.put("status", "error");
            res.put("message", "OTP expired");
            return res;
        }

        g.setRegOtpVerified(0);   // ✅ OTP VERIFIED
        g.setRegOtp(null);
        g.setOtpExpiryTime(null);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        g.setPassword(encoder.encode(g.getTempPassword()));

        g.setTempPassword(null);

        garageRepository.save(g);

        res.put("status", "success");
        res.put("message", "Registration completed");
        return res;
    }
}