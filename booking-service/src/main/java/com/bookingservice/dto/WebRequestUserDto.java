package com.bookingservice.dto;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class WebRequestUserDto {
    Long idUser;
    String firstName;
    String lastName;
    String username;
    String password;
    Date birth;
    String status;
    private UserType userType;

    public WebRequestUserDto(Long idUser, String firstName, String lastName, String username, String password, Date birth, String status, UserType userType) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.status = status;
        this.userType = userType;
    }
    public WebRequestUserDto() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "WebRequestUserDto{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birth=" + birth +
                ", status='" + status + '\'' +
                ", userType=" + userType +
                '}';
    }


    public enum UserType {
        DRIVER,
        USER,
        MODERATOR
    }
}
