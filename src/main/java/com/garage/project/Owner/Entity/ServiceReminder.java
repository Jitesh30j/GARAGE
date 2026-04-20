package com.garage.project.Owner.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "service_reminder")
@Data
public class ServiceReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    private String serviceType;

    private Date lastServiceDate;
    private Date nextServiceDate;

    private Integer status; // 0 = pending, 1 = sent

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
        this.status = 0;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }
}