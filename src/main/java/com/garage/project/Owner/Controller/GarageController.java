package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.RegisterRequest;
import com.garage.project.Owner.Dto.VerifyOtpRequest;
import com.garage.project.Owner.Service.GarageService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/garage")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest request) {
        return garageService.registerGarage(request);
    }

    @PostMapping("/verify")
    public Map<String, Object> verify(@RequestBody VerifyOtpRequest request) {
        return garageService.verifyOtp(request);
    }


}
