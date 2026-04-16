package com.garage.project.Owner.Dto;

import lombok.Data;

@Data
public class SupplierDTO {

    private int supplierId;
    private String supplierName;
    private String email;
    private String phone;
    private String address;
    private String supplierGst;

    // ONLY FIX
    private Long garageId;
}