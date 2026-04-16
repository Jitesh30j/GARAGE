package com.garage.project.Owner.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // getters & setters

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Garage getGarage() { return garage; }
    public void setGarage(Garage garage) { this.garage = garage; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}