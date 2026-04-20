package com.garage.project.Owner.Entity;

/*package org.m.entity;

import jakarta.persistence.*;

@Entity
@Table(name="suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    private String supplierName;
    private String email;
    private String phone;
    private String address;
    private String supplierGst;

    // ✅ ADD THIS (same like Vehicle)
    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    // Getters & Setters
    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getSupplierGst() { return supplierGst; }
    public void setSupplierGst(String supplierGst) { this.supplierGst = supplierGst; }

    public Integer getGarage() { return garage; }
    public void setGarage(Integer garage) { this.garage = garage; }
}*/

import jakarta.persistence.*;

@Entity
@Table(name="suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    private String supplierName;
    private String email;
    private String phone;
    private String address;
    private String supplierGst;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getSupplierGst() { return supplierGst; }
    public void setSupplierGst(String supplierGst) { this.supplierGst = supplierGst; }

    public Garage getGarage() { return garage; }
    public void setGarage(Garage garage) { this.garage = garage; }
}