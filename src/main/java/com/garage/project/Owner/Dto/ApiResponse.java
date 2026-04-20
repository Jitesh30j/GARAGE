package com.garage.project.Owner.Dto;

public class ApiResponse {

    private String status;   // success / failure
    private int code;        // 200 / 404 / 500
    private String message;
    private Object data;     // always null (as per your requirement)

    public ApiResponse(String status, int code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters & Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}