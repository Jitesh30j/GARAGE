package com.garage.project.Owner.Dto;

public class ResetPasswordRequest {
    private String email;
    private String newpassword;
    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNewpassword() { return newpassword; }
    public void setNewpassword(String newpassword) { this.newpassword = newpassword; }
}
