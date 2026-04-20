package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.LoginRequestDTO;
import com.garage.project.Owner.Dto.LoginResponseDTO;

public interface AuthLoginService {
    LoginResponseDTO login(LoginRequestDTO request);
}