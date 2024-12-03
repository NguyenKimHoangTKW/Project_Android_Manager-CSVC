package com.example.quanlycsvc.Models;

public class GoogleLoginRequest {
    public String name;
    public String email;

    public  int idRole;
    public  String message;

    public GoogleLoginRequest(String name, String email, int idRole, String message) {
        this.name = name;
        this.email = email;
        this.idRole = idRole;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
