package com.garage.project.Owner.Dto;
import lombok.Data;

@Data
public class InvoiceRequest {

    private int invoiceId;
    private Integer garageId;

    private int jobId;

    private double labourCost;
    private double partsCost;
}

