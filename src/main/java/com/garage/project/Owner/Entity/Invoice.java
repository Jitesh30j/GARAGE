package com.garage.project.Owner.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "invoices")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceId;

    private int jobId;

    private Integer garageId;

    private double labourCost;
    private double partsCost;
    private double totalAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;

    @PrePersist
    public void onCreate() {
        this.invoiceDate = new Date();
    }

    public void calculateTotal() {
        this.totalAmount = this.labourCost + this.partsCost;
    }
}