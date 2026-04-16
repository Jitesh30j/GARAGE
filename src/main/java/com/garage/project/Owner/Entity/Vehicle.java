package com.garage.project.Owner.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    private String brand;
    private String vehicleModel;
    private String vehicleNumber;
    private String vehicleName;
    private String vehicleType;

    @Temporal(TemporalType.DATE)
    private Date lastServiceDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Garage garage;

    // GETTERS
    public Long getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getVehicleModel() { return vehicleModel; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getVehicleName() { return vehicleName; }
    public String getVehicleType() { return vehicleType; }
    public Date getLastServiceDate() { return lastServiceDate; }
    public Customer getCustomer() { return customer; }
    public Garage getGarage() { return garage; }

    // SETTERS
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public void setLastServiceDate(Date lastServiceDate) { this.lastServiceDate = lastServiceDate; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setGarage(Garage garage) { this.garage = garage; }
}