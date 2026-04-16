package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.RegisterRequest;
import com.garage.project.Owner.Dto.VerifyOtpRequest;
import com.garage.project.Owner.Entity.Garage;
import com.garage.project.Owner.Repo.GarageRepository;
import com.garage.project.Owner.Service.EmailService;
import com.garage.project.Owner.Service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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

        // store temp password (before OTP verify)
        g.setTempPassword(request.getPassword());

        String otp = generateOtp();

        g.setRegOtp(otp);
        g.setRegOtpVerified(0);
        g.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));

        g.setCreatedAt(LocalDateTime.now());
        g.setUpdatedAt(LocalDateTime.now());

        garageRepository.save(g);

        emailService.sendOtpEmail(g.getEmail(), otp);

        res.put("status", "success");
        res.put("message", "OTP sent successfully");

        return res;
    }

    @Override
    public Map<String, Object> verifyOtp(VerifyOtpRequest request) {

        Map<String, Object> res = new HashMap<>();

        Garage g = garageRepository.findByEmail(request.getEmail()).orElse(null);

        if (g == null) {
            res.put("status", "error");
            res.put("message", "Garage not found");
            return res;
        }

        if (g.getRegOtp() == null || !g.getRegOtp().equals(request.getOtp())) {
            res.put("status", "error");
            res.put("message", "Invalid OTP");
            return res;
        }

        if (g.getOtpExpiryTime() == null ||
                g.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            res.put("status", "error");
            res.put("message", "OTP expired");
            return res;
        }

        // OTP verified
        g.setRegOtpVerified(1);
        g.setRegOtp(null);
        g.setOtpExpiryTime(null);

        // password move temp → main (ENCRYPTED)
        if (g.getTempPassword() != null) {
            g.setPassword(encoder.encode(g.getTempPassword()));
            g.setTempPassword(null);
        }

        g.setUpdatedAt(LocalDateTime.now());

        garageRepository.save(g);

        res.put("status", "success");
        res.put("message", "Registration completed");

        return res;
    }
}