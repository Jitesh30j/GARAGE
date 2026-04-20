package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.LoginRequestDTO;
import com.garage.project.Owner.Dto.LoginResponseDTO;
import com.garage.project.Owner.Service.AuthLoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthLoginController {

    private final AuthLoginService service;

    public AuthLoginController(AuthLoginService service) {
        this.service = service;
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return service.login(request);
    }
}