package com.bookingservice.dto;


import lombok.*;

public class BookingResponse {
    private BookingResponseDto bookingInfo;
    private String message;
    private int code;

    // private constructor to enforce the use of the builder
    private BookingResponse(Builder builder) {
        this.bookingInfo = builder.bookingInfo;
        this.message = builder.message;
        this.code = builder.code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public BookingResponseDto getBookingInfo() {
        return bookingInfo;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "bookingInfo=" + bookingInfo +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public static class Builder {
        private BookingResponseDto bookingInfo;
        private String message;
        private int code;

        private Builder() {
        }

        public Builder bookingInfo(BookingResponseDto bookingInfo) {
            this.bookingInfo = bookingInfo;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public BookingResponse build() {
            return new BookingResponse(this);
        }
    }
}
