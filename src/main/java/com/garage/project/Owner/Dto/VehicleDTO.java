package com.garage.project.Owner.Dto;

import java.util.Date;

public class VehicleDTO {

    private Long vehicleId;
    private String brand;
    private String vehicleModel;
    private String vehicleNumber;
    private String vehicleName;
    private String vehicleType;
    private Date lastServiceDate;

    private int customerId;
    private Long garageId;

    // GETTERS & SETTERS
    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public Date getLastServiceDate() { return lastServiceDate; }
    public void setLastServiceDate(Date lastServiceDate) { this.lastServiceDate = lastServiceDate; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public Long getGarageId() { return garageId; }
    public void setGarageId(Long garageId) { this.garageId = garageId; }
}