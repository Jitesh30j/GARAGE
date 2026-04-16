package com.garage.project.Owner.Dto;

public class UserDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private Long garageId;
    private int roleId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getGarageId() { return garageId; }
    public void setGarageId(Long garageId) { this.garageId = garageId; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
}
