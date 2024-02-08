package com.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private String pickupLocation;
    private String dropoffLocation;
    private String pickupTime;
    private String vehicleReg;
    private String cabType;

    public BookingEntity() {
    }

    public BookingEntity(Long id, Long userId, String pickupLocation, String dropoffLocation, String pickupTime, String vehicleReg, String cabType) {
        this.id = id;
        this.userId = userId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.vehicleReg = vehicleReg;
        this.cabType = cabType;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public String getCabType() {
        return cabType;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", pickupTime='" + pickupTime + '\'' +
                ", vehicleReg='" + vehicleReg + '\'' +
                ", cabType='" + cabType + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private String pickupLocation;
        private String dropoffLocation;
        private String pickupTime;
        private String vehicleReg;
        private String cabType;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder pickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
            return this;
        }

        public Builder dropoffLocation(String dropoffLocation) {
            this.dropoffLocation = dropoffLocation;
            return this;
        }

        public Builder pickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
            return this;
        }

        public Builder vehicleReg(String vehicleReg) {
            this.vehicleReg = vehicleReg;
            return this;
        }

        public Builder cabType(String cabType) {
            this.cabType = cabType;
            return this;
        }

        public BookingEntity build() {
            return new BookingEntity();
        }
    }
}
