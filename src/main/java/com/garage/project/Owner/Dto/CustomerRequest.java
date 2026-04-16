package com.garage.project.Owner.Dto;

import lombok.Data;

@Data
public class CustomerRequest {

    private Integer customerId;
    private Integer garageId;

    private String name;
    private String phone;
    private String email;
    private String address;
    private String vehicleName;
    private String vehicleType;
    private String updatedBy;
}