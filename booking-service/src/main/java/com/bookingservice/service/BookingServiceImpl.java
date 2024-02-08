package com.bookingservice.service;


import com.bookingservice.dto.BookingRequest;
import com.bookingservice.dto.BookingResponse;
import com.bookingservice.dto.BookingResponseDto;
import com.bookingservice.dto.WebRequestUserDto;
import com.bookingservice.entity.BookingEntity;
import com.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public BookingResponse createBooking(BookingRequest booking) {

        System.out.println(booking);


        try {
            WebRequestUserDto user = webClientBuilder.build().get()
                    .uri("http://users-service/api/users/search_user_name/" + booking.getUserName())
                    .retrieve()
                    .bodyToMono(WebRequestUserDto.class)
                    .block();

            assert user != null;

            if(user.getIdUser() == null){
                return BookingResponse.builder()
                        .bookingInfo(new BookingResponseDto())
                        .code(1)
                        .message("User with Usename " +booking.getUserName()+" does not exist")
                        .build();

            }

            BookingEntity bookingEntity = BookingEntity.builder()
                    .userId(user.getIdUser())
                    .pickupLocation(booking.getPickupLocation())
                    .dropoffLocation(booking.getDropoffLocation())
                    .pickupTime(booking.getPickupTime())
                    .vehicleReg(booking.getVehicleReg())
                    .cabType(booking.getCabType())
                    .build();
            String succesMessage = booking.getCabType() + " Cab Booked from " + booking.getPickupLocation() + " to " + booking.getDropoffLocation();
            BookingResponseDto bookingResponseDto = BookingResponseDto.builder()
                    .id(bookingRepository.save(bookingEntity).getId())
                    .userId(user.getIdUser())
                    .pickupLocation(booking.getPickupLocation())
                    .dropoffLocation(booking.getDropoffLocation())
                    .pickupTime(booking.getPickupTime())
                    .build();


            return BookingResponse.builder()
                    .bookingInfo(bookingResponseDto)
                    .code(1)
                    .message(succesMessage)
                    .build();
        }catch (Exception e){
            System.out.println("---> "+e);
            return BookingResponse.builder()
                    .bookingInfo(new BookingResponseDto())
                    .code(0)
                    .message(e.getMessage())
                    .build();

        }
    }


    @Override
    public BookingEntity getBookingById(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public List<BookingEntity> getBookingsForUser(String userName) {
        WebRequestUserDto user = webClientBuilder.build().get()
                .uri("http://users-service/api/users/search_user_name/" + userName)
                .retrieve()
                .bodyToMono(WebRequestUserDto.class)
                .block();

        System.out.println("=====>"+user);

        if (user != null) {
            System.out.println("=====>");

            Long userId = user.getIdUser();
            List<BookingEntity> bookings = bookingRepository.findAllByUserId(user.getIdUser());
            return bookings;
        } else {
            return Collections.emptyList();
        }
    }

}
