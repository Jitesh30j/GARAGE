package com.garage.project.Owner.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String garageName;
    private String ownerName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String address;
    private String city;
    private String state;
    private String pin;

    // 🔐 Final Password
    private String password;

    // 🔐 Temp password (registration)
    @Column(name = "temp_password")
    private String tempPassword;

    private String openingTime;
    private String closingTime;

    private boolean isActive = true;

    // ================= REGISTRATION OTP =================
    @Column(name = "reg_otp")
    private String regOtp;

    @Column(name = "reg_otp_verified")
    private Integer regOtpVerified = 1;

    // ================= FORGOT PASSWORD OTP =================
    @Column(name = "forgot_otp")
    private String forgotOtp;

    @Column(name = "forgot_otp_verified")
    private Integer forgotOtpVerified = 1;

    // Common expiry
    private LocalDateTime otpExpiryTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ================= GETTERS & SETTERS =================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGarageName() { return garageName; }
    public void setGarageName(String garageName) { this.garageName = garageName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTempPassword() { return tempPassword; }
    public void setTempPassword(String tempPassword) { this.tempPassword = tempPassword; }

    public String getOpeningTime() { return openingTime; }
    public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }

    public String getClosingTime() { return closingTime; }
    public void setClosingTime(String closingTime) { this.closingTime = closingTime; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getRegOtp() { return regOtp; }
    public void setRegOtp(String regOtp) { this.regOtp = regOtp; }

    public Integer getRegOtpVerified() { return regOtpVerified; }
    public void setRegOtpVerified(Integer regOtpVerified) { this.regOtpVerified = regOtpVerified; }

    public String getForgotOtp() { return forgotOtp; }
    public void setForgotOtp(String forgotOtp) { this.forgotOtp = forgotOtp; }

    public Integer getForgotOtpVerified() { return forgotOtpVerified; }
    public void setForgotOtpVerified(Integer forgotOtpVerified) { this.forgotOtpVerified = forgotOtpVerified; }

    public LocalDateTime getOtpExpiryTime() { return otpExpiryTime; }
    public void setOtpExpiryTime(LocalDateTime otpExpiryTime) { this.otpExpiryTime = otpExpiryTime; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
