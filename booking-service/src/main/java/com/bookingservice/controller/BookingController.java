package com.bookingservice.controller;


import com.bookingservice.dto.BookingRequest;
import com.bookingservice.dto.BookingResponse;
import com.bookingservice.entity.BookingEntity;
import com.bookingservice.service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/test")
    public String test(){
        return  "Booking service works";
    }
    @PostMapping("/book")
//    @CircuitBreaker(name = "users", fallbackMethod = "bookingFallBackMethod")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest booking) {
        try {
            BookingResponse createdBooking = bookingService.createBooking(booking);
            return ResponseEntity.ok(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public String bookingFallBackMethod(BookingRequest bookingRequest, RuntimeException runtimeException){
        return "Opps! Something went Wrong please try again latter!";
    }



    @GetMapping("/details/{id}")
    public ResponseEntity<BookingEntity> getBookingById(@PathVariable Long id) {
        try {
            BookingEntity booking = bookingService.getBookingById(id);
            if (booking != null) {
                return ResponseEntity.ok(booking);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/user/{userName}")
    @CircuitBreaker(name = "usersget")
    public ResponseEntity<List<BookingEntity>> getBookingsForUser(@PathVariable String userName) {
        try {
            List<BookingEntity> bookings = bookingService.getBookingsForUser(userName);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
