package com.garage.project.Owner.Dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String garageName;
    private String ownerName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String password;
    private String openingTime;
    private String closingTime;
}