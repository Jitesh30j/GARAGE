package com.garage.project.Owner.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String name;
    private String phone;
    private String email;
    private String address;

    private String vehicleName;
    private String vehicleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private String updatedBy;

    // Foreign Key (Garage)
    private Integer garageId;

    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }
}


