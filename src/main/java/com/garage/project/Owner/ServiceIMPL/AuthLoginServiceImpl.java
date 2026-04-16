package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.LoginRequestDTO;
import com.garage.project.Owner.Dto.LoginResponseDTO;
import com.garage.project.Owner.Entity.Garage;
import com.garage.project.Owner.Repo.GarageRepository;
import com.garage.project.Owner.Repo.UserRepository;
import com.garage.project.Owner.Service.AuthLoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthLoginServiceImpl implements AuthLoginService {

    private final GarageRepository garageRepo;

    public AuthLoginServiceImpl(GarageRepository garageRepo) {
        this.garageRepo = garageRepo;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        LoginResponseDTO response = new LoginResponseDTO();

        // 🔍 FIND GARAGE BY EMAIL OR PHONE
        Garage garage = garageRepo
                .findByEmailOrPhone(request.getUsername(), request.getUsername())
                .orElse(null);

        // ❌ NOT FOUND
        if (garage == null) {
            response.setMessage("Garage not found");
            response.setUser(null);
            return response;
        }

        // ❌ WRONG PASSWORD
        if (!garage.getPassword().equals(request.getPassword())) {
            response.setMessage("Invalid password");
            response.setUser(null);
            return response;
        }

        // ✅ SUCCESS RESPONSE (ONLY OWNER NAME + MESSAGE)
        Map<String, Object> data = new HashMap<>();
        data.put("ownerName", garage.getOwnerName());

        response.setMessage("Login Successful");
        response.setUser(data);

        return response;
    }
}