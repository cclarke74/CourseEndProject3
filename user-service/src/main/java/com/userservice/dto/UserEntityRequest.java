package com.userservice.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class UserEntityRequest {
    String firstName;
    String lastName;
    String username;
    String password;
    Date birth;
    String status;

    public UserEntityRequest(String firstName, String lastName, String username, String password, Date birth, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.status = status;
    }
    public UserEntityRequest() {}

    public static Builder builder() {
        return new Builder();
    }



    public static class Builder {
        private UserEntityRequest userEntityRequest;

        private Builder() {
            userEntityRequest = new UserEntityRequest();
        }

        public Builder firstName(String firstName) {
            userEntityRequest.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            userEntityRequest.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            userEntityRequest.username = username;
            return this;
        }

        public Builder password(String password) {
            userEntityRequest.password = password;
            return this;
        }

        public Builder birth(Date birth) {
            userEntityRequest.birth = birth;
            return this;
        }

        public Builder status(String status) {
            userEntityRequest.status = status;
            return this;
        }

        public UserEntityRequest build() {
            return userEntityRequest;
        }
    }

    @Override
    public String toString() {
        return "UserEntityRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birth=" + birth +
                ", status='" + status + '\'' +
                '}';
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
}
