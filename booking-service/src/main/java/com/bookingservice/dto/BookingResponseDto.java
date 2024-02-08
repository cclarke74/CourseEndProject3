package com.bookingservice.dto;

import lombok.*;

import java.util.Date;

public class BookingResponseDto {
    private Long id;
    private Long userId;
    private String pickupLocation;
    private String dropoffLocation;
    private String pickupTime;

    // private constructor to enforce the use of the builder
    public BookingResponseDto(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.pickupLocation = builder.pickupLocation;
        this.dropoffLocation = builder.dropoffLocation;
        this.pickupTime = builder.pickupTime;
    }

    public BookingResponseDto(){}

    public static Builder builder() {
        return new Builder();
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

    @Override
    public String toString() {
        return "BookingResponseDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", pickupTime='" + pickupTime + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private String pickupLocation;
        private String dropoffLocation;
        private String pickupTime;

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

        public BookingResponseDto build() {
            return new BookingResponseDto(this);
        }
    }
}
