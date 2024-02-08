package com.bookingservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

public class BookingRequest {
    private String userName; // This represents the user who made the booking
    private String pickupLocation;
    private String dropoffLocation;
    private String pickupTime; // Time when the user wants to be picked up
    private String cabType;
    private String vehicleReg; //Registration number for the booked vehicle


    public BookingRequest(String userName, String pickupLocation, String dropoffLocation, String pickupTime, String cabType, String vehicleReg) {
        this.userName = userName;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.cabType = cabType;
        this.vehicleReg = vehicleReg;
    }

    public BookingRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getCabType() {
        return cabType;
    }

    public void setCabType(String cabType) {
        this.cabType = cabType;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "userName='" + userName + '\'' +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", pickupTime='" + pickupTime + '\'' +
                ", cabType='" + cabType + '\'' +
                ", vehicleReg='" + vehicleReg + '\'' +
                '}';
    }
}
