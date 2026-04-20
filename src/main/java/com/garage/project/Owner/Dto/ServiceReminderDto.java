package com.garage.project.Owner.Dto;

import lombok.Data;
import java.util.Date;

@Data
public class ServiceReminderDto {

    private Long id;
    private Long vehicleId;
    private Integer customerId;
    private Long garageId;

    private String serviceType;

    private Date lastServiceDate;
    private Date nextServiceDate;
}