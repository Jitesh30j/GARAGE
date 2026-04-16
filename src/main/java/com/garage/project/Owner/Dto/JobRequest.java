package com.garage.project.Owner.Dto;

import lombok.Data;

@Data
public class JobRequest {

    private int jobId;
    private Integer garageId;

    private int customerId;
    private int vehicleId;
    private int mechanicId;

    private String problemDescription;
    private String jobStatus;
}